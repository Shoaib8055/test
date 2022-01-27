FROM openjdk:11
EXPOSE 8080
ADD target/services-module.jar services-module.jar
ENTRYPOINT ["java", "-jar", "/services-module.jar"]