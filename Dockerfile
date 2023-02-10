FROM maven:3.8.3-openjdk-17 as build

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests

ARG JAR_FILE=target/*.jar
RUN mkdir app && \
java -Djarmode=layertools -jar ${JAR_FILE} extract --destination app


FROM azul/zulu-openjdk:17
LABEL container_name="k8s-example-simple" maintainer="Zulu Enterprise Container Images <azul-zulu-images@microsoft.com>"

ENV DB_SSLMODE="require"
ENV JAVA_TOOL_OPTIONS="-XX:+PrintFlagsFinal -XX:+UseContainerSupport"

WORKDIR app
COPY --from=build /app/dependencies/ ./
COPY --from=build /app/snapshot-dependencies/ ./
COPY --from=build /app/spring-boot-loader/ ./
COPY --from=build /app/application/ ./

EXPOSE 8080

RUN addgroup --system --gid 1002 app && adduser --system --uid 1002 --gid 1002 appuser
USER 1002

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]