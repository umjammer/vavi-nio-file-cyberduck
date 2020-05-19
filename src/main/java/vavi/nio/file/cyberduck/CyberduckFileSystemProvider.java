/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import com.github.fge.filesystem.provider.FileSystemProviderBase;


/**
 * CyberduckFileSystemProvider.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
public final class CyberduckFileSystemProvider extends FileSystemProviderBase {

    public static final String PARAM_ALIAS = "alias";

    public CyberduckFileSystemProvider() {
        super(new CyberduckFileSystemRepository());
    }
}
