
FROM openjdk:11-slim-buster as stage1
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw -B dependency:go-offline                          
COPY src src
RUN ./mvnw -B package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim-buster

WORKDIR /opt/demo

COPY --from=stage1 target/a-2_automation_system-0.0.1-SNAPSHOT.jar /opt/demo/
CMD ["java", "-jar", "a-2_automation_system-0.0.1-SNAPSHOT.jar"]
