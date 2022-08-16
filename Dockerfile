
FROM maven:3.6.3-adoptopenjdk-11 as stage1
WORKDIR /opt/demo
COPY pom.xml .
RUN  mvn -B dependency:go-offline                          
COPY src src
RUN mvn -B package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim-buster

WORKDIR /opt/demo

COPY --from=stage1 /opt/demo/target/a-2_automation_system-0.0.1-SNAPSHOT.jar /opt/demo/
CMD ["java", "-jar", "a-2_automation_system-0.0.1-SNAPSHOT.jar"]
