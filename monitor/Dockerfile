FROM adoptopenjdk/openjdk11:jdk-11.0.3_7-slim
WORKDIR /app
COPY build/libs/monitor*jar heimdall-monitor.jar
EXPOSE 8080
CMD ["java","-jar","heimdall-monitor.jar","--server.port","8080"]
