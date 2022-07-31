

FROM maven:3.8.1-jdk-11
COPY ./ ./

RUN mvn clean package -DskipTests
COPY target/a-2_automation_system-0.0.1-SNAPSHOT.jar /demo.jar
CMD ["java", "-jar", "/demo.jar"]