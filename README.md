# Archive Encrypter

This is a small mojo which encrypts a given file, intentionally a JAR or WAR file.
The purpose is to mave the archive over a channel that enforces an AV Scan, which prohibits Java/Class/binaries due to their possible harmfulness. 
If the archive is encrypted the AV scan passes.

## Dependency
```xml
<plugin>
    <groupId>group.msg</groupId>
    <artifactId>archiveEncrypter</artifactId>
    <version>1.0</version>
</plugin>
```

## Run the mojo
The mojo requires a project to run, then run the mojo by:

```bash
$ mvn archiveEncrypter:encrypt
```

## Configuration

These are the possible configurations, together with their default value.
```xml
<configuration>
    <filename>${project.artifactId}-${project.version}.war</filename>
    <password></password> <!-- Defauts to '0815' -->
</configuration>
```

## Classic programmer paintings

![Examing the Sense of this](http://68.media.tumblr.com/4749065338c5a3cec6724cc2e5fb28e1/tumblr_o5pb9bPbgQ1ugyavxo1_1280.jpg)

“Engineer examines the security requirements”

Viktor Vasnetsov
1878