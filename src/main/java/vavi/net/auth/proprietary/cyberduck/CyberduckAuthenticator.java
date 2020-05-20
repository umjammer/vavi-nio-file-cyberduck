/*
 * Copyright (c) 2020 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.net.auth.proprietary.cyberduck;

import java.net.URI;

import vavi.net.auth.Authenticator;

import ch.cyberduck.core.Session;


/**
 * CyberduckAuthenticator.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/02/15 umjammer initial version <br>
 */
public interface CyberduckAuthenticator extends Authenticator<CyberduckCredential, Session<?>> {

    /** */
    CyberduckCredential getCredential(String alias, URI uri);

    /** factory */
    static CyberduckAuthenticator getAuthenticator(URI uri) {
        String scheme = uri.getScheme();
        switch (scheme) {
        case "webdav": return new WebdavCyberduckAuthenticator();
        case "sftp": return new SftpCyberduckAuthenticator();
        default: throw new IllegalArgumentException(scheme);
        }
    }
}

/* */
