FROM adoptopenjdk/openjdk11-openj9:slim

WORKDIR /app
COPY build/libs/backend*jar heimdall.jar

ENV JAVA_XMX="512m"
ENV JAVA_OPTS="-Xss512k -XX:+UseSerialGC"
EXPOSE 8080

CMD java -Xmx$JAVA_XMX $JAVA_OPTS -jar heimdall.jar --server.port 8080
