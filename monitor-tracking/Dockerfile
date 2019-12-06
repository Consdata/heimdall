FROM adoptopenjdk/openjdk11-openj9:slim

WORKDIR /app
COPY build/libs/monitor-tracking*jar heimdall-monitor-tracking.jar

ENV JAVA_XMX="256m"
ENV JAVA_OPTS="-Xss512k -XX:+UseSerialGC"
EXPOSE 8080

CMD java -Xmx$JAVA_XMX $JAVA_OPTS -jar heimdall-monitor-tracking.jar --server.port 8080
