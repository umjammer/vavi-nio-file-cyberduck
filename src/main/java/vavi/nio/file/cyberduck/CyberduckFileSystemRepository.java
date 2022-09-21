/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.github.fge.filesystem.driver.FileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemRepositoryBase;

import vavi.net.auth.proprietary.cyberduck.CyberduckAuthenticator;
import vavi.net.auth.proprietary.cyberduck.CyberduckCredential;
import vavi.util.Debug;

import ch.cyberduck.core.Session;


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

    /**
     * @param uri "cyberduck:protocol:///?alias=alias", sub url (after "cyberduck:") parts will be replaced by properties.
     *            if you don't use alias, the url must be included username, password, host, port.
     */
    @Nonnull
    @Override
    public FileSystemDriver createDriver(final URI uri, final Map<String, ?> env) throws IOException {
        String uriString = uri.toString();
        URI subUri = URI.create(uriString.substring(uriString.indexOf(':') + 1));
        String protocol = subUri.getScheme();
Debug.println("protocol: " + protocol);

        Map<String, String> params = getParamsMap(subUri);
        String alias = params.get(CyberduckFileSystemProvider.PARAM_ALIAS);

        CyberduckAuthenticator authenticator = CyberduckAuthenticator.getAuthenticator(subUri);
        CyberduckCredential credential = authenticator.getCredential(alias, subUri);
        Session<?> session = authenticator.authorize(credential);

        CyberduckFileStore fileStore = new CyberduckFileStore(session, factoryProvider.getAttributesFactory());
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
