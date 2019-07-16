/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.nio.file.FileStore;

import com.github.fge.filesystem.attributes.FileAttributesFactory;
import com.github.fge.filesystem.filestore.FileStoreBase;

import ch.cyberduck.core.Session;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.Quota;


/**
 * A simple Cyberduck {@link FileStore}
 *
 * <p>
 * This makes use of information available in {@link Quota}.
 * Information is computed in "real time".
 * </p>
 */
public final class CyberduckFileStore extends FileStoreBase {

    private final Session<?> session;

    /**
     * Constructor
     *
     * @param session the (valid) OneDrive client to use
     */
    public CyberduckFileStore(final Session<?> session, final FileAttributesFactory factory) {
        super("cyberduck", factory, false);
        this.session = session;
    }

    /**
     * Returns the size, in bytes, of the file store.
     *
     * @return the size of the file store, in bytes
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public long getTotalSpace() throws IOException {
        final Quota.Space quota = getQuota();
        return quota == null ? 0 : quota.available + quota.used;
    }

    /**
     * Returns the number of bytes available to this Java virtual machine on the
     * file store.
     * <p>
     * The returned number of available bytes is a hint, but not a
     * guarantee, that it is possible to use most or any of these bytes. The
     * number of usable bytes is most likely to be accurate immediately
     * after the space attributes are obtained. It is likely to be made
     * inaccurate
     * by any external I/O operations including those made on the system outside
     * of this Java virtual machine.
     *
     * @return the number of bytes available
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public long getUsableSpace() throws IOException {
        final Quota.Space quota = getQuota();
        if (quota == null) {
            return 0;
        } else {
            return quota.available;
        }
    }

    /**
     * Returns the number of unallocated bytes in the file store.
     * <p>
     * The returned number of unallocated bytes is a hint, but not a
     * guarantee, that it is possible to use most or any of these bytes. The
     * number of unallocated bytes is most likely to be accurate immediately
     * after the space attributes are obtained. It is likely to be
     * made inaccurate by any external I/O operations including those made on
     * the system outside of this virtual machine.
     *
     * @return the number of unallocated bytes
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public long getUnallocatedSpace() throws IOException {
        return 0;
    }

    /** */
    private Quota.Space cache; // TODO refresh

    /** */
    private Quota.Space getQuota() throws IOException {
        if (cache != null) {
            return cache;
        } else {
            try {
                cache = session._getFeature(Quota.class).get();
                return cache;
            } catch (BackgroundException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
