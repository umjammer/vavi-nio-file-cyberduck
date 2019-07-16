/*
 * Copyright (c) 2016 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.github.fge.filesystem.driver.FileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemRepositoryBase;

import ch.cyberduck.core.Session;


/**
 * CyberduckFileSystemRepository.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2016/03/11 umjammer initial version <br>
 */
@ParametersAreNonnullByDefault
public final class CyberduckFileSystemRepository extends FileSystemRepositoryBase {

    /** */
    public CyberduckFileSystemRepository() {
        super("cyberduck", new CyberduckFileSystemFactoryProvider());
    }

    /**
     * TODO root from uri
     * @throws NoSuchElementException required values are not in env
     */
    @Nonnull
    @Override
    public FileSystemDriver createDriver(final URI uri, final Map<String, ?> env) throws IOException {
        Map<String, String> params = getParamsMap(uri);
        if (!params.containsKey(CyberduckFileSystemProvider.PARAM_ID)) {
            throw new NoSuchElementException("uri not contains a param " + CyberduckFileSystemProvider.PARAM_ID);
        }
        final String email = params.get(CyberduckFileSystemProvider.PARAM_ID);

        if (!env.containsKey("session")) {
            throw new NoSuchElementException("app credential not contains a param " + "session");
        }
        Session<?> session = Session.class.cast(env.get("session"));

        final CyberduckFileStore fileStore = new CyberduckFileStore(session, factoryProvider.getAttributesFactory());
        return new CyberduckFileSystemDriver(fileStore, factoryProvider, session, env);
    }
}
