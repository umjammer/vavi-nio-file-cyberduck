/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.io.IOUtils;

import com.github.fge.filesystem.driver.UnixLikeFileSystemDriverBase;
import com.github.fge.filesystem.exceptions.IsDirectoryException;
import com.github.fge.filesystem.provider.FileSystemFactoryProvider;

import vavi.nio.file.Cache;
import vavi.nio.file.Util;
import vavi.nio.file.Util.OutputStreamForUploading;
import vavi.util.Debug;

import static vavi.nio.file.Util.toFilenameString;
import static vavi.nio.file.Util.toPathString;

import ch.cyberduck.core.AttributedList;
import ch.cyberduck.core.DisabledConnectionCallback;
import ch.cyberduck.core.DisabledListProgressListener;
import ch.cyberduck.core.ListService;
import ch.cyberduck.core.Session;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.Copy;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.features.Directory;
import ch.cyberduck.core.features.Move;
import ch.cyberduck.core.features.Read;
import ch.cyberduck.core.features.Search;
import ch.cyberduck.core.features.Write;
import ch.cyberduck.core.io.StatusOutputStream;
import ch.cyberduck.core.shared.DefaultHomeFinderService;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.ui.browser.SearchFilter;


/**
 * CyberduckFileSystemDriver.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
@ParametersAreNonnullByDefault
public final class CyberduckFileSystemDriver extends UnixLikeFileSystemDriverBase {

    private final Session<?> session;
    private boolean ignoreAppleDouble = false;

    @SuppressWarnings("unchecked")
    public CyberduckFileSystemDriver(final FileStore fileStore,
            final FileSystemFactoryProvider provider,
            final Session<?> session,
            final Map<String, ?> env) {
        super(fileStore, provider);
        this.session = session;
        ignoreAppleDouble = (Boolean) ((Map<String, Object>) env).getOrDefault("ignoreAppleDouble", Boolean.FALSE);
//System.err.println("ignoreAppleDouble: " + ignoreAppleDouble);
    }

    /** TODO cyberduck has cache? */
    private Cache<ch.cyberduck.core.Path> cache = new Cache<ch.cyberduck.core.Path>() {
        /**
         * TODO when the parent is not cached
         * @see #ignoreAppleDouble
         * @throws NoSuchFileException must be thrown when the path is not found in this cache
         */
        public ch.cyberduck.core.Path getEntry(Path path) throws IOException {
            if (cache.containsFile(path)) {
                return cache.getFile(path);
            } else {
                if (ignoreAppleDouble && path.getFileName() != null && Util.isAppleDouble(path)) {
                    throw new NoSuchFileException("ignore apple double file: " + path);
                }

                try {
//                    String pathString = toPathString(path);
//Debug.println("path: " + pathString);
                    ch.cyberduck.core.Path entry;
                    if (path.getNameCount() == 0) {
                        entry = new DefaultHomeFinderService(session).find();
                    } else {
                        ch.cyberduck.core.Path parentEntry = getEntry(path.getParent());
                        Search search = session._getFeature(Search.class);
                        AttributedList<ch.cyberduck.core.Path> entries = search.search(parentEntry, new SearchFilter(toFilenameString(path)), new DisabledListProgressListener());
                        if (!entries.isEmpty()) {
Debug.println("entries: " + entries.size());
                            entry = entries.get(0);
                        } else {
                            if (cache.containsFile(path)) {
                                cache.removeEntry(path);
                            }
                            throw new NoSuchFileException(path.toString());
                        }
                    }
                    cache.putFile(path, entry);
                    return entry;
                } catch (BackgroundException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

    @Nonnull
    @Override
    public InputStream newInputStream(final Path path, final Set<? extends OpenOption> options) throws IOException {
        final ch.cyberduck.core.Path entry = cache.getEntry(path);

        // TODO: metadata driver
        if (entry.isDirectory()) {
            throw new IsDirectoryException(path.toString());
        }

        try {
            final Read read = session._getFeature(Read.class);
            return read.read(entry, new TransferStatus(), new DisabledConnectionCallback());
        } catch (BackgroundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * fuse からだと
     * <ol>
     *  <li>create -> newByteChannel
     *  <li>flush -> n/a
     *  <li>lock -> n/a
     *  <li>release -> byteChannel.close
     * </ol>
     * と呼ばれる <br/>
     * 元のファイルが取れない... <br/>
     * 書き込みの指示もない...
     * <p>
     * nio.file からだと
     * <pre>
     *  newOutputStream -> write(2)
     * </pre>
     */
    @Nonnull
    @Override
    public OutputStream newOutputStream(final Path path, final Set<? extends OpenOption> options) throws IOException {
        try {
            ch.cyberduck.core.Path entry = cache.getEntry(path);

            if (entry.isDirectory()) {
                throw new IsDirectoryException(path.toString());
            } else {
                throw new FileAlreadyExistsException(path.toString());
            }
        } catch (NoSuchFileException e) {
Debug.println("newOutputStream: " + e.getMessage());
//new Exception("*** DUMMY ***").printStackTrace();
        }

        return new OutputStreamForUploading() {
            @Override
            protected void upload(InputStream is) throws IOException {
                try {
                    final Write<?> write = session._getFeature(Write.class);
                    TransferStatus status =  new TransferStatus();
                    status.setOffset(0);
                    status.setLength(is.available());
                    ch.cyberduck.core.Path parentEntry = cache.getEntry(path.getParent());
                    ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(parentEntry, toFilenameString(path), EnumSet.of(ch.cyberduck.core.Path.Type.file));
                    StatusOutputStream<?> os = write.write(preEntry, status, new DisabledConnectionCallback());
                    IOUtils.copyLarge(is, os);
                    is.close();
                    os.close();
                    ch.cyberduck.core.Path newEntry = cache.getEntry(path);
                    cache.addEntry(path, newEntry);
                } catch (BackgroundException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

    @Nonnull
    @Override
    public DirectoryStream<Path> newDirectoryStream(final Path dir,
                                                    final DirectoryStream.Filter<? super Path> filter) throws IOException {
        return Util.newDirectoryStream(getDirectoryEntries(dir), filter);
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path,
                                              Set<? extends OpenOption> options,
                                              FileAttribute<?>... attrs) throws IOException {
        if (options != null && (options.contains(StandardOpenOption.WRITE) || options.contains(StandardOpenOption.APPEND))) {
            return new Util.SeekableByteChannelForWriting(newOutputStream(path, options)) {
                @Override
                protected long getLeftOver() throws IOException {
                    long leftover = 0;
                    if (options.contains(StandardOpenOption.APPEND)) {
                        ch.cyberduck.core.Path entry = cache.getEntry(path);
                        if (entry != null && entry.attributes().getSize() >= 0) {
                            leftover = entry.attributes().getSize();
                        }
                    }
                    return leftover;
                }

                @Override
                public void close() throws IOException {
System.out.println("SeekableByteChannelForWriting::close");
                    if (written == 0) {
                        // TODO no mean
System.out.println("SeekableByteChannelForWriting::close: scpecial: " + path);
                        java.io.File file = new java.io.File(toPathString(path));
                        FileInputStream fis = new FileInputStream(file);
                        FileChannel fc = fis.getChannel();
                        fc.transferTo(0, file.length(), this);
                        fis.close();
                    }
                    super.close();
                }
            };
        } else {
            ch.cyberduck.core.Path entry = cache.getEntry(path);
            if (entry.isDirectory()) {
                throw new IsDirectoryException(path.toString());
            }
            return new Util.SeekableByteChannelForReading(newInputStream(path, null)) {
                @Override
                protected long getSize() throws IOException {
                    return entry.attributes().getSize();
                }
            };
        }
    }

    @Override
    public void createDirectory(final Path dir, final FileAttribute<?>... attrs) throws IOException {
        ch.cyberduck.core.Path parentEntry = cache.getEntry(dir.getParent());

        // TODO: how to diagnose?
        try {
            final Directory<?> directory = session._getFeature(Directory.class);
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(parentEntry, toFilenameString(dir), EnumSet.of(ch.cyberduck.core.Path.Type.directory));
            ch.cyberduck.core.Path newEntry = directory.mkdir(preEntry, null, new TransferStatus());
            cache.addEntry(dir, newEntry);
        } catch (BackgroundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(final Path path) throws IOException {
        removeEntry(path);
    }

    @Override
    public void copy(final Path source, final Path target, final Set<CopyOption> options) throws IOException {
        if (cache.existsEntry(target)) {
            if (options != null && options.stream().anyMatch(o -> o.equals(StandardCopyOption.REPLACE_EXISTING))) {
                removeEntry(target);
            } else {
                throw new FileAlreadyExistsException(target.toString());
            }
        }
        copyEntry(source, target);
    }

    @Override
    public void move(final Path source, final Path target, final Set<CopyOption> options) throws IOException {
        if (cache.existsEntry(target)) {
            if (cache.getEntry(target).isDirectory()) {
                if (options != null && options.stream().anyMatch(o -> o.equals(StandardCopyOption.REPLACE_EXISTING))) {
                    // replace the target
                    if (cache.getChildCount(target) > 0) {
                        throw new DirectoryNotEmptyException(target.toString());
                    } else {
                        removeEntry(target);
                        moveEntry(source, target, false);
                    }
                } else {
                    // move into the target
                    // TODO SPEC is FileAlreadyExistsException ?
                    moveEntry(source, target, true);
                }
            } else {
                if (options != null && options.stream().anyMatch(o -> o.equals(StandardCopyOption.REPLACE_EXISTING))) {
                    removeEntry(target);
                    moveEntry(source, target, false);
                } else {
                    throw new FileAlreadyExistsException(target.toString());
                }
            }
        } else {
            if (source.getParent().equals(target.getParent())) {
                // rename
                renameEntry(source, target);
            } else {
                moveEntry(source, target, false);
            }
        }
    }

    /**
     * Check access modes for a path on this filesystem
     * <p>
     * If no modes are provided to check for, this simply checks for the
     * existence of the path.
     * </p>
     *
     * @param path the path to check
     * @param modes the modes to check for, if any
     * @throws IOException filesystem level error, or a plain I/O error
     *                     if you use this with javafs (jnr-fuse), you should throw {@link NoSuchFileException} when the file not found.
     * @see FileSystemProvider#checkAccess(Path, AccessMode...)
     */
    @Override
    public void checkAccess(final Path path, final AccessMode... modes) throws IOException {
        final ch.cyberduck.core.Path entry = cache.getEntry(path);

        if (!entry.isFile()) {
            return;
        }

        // TODO: assumed; not a file == directory
        for (final AccessMode mode : modes) {
            if (mode == AccessMode.EXECUTE) {
                throw new AccessDeniedException(path.toString());
            }
        }
    }

    @Override
    public void close() throws IOException {
        try {
            session.close();
        } catch (BackgroundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @throws IOException you should throw {@link NoSuchFileException} when the file not found.
     */
    @Nonnull
    @Override
    public Object getPathMetadata(final Path path) throws IOException {
        return cache.getEntry(path);
    }

    /** */
    private List<Path> getDirectoryEntries(Path dir) throws IOException {
        final ch.cyberduck.core.Path entry = cache.getEntry(dir);

        if (!entry.isDirectory()) {
//System.err.println(entry.name + ", " + entry.id + ", " + entry.hashCode());
            throw new NotDirectoryException(dir.toString());
        }

        List<Path> list = null;
        if (cache.containsFolder(dir)) {
            list = cache.getFolder(dir);
        } else {
            list = new ArrayList<>();

            try {
                final ListService listService = session._getFeature(ListService.class);
                AttributedList<ch.cyberduck.core.Path> children = listService.list(entry, new DisabledListProgressListener());

                for (final ch.cyberduck.core.Path child : children) {
                    Path childPath = dir.resolve(child.getName());
                    list.add(childPath);
//System.err.println("child: " + childPath.toRealPath().toString());

                    cache.putFile(childPath, child);
                }
                cache.putFolder(dir, list);
            } catch (BackgroundException e) {
                throw new IllegalStateException(e);
            }
        }

        return list;
    }

    /** */
    private void removeEntry(Path path) throws IOException {
        ch.cyberduck.core.Path entry = cache.getEntry(path);
        if (entry.isDirectory()) {
            if (cache.getChildCount(path) > 0) {
                throw new DirectoryNotEmptyException(path.toString());
            }
        }

        // TODO: unknown what happens when a move operation is performed
        // and the target already exists
        try {
            final Delete delete = session._getFeature(Delete.class);
            delete.delete(Arrays.asList(entry), new DisabledConnectionCallback(), new Delete.DisabledCallback());

            cache.removeEntry(path);
        } catch (BackgroundException e) {
            throw new IllegalStateException(e);
        }
    }

    /** */
    private void copyEntry(final Path source, final Path target) throws IOException {
        ch.cyberduck.core.Path sourceEntry = cache.getEntry(source);
        if (sourceEntry.isFile()) {
            try {
                ch.cyberduck.core.Path targetParentEntry = cache.getEntry(target.getParent());
                ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
                final Copy copy = session._getFeature(Copy.class);
                ch.cyberduck.core.Path newEntry = copy.copy(sourceEntry, preEntry, new TransferStatus(), new DisabledConnectionCallback());

                cache.addEntry(target, newEntry);
            } catch (BackgroundException e) {
                throw new IllegalStateException(e);
            }
        } else if (sourceEntry.isDirectory()) {
            // TODO java spec. allows empty folder
            throw new IsDirectoryException(source.toString());
        }
    }

    /**
     * @param targetIsParent if the target is folder
     */
    private void moveEntry(final Path source, final Path target, boolean targetIsParent) throws IOException {
        ch.cyberduck.core.Path sourceEntry = cache.getEntry(source);
        if (sourceEntry.isFile()) {
            try {
                ch.cyberduck.core.Path targetParentEntry = cache.getEntry(targetIsParent ? target : target.getParent());
                ch.cyberduck.core.Path preEntry;
                if (targetIsParent) {
                    preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(source), EnumSet.of(ch.cyberduck.core.Path.Type.file));
                } else {
                    preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
                }
                final Move move = session._getFeature(Move.class);
                // TODO why cannot use move() return like copy or rename
                move.move(sourceEntry, preEntry, new TransferStatus(), new Delete.DisabledCallback(), new DisabledConnectionCallback());
                cache.removeEntry(source);
                if (targetIsParent) {
                    ch.cyberduck.core.Path newEntry = cache.getEntry(target.resolve(source.getFileName())); // TODO
                    cache.addEntry(target.resolve(source.getFileName()), newEntry);
                } else {
                    ch.cyberduck.core.Path newEntry = cache.getEntry(target); // TODO
                    cache.addEntry(target, newEntry);
                }
            } catch (BackgroundException e) {
                throw new IllegalStateException(e);
            }
        } else if (sourceEntry.isDirectory()) {
            // TODO java spec. allows empty folder
            throw new IsDirectoryException("source can not be a folder: " + source);
        }
    }

    /** */
    private void renameEntry(final Path source, final Path target) throws IOException {
        ch.cyberduck.core.Path sourceEntry = cache.getEntry(source);
//Debug.println(sourceEntry.id + ", " + sourceEntry.name);

        try {
            ch.cyberduck.core.Path targetParentEntry = cache.getEntry(target.getParent());
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            final Move move = session._getFeature(Move.class);
            ch.cyberduck.core.Path patchedEntry = move.move(sourceEntry, preEntry, new TransferStatus(), new Delete.DisabledCallback(), new DisabledConnectionCallback());
            cache.removeEntry(source);
            cache.addEntry(target, patchedEntry);
        } catch (BackgroundException e) {
            throw new IllegalStateException(e);
        }
    }
}
