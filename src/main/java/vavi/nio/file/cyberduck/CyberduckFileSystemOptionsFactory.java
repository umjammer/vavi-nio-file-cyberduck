/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.nio.file.LinkOption;

import com.github.fge.filesystem.options.FileSystemOptionsFactory;


/**
 * CyberduckFileSystemOptionsFactory.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
public class CyberduckFileSystemOptionsFactory extends FileSystemOptionsFactory {

    public CyberduckFileSystemOptionsFactory() {
        addLinkOption(LinkOption.NOFOLLOW_LINKS);
    }
}

/* */
