/*
 * Copyright (c) 2019 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static vavi.nio.file.Base.testAll;


/**
 * Test1.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2019/07/17 umjammer initial version <br>
 */
class Test1 {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("cyberduck:webdav:///dav?alias=" + "boxdav");

        Map<String, Object> env = new HashMap<>();

        FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(uri, env);
        // TODO virtual root directory 'dav' douesn't work
        Files.list(fs.getPath("/dav")).forEach(System.err::println);
        Files.list(fs.getPath("/dav/Books/IT")).forEach(System.err::println);
    }

    /**
     * environment variable
     * <ul>
     * <li> TEST_ACCOUNT
     * <li> TEST_PASSWORD
     * <li> TEST_HOST
     * <li> TEST_PORT
     * </ul>
     */
    @Test
    void test01() throws Exception {
        String username = URLEncoder.encode(System.getenv("TEST_ACCOUNT"), "utf-8");
        String password = System.getenv("TEST_PASSWORD");
        String host = System.getenv("TEST_HOST");
        String port = System.getenv("TEST_PORT");

        URI uri = URI.create(String.format("cyberduck:webdav://%s:%s@%s:%s/dav", username, password, host, port));

        testAll(new CyberduckFileSystemProvider().newFileSystem(uri, Collections.EMPTY_MAP));
    }
}

/* */
