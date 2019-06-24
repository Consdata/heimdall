FROM adoptopenjdk/openjdk11:jdk-11.0.3_7-slim
WORKDIR /app
COPY build/libs/dependency-list*jar heimdall-dependency-list.jar
EXPOSE 8080
CMD ["java","-jar","heimdall-dependency-list.jar","--server.port","8080"]
