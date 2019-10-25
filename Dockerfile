# Extra stuff we needed to do to make our build work without any other installation
FROM gradle:5.6.2 as builder
COPY build.gradle .
COPY gradle.properties .
COPY settings.gradle .
COPY src ./src
RUN gradle clean build --no-daemon

#Normally, we only need these guys and let gradle do its stuff
FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY --from=builder /home/gradle/build/libs/*.jar app.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar app.jar --server.port=8080