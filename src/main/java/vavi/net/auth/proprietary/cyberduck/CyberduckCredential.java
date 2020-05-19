/*
 * Copyright (c) 2020 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.net.auth.proprietary.cyberduck;

import java.io.IOException;
import java.net.URI;

import vavi.net.auth.AppCredential;
import vavi.net.auth.UserCredential;
import vavi.util.properties.annotation.Property;
import vavi.util.properties.annotation.PropsEntity;


/**
 * CyberduckCredential.
 * <p>
 * properties file "~/vavifuse/credentials.properties"
 * </p>
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/02/15 umjammer initial version <br>
 */
@PropsEntity(url = "file://${HOME}/.vavifuse/credentials.properties")
public class CyberduckCredential implements UserCredential, AppCredential {

    @Property(name = "cyberduck.username.{0}")
    protected String username;
    @Property(name = "cyberduck.password.{0}")
    protected transient String password;
    @Property(name = "cyberduck.host.{0}")
    protected String host;
    @Property(name = "cyberduck.port.{0}")
    protected int port = -1;

    /** */
    protected String scheme;

    /** */
    public CyberduckCredential(URI uri) {
//Debug.println(uri);
        this.scheme = uri.getScheme();
        String[] userInfo = uri.getUserInfo() != null ? uri.getUserInfo().split(":") : null;
        if (userInfo != null && !userInfo[0].isEmpty()) {
            this.username = userInfo[0];
        }
        if (userInfo != null && !userInfo[1].isEmpty()) {
            this.password = userInfo[1];
        }
        if (uri.getHost() != null && !uri.getHost().isEmpty()) {
            this.host = uri.getHost();
        }
        if (uri.getPort() != -1) {
            this.port = uri.getPort();
        }
    }

    /**
     * @param alias
     */
    public CyberduckCredential(String alias) {
        try {
            PropsEntity.Util.bind(this, alias);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getClientId() {
        return scheme;
    }

    @Override
    public String getApplicationName() {
        return "vavi-apps-fuse";
    }

    @Override
    public String getScheme() {
        return "cyberduck";
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}

/* */
