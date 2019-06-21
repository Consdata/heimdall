FROM adoptopenjdk/openjdk11:jdk-11.0.3_7-slim
WORKDIR /app
COPY build/libs/report*jar heimdall-report.jar
EXPOSE 8080
CMD ["java","-jar","heimdall-report.jar","--server.port","8080"]
