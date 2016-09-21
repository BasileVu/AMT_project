# AMT-LAB01
This is a simple docker - java EE application for our AMT course in HEIG-VD

# Build the app
1. Clone or download the repo.
2. Go to the root of the directory and enter `mvn compile war:war`.

# Deploy the app
Still at the root of the directory, enter the following commands:

1. `cp target/lab01-1.0-SNAPSHOT.war images/wildfly/`
2. `docker-compose up --build`

The app should be running at `localhost:9090/lab01-1.0-SNAPSHOT`.
