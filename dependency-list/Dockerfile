FROM adoptopenjdk/openjdk11-openj9:slim

WORKDIR /app
COPY build/libs/dependency-list*jar heimdall-dependency-list.jar

ENV JAVA_XMX="256m"
ENV JAVA_OPTS="-Xss512k -XX:+UseSerialGC"
EXPOSE 8080

CMD java -Xmx$JAVA_XMX $JAVA_OPTS -jar heimdall-dependency-list.jar --server.port 8080
