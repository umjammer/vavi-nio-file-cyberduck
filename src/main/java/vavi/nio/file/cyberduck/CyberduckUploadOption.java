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
 * </p>
 * TODO CopyOption doesn't work.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2020/05/31 umjammer initial version <br>
 */
public class CyberduckUploadOption implements OpenOption, CopyOption {

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
        return other != null && CyberduckUploadOption.class.isInstance(other); // TODO ad-hoc
    }

    @Override
    public int hashCode() {
        return Long.hashCode(4268029208933599734L); // TODO ad-hoc
    }
}

/* */
