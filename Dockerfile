FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN javac MyWebServer.java

CMD ["java", "MyWebServer"]
