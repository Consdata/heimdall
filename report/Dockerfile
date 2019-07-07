FROM adoptopenjdk/openjdk11-openj9:slim

WORKDIR /app
COPY build/libs/report*jar heimdall-report.jar

ENV JAVA_OPTS="-Xmx256m -Xss512k -XX:+UseSerialGC"
EXPOSE 8080

CMD java $JAVA_OPTS -jar heimdall-report.jar --server.port 8080
