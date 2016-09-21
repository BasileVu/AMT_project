# AMT-LAB01
This is a simple docker - java EE application for our AMT course in HEIG-VD

# Build the app
1. Clone or download the repo
2. At the root of the directory (AMT-LAB01), enter `mvn compile war:war`

# Deploy the app
1. `cp target/lab01-1.0-SNAPSHOT.war images/wildfly/`
2. `docker-compose up --build`
