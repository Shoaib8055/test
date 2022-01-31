FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=/target/services-module.jar

COPY ${JAR_FILE} myapp.jar

EXPOSE 9003

ENTRYPOINT ["java","-jar" , "/myapp.jar"]