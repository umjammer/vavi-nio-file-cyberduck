/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import ch.cyberduck.core.shared.DefaultHomeFinderService;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.ui.browser.SearchFilter;
import com.github.fge.filesystem.driver.DoubleCachedFileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemFactoryProvider;
import org.apache.commons.io.IOUtils;
import vavi.nio.file.Util;
import vavi.util.Debug;

import static vavi.nio.file.Util.toFilenameString;


/**
 * CyberduckFileSystemDriver.
 *
 * TODO does file cache really needed?
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
public final class CyberduckFileSystemDriver extends DoubleCachedFileSystemDriver<ch.cyberduck.core.Path> {

    private Session<?> session;

    public CyberduckFileSystemDriver(final FileStore fileStore,
            FileSystemFactoryProvider provider,
            Session<?> session,
            Map<String, ?> env) throws IOException {
        super(fileStore, provider);
        this.session = session;
        setEnv(env);
    }

    @Override
    protected String getFilenameString(ch.cyberduck.core.Path entry) {
        return entry.getName();
    }

    @Override
    protected boolean isFolder(ch.cyberduck.core.Path entry) {
        return entry.isDirectory();
    }

    @Override
    protected ch.cyberduck.core.Path getRootEntry(Path root) throws IOException {
        try {
            return new DefaultHomeFinderService(session).find();
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    /**
     * TODO cyberduck might have own cache
     * @param parentEntry should be set, not null.
     */
    @Override
    protected ch.cyberduck.core.Path getEntry(ch.cyberduck.core.Path parentEntry, Path path)throws IOException {
        try {
Debug.println(Level.FINE, "parentEntry: " + parentEntry.getAbsolute());
            Search search = session._getFeature(Search.class);
            // TODO SearchFilter is not exact match, so entries might be > 1
            AttributedList<ch.cyberduck.core.Path> entries = search.search(parentEntry, new SearchFilter(toFilenameString(path)), new DisabledListProgressListener());
            if (!entries.isEmpty()) {
//Debug.println("entries: " + entries.size());
                return entries.get(0);
            } else {
                return null;
            }
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    };

    @Override
    protected InputStream downloadEntryImpl(ch.cyberduck.core.Path entry, Path path, Set<? extends OpenOption> options) throws IOException {
        try {
            Read read = session._getFeature(Read.class);
            // this is the best performance
            return read.read(entry, new TransferStatus(), new DisabledConnectionCallback());
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected OutputStream uploadEntry(ch.cyberduck.core.Path parentEntry, Path path, Set<? extends OpenOption> options) throws IOException {
        CyberduckUploadOption uploadOption = Util.getOneOfOptions(CyberduckUploadOption.class, options);
        if (uploadOption != null) {
            // java.nio.file is highly abstracted, so here source information is lost.
            // but cyberduck requires content length for upload? (TODO).
            // so reluctantly we provide {@link CyberduckUploadOption} for {@link java.nio.file.Files#copy} options.
            Path source = uploadOption.getSource();
Debug.println("upload w/ option: " + source);
            return new Util.OutputStreamForUploading(uploadEntry(parentEntry, path, Files.size(source))) {
                @Override
                protected void onClosed() throws IOException {
                    ch.cyberduck.core.Path newEntry = getEntry(parentEntry, path);
                    updateEntry(path, newEntry);
                }
            };
        } else {
Debug.println("upload w/o option");
            return new Util.OutputStreamForUploading() {
                @Override
                protected void onClosed() throws IOException {
                    InputStream is = getInputStream();
                    OutputStream os = uploadEntry(parentEntry, path, is.available());
                    IOUtils.copyLarge(is, os);
                    is.close();
                    os.close();

                    ch.cyberduck.core.Path newEntry = getEntry(parentEntry, path);
                    updateEntry(path, newEntry);
                }
            };
        }
    }

    /** */
    private OutputStream uploadEntry(ch.cyberduck.core.Path parentEntry, Path path, long size) throws IOException {
        try {
            Write<?> write = session._getFeature(Write.class);
            TransferStatus status =  new TransferStatus();
            status.setOffset(0);
            status.setLength(size); // TODO seems to work w/o size (sftp)
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(parentEntry, toFilenameString(path), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            // this is best performance
            return new BufferedOutputStream(write.write(preEntry, status, new DisabledConnectionCallback()));
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected List<ch.cyberduck.core.Path> getDirectoryEntries(ch.cyberduck.core.Path dirEntry, Path dir) throws IOException {
        try {
            ListService listService = session._getFeature(ListService.class);
            AttributedList<ch.cyberduck.core.Path> children = listService.list(dirEntry, new DisabledListProgressListener());
            return StreamSupport.stream(children.spliterator(), false).collect(Collectors.toList());
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected ch.cyberduck.core.Path createDirectoryEntry(ch.cyberduck.core.Path parentEntry, Path dir) throws IOException {
        // TODO: how to diagnose?
        try {
            Directory<?> directory = session._getFeature(Directory.class);
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(parentEntry, toFilenameString(dir), EnumSet.of(ch.cyberduck.core.Path.Type.directory));
            ch.cyberduck.core.Path newEntry = directory.mkdir(preEntry, null, new TransferStatus());
            return newEntry;
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected boolean hasChildren(ch.cyberduck.core.Path dirEntry, Path dir) throws IOException {
        return getDirectoryEntries(dirEntry, dir).size() > 0;        
    }

    @Override
    protected void removeEntry(ch.cyberduck.core.Path entry, Path path) throws IOException {
        try {
            // TODO: unknown what happens when a move operation is performed
            // and the target already exists
            Delete delete = session._getFeature(Delete.class);
            delete.delete(Collections.singletonList(entry), new DisabledConnectionCallback(), new Delete.DisabledCallback());
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected ch.cyberduck.core.Path copyEntry(ch.cyberduck.core.Path sourceEntry, ch.cyberduck.core.Path targetParentEntry, Path source, Path target, Set<CopyOption> options) throws IOException {
        try {
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            Copy copy = session._getFeature(Copy.class);
            return copy.copy(sourceEntry, preEntry, new TransferStatus(), new DisabledConnectionCallback());
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected ch.cyberduck.core.Path moveEntry(ch.cyberduck.core.Path sourceEntry, ch.cyberduck.core.Path targetParentEntry, Path source, Path target, boolean targetIsParent) throws IOException {
        try {
            ch.cyberduck.core.Path preEntry;
            if (targetIsParent) {
                preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(source), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            } else {
                preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            }
            final Move move = session._getFeature(Move.class);
            // TODO why cannot use move() return like copy or rename
            move.move(sourceEntry, preEntry, new TransferStatus(), new Delete.DisabledCallback(), new DisabledConnectionCallback());
            if (targetIsParent) {
                return getEntry(targetParentEntry, target.resolve(source.getFileName())); // TODO
            } else {
                return getEntry(targetParentEntry, target); // TODO
            }
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected ch.cyberduck.core.Path moveFolderEntry(ch.cyberduck.core.Path sourceEntry, ch.cyberduck.core.Path targetParentEntry, Path source, Path target, boolean targetIsParent) throws IOException {
        try {
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            Move move = session._getFeature(Move.class);
            // TODO why cannot use move() return like copy or rename
            move.move(sourceEntry, preEntry, new TransferStatus(), new Delete.DisabledCallback(), new DisabledConnectionCallback());
            ch.cyberduck.core.Path newEntry = getEntry(null, target); // TODO
//Debug.println(newEntry.toAbsolutePath().getParent() + "/" + newEntry.getName() + ", " + newEntry.isDirectory());
            return newEntry;
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected ch.cyberduck.core.Path renameEntry(ch.cyberduck.core.Path sourceEntry, ch.cyberduck.core.Path targetParentEntry, Path source, Path target) throws IOException {
        try {
            ch.cyberduck.core.Path preEntry = new ch.cyberduck.core.Path(targetParentEntry, toFilenameString(target), EnumSet.of(ch.cyberduck.core.Path.Type.file));
            Move move = session._getFeature(Move.class);
            return move.move(sourceEntry, preEntry, new TransferStatus(), new Delete.DisabledCallback(), new DisabledConnectionCallback());
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            session.close();
        } catch (BackgroundException e) {
            throw new IOException(e);
        }
    }
}
