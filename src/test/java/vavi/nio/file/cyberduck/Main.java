/*
 * Copyright (c) 2019 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import vavi.util.properties.annotation.Property;
import vavi.util.properties.annotation.PropsEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.DisabledCancelCallback;
import ch.cyberduck.core.DisabledHostKeyCallback;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.dav.DAVSSLProtocol;
import ch.cyberduck.core.dav.DAVSession;
import ch.cyberduck.core.proxy.Proxy;


/**
 * Main.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2019/07/17 umjammer initial version <br>
 */
@PropsEntity(url = "file://${user.home}/.vavifuse/credentials.properties")
class Main {

    @Property(name = "vfs.username.{0}")
    private String username;
    @Property(name = "vfs.password.{0}")
    private transient String password;
    @Property(name = "vfs.host.{0}")
    private String host;
    @Property(name = "vfs.port.{0}")
    private int port;

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        PropsEntity.Util.bind(app, "webdav");

        Credentials credentials = new Credentials(app.username, app.password);
        final Host host = new Host(new DAVSSLProtocol(), app.host, app.port, "webdav", credentials);
        final DAVSession session = new DAVSession(host);
        assertNotNull(session.open(Proxy.DIRECT, new DisabledHostKeyCallback(), new DisabledLoginCallback()));
        assertTrue(session.isConnected());
        assertNotNull(session.getClient());
        session.login(Proxy.DIRECT, new DisabledLoginCallback(), new DisabledCancelCallback());

        URI uri = URI.create("cyberduck:///?id=" + app.username);

        Map<String, Object> env = new HashMap<>();
        env.put("session", session);

        FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(uri, env);
        Files.list(fs.getPath("/")).forEach(System.err::println);
        Files.list(fs.getPath("/onedrive%3Avavivavi%40live.jp")).forEach(System.err::println);
    }

    @Test
    void test() {
        fail("Not yet implemented");
    }
}

/* */
