/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.nio.file.FileSystem;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static vavi.nio.file.Base.testMoveFolder;


/**
 * CyberDuck. (sftp)
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/xx umjammer initial version <br>
 */
public class TotalTest {

    /** on a temporary sftp server */
    @Test
    void test02() throws Exception {
        try (SftpTestServer server = new SftpTestServer();
             FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(server.getUri(), Collections.emptyMap())) {

            testMoveFolder(fs);
        }
    }
}