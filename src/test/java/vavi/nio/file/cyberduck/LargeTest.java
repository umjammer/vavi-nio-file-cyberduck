/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.nio.file.FileSystem;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static vavi.nio.file.Base.testLargeFile;


/**
 * CyberDuck. (sftp)
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/xx umjammer initial version <br>
 */
public class LargeTest {

    /** on a temporary sftp server */
    @Test
    void test01() throws Exception {
        try (SftpTestServer server = new SftpTestServer();
             FileSystem fs = new CyberduckFileSystemProvider().newFileSystem(server.getUri(), Collections.emptyMap())) {

            testLargeFile(fs, CyberduckUploadOption.class);
        }
    }
}