#Stage 1
# initialize build and set base image for first stage
FROM maven:3.6.3-adoptopenjdk-11 as stage1
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
WORKDIR /opt/demo
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn install -Dmaven.test.skip=true -am —offline
#Stage 2
# set base image for second stage
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

# set deployment directory
WORKDIR /opt/demo
# copy over the built artifact from the maven image
COPY --from=stage1 /opt/demo/target/a-2_automation_system-0.0.1-SNAPSHOT.jar /opt/demo/
CMD ["java", "-jar", "a-2_automation_system-0.0.1-SNAPSHOT.jar"]
