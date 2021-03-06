/*
 * Copyright (c) 2020 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.net.auth.proprietary.cyberduck;

import java.io.IOException;
import java.net.URI;
import java.util.NoSuchElementException;

import vavi.nio.file.cyberduck.CyberduckFileSystemProvider;
import vavi.util.Debug;

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
import ch.cyberduck.core.ssl.DefaultX509KeyManager;
import ch.cyberduck.core.ssl.DisabledX509TrustManager;


/**
 * WebdavCyberduckAuthenticator.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/05/02 umjammer initial version <br>
 */
public class WebdavCyberduckAuthenticator implements CyberduckAuthenticator {

    /** */
    private URI uri;

    @Override
    public CyberduckCredential getCredential(String alias, URI uri) {
        this.uri = uri;

        CyberduckCredential credential;
        if (alias != null) {
            credential = new CyberduckCredential(alias);
Debug.println("credential: by alias " + alias);
        } else {
            credential = new CyberduckCredential(uri);
            if (credential.getId() == null || credential.getId().isEmpty()) {
                throw new NoSuchElementException("uri should have a username or a param " + CyberduckFileSystemProvider.PARAM_ALIAS);
            }
Debug.println("credential: by uri");
        }

        return credential;
    }

    @Override
    public Session<?> authorize(CyberduckCredential credential) throws IOException {
        try {
            Credentials credentials = new Credentials(credential.getId(), credential.getPassword());
            Host host = new Host(new DAVSSLProtocol(), credential.getHost(), credential.getPort(), uri.getPath(), credentials);
            DAVSession session = new DAVSession(host, new DisabledX509TrustManager(), new DefaultX509KeyManager());
            session.open(Proxy.DIRECT, new DisabledHostKeyCallback(), new DisabledLoginCallback());
            session.login(Proxy.DIRECT, new DisabledLoginCallback(), new DisabledCancelCallback());
            return session;
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }
}

/* */
