[![Release](https://jitpack.io/v/umjammer/vavi-nio-file-cyberduck.svg)](https://jitpack.io/#umjammer/vavi-nio-file-cyberduck) [![Java CI with Maven](https://github.com/umjammer/vavi-nio-file-cyberduck/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/umjammer/vavi-nio-file-cyberduck/actions) [![Parent](https://img.shields.io/badge/Parent-vavi--apps--fuse-pink)](https://github.com/umjammer/vavi-apps-fuse)

# vavi-nio-file-cyberduck

A Java NIO FileSystem implementation over Cyberduck.

## Status

| fs                     | list | upload | download | copy | move | rm | mkdir | cache | watch | library |
|------------------------|------|--------|----------|------|------|----|-------|-------|-------|---------|
| cyberduck (webdav ssl) | ✅   | ✅    | ✅       | ✅   | ✅  | ✅ | ✅   | ✅   |       | [cyberduck.webdav](https://github.com/iterate-ch/cyberduck/webdav) |
| cyberduck (sftp      ) | ✅   | ✅    | ✅       | ✅   | ✅  | ✅ | ✅   | ✅   |       | [cyberduck.ssh](https://github.com/iterate-ch/cyberduck/ssh) |

## Install

https://jitpack.io/#umjammer/vavi-nio-file-cyberduck

## Usage

prepare a property file.

```shell
$ cat ${HOME}/.vavifuse/credentials.properties
cyberduck.username.webdav=your_user_name
cyberduck.password.webdav=your_password
cyberduck.host.webdav=localhost
cyberduck.port.webdav=8443
```

### Sample

https://github.com/umjammer/vavi-nio-file-cyberduck/blob/master/src/test/java/vavi/nio/file/cyberduck/Test1.java

## Test

```shell
$ cp local.properties.sample local.properties
$ vi local.properties
test.webdav.account=your_webdav@account.com
test.webdav.password=your_webdav_password
test.webdav.host=your_webdav_host
test.webdav.port=your_webdav_port
test.webdav.path=/your_webdav_path
$ mvn test
```
