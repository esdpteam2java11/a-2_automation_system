FROM openjdk:11

COPY target/a-2_automation_system-0.0.1-SNAPSHOT.jar /demo.jar

EXPOSE 8080
CMD ["java", "-jar", "/demo.jar"]
