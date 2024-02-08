/*
 * Copyright (c) 2020 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.nio.file.CopyOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;


/**
 * CyberduckUploadOption.
 * <p>
 * for large file.
 * <p>
 * in {@link com.github.fge.filesystem.options.FileSystemOptionsFactory},
 * instances of this class are compared by contains method. so i override
 * {@link #equals(Object)} and {@link #hashCode()}.
 * </p>
 * TODO CopyOption doesn't work.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/05/31 umjammer initial version <br>
 */
public class CyberduckUploadOption implements OpenOption, CopyOption {

    /** */
    private static final long serialVersionUID = 4268029208933599734L;

    private Path source;

    /** */
    public CyberduckUploadOption(Path source) {
        this.source = source;
    }

    /** */
    public Path getSource() {
        return source;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof CyberduckUploadOption; // TODO ad-hoc
    }

    @Override
    public int hashCode() {
        return Long.hashCode(serialVersionUID); // TODO ad-hoc
    }
}

/* */
