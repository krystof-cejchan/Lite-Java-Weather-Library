#!/bin/sh
gpg --list-secret-keys --keyid-format LONG
# https://maven.apache.org/plugins/maven-gpg-plugin/
# https://maven.apache.org/plugins/maven-gpg-plugin/examples/deploy-signed-artifacts.html
# https://maven.apache.org/plugins/maven-gpg-plugin/usage.html
mvn clean deploy -Dgpg.skip -Darguments=-Dgpg.skip
# mvn clean deploy
