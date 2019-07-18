/*
 * Copyright (c) 2019 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static vavi.nio.file.Base.testAll;


/**
 * Main.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2019/07/17 umjammer initial version <br>
 */
class Main {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("cyberduck:webdav:///webdav?id=" + "webdav");

        Map<String, Object> env = new HashMap<>();

        FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(uri, env);
        Files.list(fs.getPath("/")).forEach(System.err::println);
        Files.list(fs.getPath("/onedrive%3Avavivavi%40live.jp")).forEach(System.err::println);
    }

    @Test
    void test01() throws Exception {
        URI uri = URI.create("cyberduck:webdav:///webdav/onedrive%3Avavivavi%40live.jp?id=" + "webdav");

        testAll(new CyberduckFileSystemProvider().newFileSystem(uri, Collections.EMPTY_MAP));
    }
}

/* */
