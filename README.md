# AMT_project
This is a simple docker - java EE application for our AMT course at HEIG-VD.

The purpose is to learn how multi-tier applications work and how to build them using various components (Servlets, JSP, Beans, DAO, ...) to produce a well-structured app.

In this project, a user is able to create an account, login and see various informations about his account. A REST API can be used in order to do interact easily and quickly with the various informations stored in the database.

## Deploying the app
Download/clone the repo, `cd` into it and just type `./deploy.sh`.

The app should be running at `localhost:9090/AMT_project/`.

### Detailed commands

If for any reason you prefer to do it manually instead of running the script, you can do the following:

#### Build the app
1. Clone or download the repo.
2. Go to the root of the directory and enter `mvn compile war:war`.

#### Deploy the app
Still at the root of the directory, enter the following commands:

1. `cp target/AMT_project.war images/wildfly/`
2. `docker-compose up --build`

## REST API

The various routes of our API are defined on our [wiki page](https://github.com/BenjaminSchubert/AMT_project/wiki/REST-API).
