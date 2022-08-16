#Stage 1
# initialize build and set base image for first stage
FROM openjdk:11-slim-buster as stage1
# set working directory
WORKDIR /opt/demo
# copy just pom.xml
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
# go-offline using the pom.xml
RUN ./mvnw -B dependency:go-offline                          
COPY ./src ./src
RUN ./mvnw -B package -Dmaven.test.skip=true
#Stage 2
# set base image for second stage
FROM openjdk:11-jre-slim-buster
# set deployment directory
WORKDIR /opt/demo
# copy over the built artifact from the maven image
COPY --from=stage1 /opt/demo/target/a-2_automation_system-0.0.1-SNAPSHOT.jar /opt/demo/
CMD ["java", "-jar", "a-2_automation_system-0.0.1-SNAPSHOT.jar"]
