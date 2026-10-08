/*
 * Copyright (c) 2026 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.cyberduck;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.config.keys.writer.openssh.OpenSSHKeyEncryptionContext;
import org.apache.sshd.common.config.keys.writer.openssh.OpenSSHKeyPairResourceWriter;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;


/**
 * A temporary sftp server for tests.
 * <p>
 * The server is rooted at a temporary directory and accepts only public key authentication.
 * A client key pair is generated and the private key is written as a passphrase encrypted
 * OpenSSH key file, as same as a real environment.
 * </p>
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2026-10-08 nsano initial version <br>
 */
class SftpTestServer implements AutoCloseable {

    static final String USER = "tester";
    static final String PASSPHRASE = "test passphrase";

    private final Path base;
    private final Path root;
    private final Path keyPath;
    private final SshServer sshd;

    SftpTestServer() throws IOException, GeneralSecurityException {
        base = Files.createTempDirectory("sftp-test-");
        root = Files.createDirectory(base.resolve("root"));

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair clientKey = generator.generateKeyPair();

        keyPath = base.resolve("id_rsa");
        OpenSSHKeyEncryptionContext encryption = new OpenSSHKeyEncryptionContext();
        encryption.setPassword(PASSPHRASE);
        encryption.setCipherName("AES");
        encryption.setCipherMode("CTR");
        encryption.setCipherType("256");
        try (OutputStream os = Files.newOutputStream(keyPath)) {
            OpenSSHKeyPairResourceWriter.INSTANCE.writePrivateKey(clientKey, USER, encryption, os);
        }

        sshd = SshServer.setUpDefaultServer();
        sshd.setHost("127.0.0.1");
        sshd.setPort(0);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(base.resolve("host.ser")));
        sshd.setPasswordAuthenticator(null);
        sshd.setKeyboardInteractiveAuthenticator(null);
        sshd.setPublickeyAuthenticator((username, key, session) ->
                USER.equals(username) && KeyUtils.compareKeys(key, clientKey.getPublic()));
        sshd.setSubsystemFactories(List.of(new SftpSubsystemFactory()));
        sshd.setFileSystemFactory(new VirtualFileSystemFactory(root));
        sshd.start();
    }

    /** the directory served as "/" */
    Path getRoot() {
        return root;
    }

    /** @return "cyberduck:sftp://..." uri for this server */
    URI getUri() {
        return URI.create(String.format("cyberduck:sftp://%s@127.0.0.1:%d/?keyPath=%s&passphrase=%s",
                USER,
                sshd.getPort(),
                URLEncoder.encode(keyPath.toString(), StandardCharsets.UTF_8),
                URLEncoder.encode(PASSPHRASE, StandardCharsets.UTF_8)));
    }

    @Override
    public void close() throws IOException {
        try {
            sshd.stop(true);
        } finally {
            try (Stream<Path> s = Files.walk(base)) {
                s.sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
            }
        }
    }
}
