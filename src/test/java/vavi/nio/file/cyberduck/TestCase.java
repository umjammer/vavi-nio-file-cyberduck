/*
 * Copyright (c) 2019 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vavi.util.Debug;

import static vavi.nio.file.Base.testAll;


/**
 * TestCase.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2019/07/17 umjammer initial version <br>
 */
class TestCase {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("cyberduck:sftp://?alias=" + "sftp");

        Map<String, Object> env = new HashMap<>();
        env.put(CyberduckFileSystemProvider.ENV_DISABLED_FILE_CACHE, true);
        FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(uri, env);
        Path root = fs.getRootDirectories().iterator().next();
Debug.println(Level.FINE, root.toString());
        Files.list(root).forEach(System.err::println);
        System.err.println("---");
        Files.list(root.resolve("waiting")).forEach(System.err::println);
        fs.close();
    }

    /** on a temporary webdav server */
    @Test
    @DisplayName("webdav")
    void test01() throws Exception {
        try (WebdavTestServer server = new WebdavTestServer();
             FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(server.getUri(), Collections.emptyMap())) {
Debug.println(Level.FINE, server.getUri());

            testAll(fs);
        }
    }

    /** on a temporary sftp server */
    @Test
    @DisplayName("sftp")
    void test02() throws Exception {
        Map<String, Object> env = new HashMap<>();
        env.put(CyberduckFileSystemProvider.ENV_DISABLED_FILE_CACHE, true);

        try (SftpTestServer server = new SftpTestServer();
             FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(server.getUri(), env)) {
Debug.println(Level.FINE, server.getUri());

            testAll(fs);
        }
    }
}
