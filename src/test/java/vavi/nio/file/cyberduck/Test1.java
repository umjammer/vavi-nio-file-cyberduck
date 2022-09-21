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
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import vavi.util.Debug;

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
        env.put(CyberduckFileSystemProvider.ENV_DISABLED_FILE_CACHE, true);
        FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(uri, env);
        Path root = fs.getRootDirectories().iterator().next();
Debug.println(root.toString());
        Files.list(root).forEach(System.err::println);
Debug.println("---");
        Files.list(root.resolve("Books/IT")).forEach(System.err::println);
    }

    /**
     * environment variable
     * <ul>
     * <li> TEST_WEBDAV_ACCOUNT
     * <li> TEST_WEBDAV_PASSWORD
     * <li> TEST_WEBDAV_HOST
     * <li> TEST_WEBDAV_PORT
     * <li> TEST_WEBDAV_PATH
     * </ul>
     */
    @Test
    void test01() throws Exception {
        String username = URLEncoder.encode(System.getenv("TEST_WEBDAV_ACCOUNT"), "utf-8");
        String password = System.getenv("TEST_WEBDAV_PASSWORD");
        String host = System.getenv("TEST_WEBDAV_HOST");
        String port = System.getenv("TEST_WEBDAV_PORT");
        String path = System.getenv("TEST_WEBDAV_PATH");

        URI uri = URI.create(String.format("cyberduck:webdav://%s:%s@%s:%s%s", username, password, host, port, path));

        testAll(new CyberduckFileSystemProvider().newFileSystem(uri, Collections.emptyMap()));
    }

    /**
     * environment variable
     * <ul>
     * <li> TEST_SFTP_ACCOUNT
     * <li> TEST_SFTP_PASSPHRASE
     * <li> TEST_SFTP_HOST
     * <li> TEST_SFTP_KEYPATH
     * <li> TEST_SFTP_PATH
     * </ul>
     */
    @Test
    @DisabledIfEnvironmentVariable(named = "GITHUB_WORKFLOW", matches = ".*")
    void test02() throws Exception {
        String username = URLEncoder.encode(System.getenv("TEST_SFTP_ACCOUNT"), "utf-8");
        String passPhrase = System.getenv("TEST_SFTP_PASSPHRASE");
        String host = System.getenv("TEST_SFTP_HOST");
        String keyPath = System.getenv("TEST_SFTP_KEYPATH");
        String path = System.getenv("TEST_SFTP_PATH");

        URI uri = URI.create(String.format("cyberduck:sftp://%s@%s%s?keyPath=%s&passphrase=%s", username, host, path, keyPath, passPhrase));

        Map<String, Object> env = new HashMap<>();
        env.put(CyberduckFileSystemProvider.ENV_DISABLED_FILE_CACHE, true);

        testAll(new CyberduckFileSystemProvider().newFileSystem(uri, env));
    }
}

/* */
