#!/bin/sh
# shellcheck disable=SC2164
# shellcheck disable=SC1012
# shellcheck disable=SC1001
cd "$1"
mvn deploy
mvn release:clean
