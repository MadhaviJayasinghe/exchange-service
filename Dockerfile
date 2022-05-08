FROM openjdk:11-jre-slim-buster
ADD target/exchange-service-0.0.1-SNAPSHOT.jar exchange-service-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "exchange-service-0.0.1-SNAPSHOT.jar"]