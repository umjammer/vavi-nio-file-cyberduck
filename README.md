[![Release](https://jitpack.io/v/umjammer/vavi-nio-file-cyberduck.svg)](https://jitpack.io/#umjammer/vavi-nio-file-cyberduck)
[![Java CI](https://github.com/umjammer/vavi-nio-file-cyberduck/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/vavi-nio-file-cyberduck/actions)
[![CodeQL](https://github.com/umjammer/vavi-nio-file-cyberduck/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/umjammer/vavi-nio-file-cyberduck/actions/workflows/codeql-analysis.yml)
![Java](https://img.shields.io/badge/Java-17-b07219)
[![Parent](https://img.shields.io/badge/Parent-vavi--apps--fuse-pink)](https://github.com/umjammer/vavi-apps-fuse)

# vavi-nio-file-cyberduck

<img alt="CyberDuck" src="https://github.com/umjammer/vavi-nio-file-cyberduck/assets/493908/3f341105-9775-4821-b0dc-072abdd4f284" width=120 /><sub><a href="https://iterate.ch/">© iterate GmbH</a></sub>

A Java NIO FileSystem implementation over [Cyberduck](https://github.com/iterate-ch/cyberduck).

## Status

| fs                     | list | upload | download | copy | move | rm | mkdir | cache | watch | library |
|------------------------|------|--------|----------|------|------|----|-------|-------|-------|---------|
| cyberduck (webdav ssl) | ✅   | ✅    | ✅       | ✅   | ✅  | ✅ | ✅   | ✅   |       | [cyberduck.webdav](https://github.com/iterate-ch/cyberduck/) |
| cyberduck (sftp)       | ✅   | ✅    | ✅       | ✅   | ✅  | ✅ | ✅   | ✅   |       | [cyberduck.ssh](https://github.com/iterate-ch/cyberduck/) |

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

### webdav hosting service for test

 * [boxdab](https://www.box.com/) ... ~~closed~~ ~~still working~~ works, but got error. closed? (Aug 2022)
 * [4shared](https://www.4shared.com/) ... limited term, folder deletion doesn't work??? -> banned by ltns 
 * [pCloud](https://my.pcloud.com/) ... seems good (2021-11-03) -> banned by ltns
 * [DriveHQ](https://www.drivehq.com/) ... seems good for testing (2022-09-21) 🎯

## TODO

 * ~~"log4j -> jul" delegation~~
   * ~~log4j-jul ... doesn't work well~~ log4j-jul is "jul -> log4j"
 * log4j -> XXX (cyberduck start using log4j for some part)
   * https://github.com/OpenSourceNZ/cloud-log4j2-over-slf4j-appender
     * log4j version conflict 

## Warning

this project includes dummy `org.slf4j.bridge.SLF4JBridgeHandler` for cyberduck's not loosely coupled implementation.
if you want to use this project with the package `org.slf4j:jul-to-slf4j`, ~~you might get some troubles~~
on java17 it works fine.