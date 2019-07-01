FROM adoptopenjdk/openjdk11-openj9:slim

WORKDIR /app
COPY build/libs/monitor-tracking*jar heimdall-monitor-tracking.jar

ENV JAVA_OPTS="-Xmx256m -Xss512k -XX:+UseSerialGC"
EXPOSE 8080

CMD java $JAVA_OPTS -jar heimdall-monitor-tracking.jar --server.port 8080
