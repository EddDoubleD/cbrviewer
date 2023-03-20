FROM cr-sk-prod.otr.ru:5005/registry/docker-images/openjdk:11-jdk-slim as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
# Squeeze in 60 seconds
FROM cr-sk-prod.otr.ru:5005/registry/docker-images/alpine:3 as packager
RUN apk --no-cache add openjdk11-jdk openjdk11-jmods
ENV JAVA_MINIMAL="/opt/java-minimal"
RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=radistao:IMPLEMENTOR_VERSION=radistao_JRE" \
    --output "$JAVA_MINIMAL"

FROM cr-sk-prod.otr.ru:5005/registry/docker-images/alpine:3
LABEL maintainer="sulimov.dmitriy@otr.ru"
WORKDIR /app
ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
COPY --from=builder /src/target/cbrviewer-*.jar app.jar
# The plugin does not start in automatic mode, we do not display ports outside
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]