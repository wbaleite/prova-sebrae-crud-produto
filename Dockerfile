FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/manter-produto-0.0.1-SNAPSHOT.jar /app/manter-produto.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/manter-produto.jar"]
