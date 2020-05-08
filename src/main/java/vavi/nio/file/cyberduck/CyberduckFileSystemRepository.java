/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.github.fge.filesystem.driver.FileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemRepositoryBase;

import vavi.util.Debug;
import vavi.util.properties.annotation.Property;
import vavi.util.properties.annotation.PropsEntity;

import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.DisabledCancelCallback;
import ch.cyberduck.core.DisabledHostKeyCallback;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.dav.DAVSSLProtocol;
import ch.cyberduck.core.dav.DAVSession;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.proxy.Proxy;


/**
 * CyberduckFileSystemRepository.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
@ParametersAreNonnullByDefault
public final class CyberduckFileSystemRepository extends FileSystemRepositoryBase {

    /** */
    public CyberduckFileSystemRepository() {
        super("cyberduck", new CyberduckFileSystemFactoryProvider());
    }

    /** TODO naming */
    static abstract class Factory {
        @Property(name = "cyberduck.username.{0}")
        protected String username;
        @Property(name = "cyberduck.password.{0}")
        protected transient String password;
        @Property(name = "cyberduck.host.{0}")
        protected String host;
        @Property(name = "cyberduck.port.{0}")
        protected String port;
        /** */
        protected URI uri;

        /** */
        Factory(URI uri) {
            this.uri = uri;
            String[] userInfo = uri.getUserInfo() != null ? uri.getUserInfo().split(":") : null;
            this.username = userInfo != null && !userInfo[0].isEmpty() ? userInfo[0] : null;
            this.password = userInfo != null && !userInfo[1].isEmpty() ? userInfo[1] : null;
            this.host = uri.getHost();
            this.port = uri.getPort() != -1 ? String.valueOf(uri.getPort()) : null;
        }

        /** */
        abstract Session<?> getSession() throws IOException;

        /** TODO implement properly */
        static Factory getFactory(URI uri) {
            String protocol = uri.getScheme();
            switch (protocol) {
            case "webdav": return new WebdavFactory(uri);
            default: throw new IllegalArgumentException(protocol);
            }
        }
    }

    @PropsEntity(url = "file://${user.home}/.vavifuse/credentials.properties")
    private static class WebdavFactory extends Factory {
        WebdavFactory(URI uri) {
            super(uri);
        }
        @Override
        public Session<?> getSession() throws IOException {
            try {
                Credentials credentials = new Credentials(username, password);
                final Host host = new Host(new DAVSSLProtocol(), this.host, port != null ? Integer.parseInt(port) : -1, uri.getPath(), credentials);
                final DAVSession session = new DAVSession(host);
                session.open(Proxy.DIRECT, new DisabledHostKeyCallback(), new DisabledLoginCallback());
                session.login(Proxy.DIRECT, new DisabledLoginCallback(), new DisabledCancelCallback());
                return session;
            } catch (BackgroundException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * @param uri "cyberduck:protocol:///?id=alias", sub url (after "cyberduck:") parts will be replaced by properties.
     */
    @Nonnull
    @Override
    public FileSystemDriver createDriver(final URI uri, final Map<String, ?> env) throws IOException {
        String uriString = uri.toString();
        URI subUri = URI.create(uriString.substring(uriString.indexOf(':') + 1));
        String protocol = subUri.getScheme();
Debug.println("protocol: " + protocol);

        Map<String, String> params = getParamsMap(subUri);
        if (!params.containsKey(CyberduckFileSystemProvider.PARAM_ID)) {
            throw new NoSuchElementException("uri not contains a param " + CyberduckFileSystemProvider.PARAM_ID);
        }
        final String alias = params.get(CyberduckFileSystemProvider.PARAM_ID);

        Factory factory = Factory.getFactory(subUri);
        PropsEntity.Util.bind(factory, alias);
        Session<?> session = factory.getSession();

        final CyberduckFileStore fileStore = new CyberduckFileStore(session, factoryProvider.getAttributesFactory());
        return new CyberduckFileSystemDriver(fileStore, factoryProvider, session, env);
    }

    /* ad-hoc hack for ignoring checking opacity */
    protected void checkURI(@Nullable final URI uri) {
        Objects.requireNonNull(uri);
        if (!uri.isAbsolute()) {
            throw new IllegalArgumentException("uri is not absolute");
        }
        if (!getScheme().equals(uri.getScheme())) {
            throw new IllegalArgumentException("bad scheme");
        }
    }
}
