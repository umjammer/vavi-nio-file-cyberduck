[![Release](https://jitpack.io/v/umjammer/vavi-nio-file-cyberduck.svg)](https://jitpack.io/#umjammer/vavi-nio-file-cyberduck) [![Actions Status](https://github.com/umjammer/vavi-nio-file-cyberduck/workflows/Java%20CI/badge.svg)](https://github.com/umjammer/vavi-nio-file-cyberduck/actions) [![Parent](https://img.shields.io/badge/Parent-vavi--apps--fuse-pink)](https://github.com/umjammer/vavi-apps-fuse)

# vavi-nio-file-cyberduck

A Java NIO FileSystem implementation over Cyberduck.

## Status

| fs                     | list | upload | download | copy | move | rm | mkdir | cache | watch | library |
|------------------------|------|--------|----------|------|------|----|-------|-------|-------|---------|
| cyberduck (webdav ssl) | ✅    | ✅     | ✅       | ✅   | ✅   | ✅ | ✅    | ✅    |       | [cyberduck](https://github.com/iterate-ch/cyberduck) |

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
