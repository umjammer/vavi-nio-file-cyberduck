/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import com.github.fge.filesystem.driver.DoubleCachedFileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemProviderBase;


/**
 * CyberduckFileSystemProvider.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
public final class CyberduckFileSystemProvider extends FileSystemProviderBase {

    public static final String PARAM_ALIAS = "alias";

    public static final String ENV_DISABLED_FILE_CACHE = DoubleCachedFileSystemDriver.ENV_DISABLED_FILE_CACHE;

    public CyberduckFileSystemProvider() {
        super(new CyberduckFileSystemRepository());
    }
}
