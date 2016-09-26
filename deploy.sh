#!/usr/bin/sh

set -e

# build
mvn compile war:war

# deploy
cp target/lab01-1.0-SNAPSHOT.war images/wildfly/
docker-compose up --build
