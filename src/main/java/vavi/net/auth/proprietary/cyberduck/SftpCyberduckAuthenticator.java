/*
 * Copyright (c) 2020 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.net.auth.proprietary.cyberduck;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;

import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.DisabledCancelCallback;
import ch.cyberduck.core.DisabledHostKeyCallback;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.DisabledPasswordStore;
import ch.cyberduck.core.DisabledProgressListener;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.Local;
import ch.cyberduck.core.LoginConnectionService;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.sftp.SFTPProtocol;
import ch.cyberduck.core.sftp.SFTPSession;
import ch.cyberduck.core.ssl.DefaultX509KeyManager;
import ch.cyberduck.core.ssl.DisabledX509TrustManager;
import vavi.net.http.HttpUtil;
import vavi.nio.file.cyberduck.CyberduckFileSystemProvider;
import vavi.util.Debug;
import vavi.util.properties.annotation.Property;
import vavi.util.properties.annotation.PropsEntity;


/**
 * SftpCyberduckAuthenticator.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/05/02 umjammer initial version <br>
 */
public class SftpCyberduckAuthenticator implements CyberduckAuthenticator {

    /**
     * <p>
     * properties file "~/vavifuse/credentials.properties"
     * <ul>
     * <li> ssh.keyPath.<i>alias</i>
     * <li> ssh.passphrase.<i>alias</i>
     * </ul>
     * </p>
     */
    @PropsEntity(url = "file://${user.home}/.vavifuse/credentials.properties")
    private static final class SftpCyberduckCredential extends CyberduckCredential {
        @Property(name = "ssh.keyPath.{0}")
        private String keyPath;
        @Property(name = "ssh.passphrase.{0}")
        private transient String passphrase;

        public SftpCyberduckCredential(String alias) {
            super(alias);
        }

        /**
         * @param uri ?keyPath={keyPath}&passphrase={passphrase}
         */
        public SftpCyberduckCredential(URI uri) {
            super(uri);
            try {
                Map<String, String[]> params = HttpUtil.splitQuery(uri);
                this.keyPath = params.get("keyPath")[0];
                this.passphrase = params.get("passphrase")[0];
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public String getClientId() {
            return "sftp";
        }

        @Override
        public int getPort() {
            return port == -1 ? 22 : port;
        }
    }

    /** */
    private URI uri;

    @Override
    public CyberduckCredential getCredential(String alias, URI uri) {
        this.uri = uri;

        CyberduckCredential credential;
        if (alias != null) {
            credential = new SftpCyberduckCredential(alias);
Debug.println("credential: by alias " + alias);
        } else {
            credential = new SftpCyberduckCredential(uri);
            if (credential.getId() == null || credential.getId().isEmpty()) {
                throw new NoSuchElementException("uri should have a username or a param " + CyberduckFileSystemProvider.PARAM_ALIAS);
            }
Debug.println("credential: by uri");
        }

        return credential;
    }

    @Override
    public Session<?> authorize(CyberduckCredential credential) throws IOException {
        SftpCyberduckCredential c = (SftpCyberduckCredential) credential;
        try {
            Credentials credentials = new Credentials(c.getId());
            credentials.setIdentity(new Local(c.keyPath));
            credentials.setIdentityPassphrase(c.passphrase);
            Host host = new Host(new SFTPProtocol(), c.getHost(), c.getPort(), c.getPath(), credentials) {
                @Override public String getProperty(String key) {
                    if("ssh.authentication.agent.enable".equals(key)) {
                        return String.valueOf(false);
                    }
                    return null;
                }
            };
            SFTPSession session = new SFTPSession(host, new DisabledX509TrustManager(), new DefaultX509KeyManager());
            LoginConnectionService login = new LoginConnectionService(new DisabledLoginCallback(), new DisabledHostKeyCallback(), new DisabledPasswordStore(), new DisabledProgressListener());
            login.connect(session, new DisabledCancelCallback());
            return session;
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }
}

/* */
