#!/usr/bin/env sh

set -e

# build
mvn compile war:war

# deploy
cp target/AMT_project.war images/wildfly/standalone/deployments/
docker-compose up --build
