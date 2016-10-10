FROM jboss/wildfly:10.1.0.Final

LABEL Wildfly Server

MAINTAINER Benjamin Schubert <benjamin.schubert@heig-vd.ch>

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

COPY AMT_project.war /opt/jboss/wildfly/standalone/deployments
