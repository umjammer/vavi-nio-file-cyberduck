/*
 * Copyright (c) 2017 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import vavi.net.fuse.Base;

import static org.junit.jupiter.api.Assumptions.assumeTrue;


/**
 * fuse test. (cyberduck)
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2017/03/19 umjammer initial version <br>
 */
@DisabledIfEnvironmentVariable(named = "GITHUB_WORKFLOW", matches = ".*")
public class FuseTest {

    SftpTestServer server;
    String mountPoint;
    FileSystem fs;
    Map<String, Object> options;

    /**
     * environment variable
     * <ul>
     * <li> TEST_MOUNT_POINT ... an existing empty directory, if not set a temporary directory is used
     * </ul>
     * fuse is installed or not.
     * on macOS whether the mount works is not detectable beforehand (macFUSE 5 mounts without the kext loaded),
     * an unusable fuse fails at mounting.
     */
    static boolean isFuseAvailable() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            return Files.exists(Path.of("/dev/fuse"));
        } else if (os.contains("mac")) {
            return Files.isDirectory(Path.of("/Library/Filesystems/macfuse.fs"));
        } else {
            return false;
        }
    }

    @BeforeEach
    public void before() throws Exception {
        assumeTrue(isFuseAvailable(), "fuse (macFUSE) is not installed");

        mountPoint = System.getenv("TEST_MOUNT_POINT");
        if (mountPoint == null || mountPoint.isEmpty()) {
            mountPoint = Files.createTempDirectory("cyberduck-fuse-").toString();
        }

        server = new SftpTestServer();

        Map<String, Object> env = new HashMap<>();
        env.put("ignoreAppleDouble", true);

        fs = FileSystems.newFileSystem(server.getUri(), env);

        options = new HashMap<>();
        options.put("fsname", "cyberduck_fs" + "@" + System.currentTimeMillis());
        options.put("noappledouble", null);
        //options.put("noapplexattr", null);
        options.put(vavi.net.fuse.javafs.JavaFSFuse.ENV_DEBUG, false);
        options.put(vavi.net.fuse.javafs.JavaFSFuse.ENV_READ_ONLY, false);
    }

    @AfterEach
    public void after() throws Exception {
        try {
            if (fs != null) fs.close();
        } finally {
            if (server != null) server.close();
        }
        if (mountPoint != null) {
            waitUnmounted(Path.of(mountPoint));
        }
    }

    /**
     * some providers (e.g. jnr-fuse) unmount asynchronously,
     * so the next provider's mount fails while the mount point is still busy.
     */
    static void waitUnmounted(Path mountPoint) throws IOException, InterruptedException {
        FileStore parentStore = Files.getFileStore(mountPoint.toAbsolutePath().getParent());
        for (int i = 0; i < 100; i++) {
            try {
                if (Files.isDirectory(mountPoint) && Files.getFileStore(mountPoint).equals(parentStore)) {
                    return;
                }
            } catch (IOException e) {
                // still in transition
            }
            Thread.sleep(100);
        }
        throw new IllegalStateException("mount point is not unmounted: " + mountPoint);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "vavi.net.fuse.javafs.JavaFSFuseProvider",
        "vavi.net.fuse.jnrfuse.JnrFuseFuseProvider",
        "vavi.net.fuse.fusejna.FuseJnaFuseProvider",
    })
    public void test01(String providerClassName) throws Exception {
        System.setProperty("vavi.net.fuse.FuseProvider.class", providerClassName);

        Base.testFuse(fs, mountPoint, options);
    }
}
