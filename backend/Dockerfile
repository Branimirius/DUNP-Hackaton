FROM maven:3.8.2 AS build

#COPY --chown=maven:maven . /home/maven/src

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package


FROM bellsoft/liberica-openjdk-alpine:11.0.6-aarch64

ARG JAR_FILE=logoass-docker.jar

WORKDIR /opt/app

EXPOSE 8080


RUN mkdir /app

COPY --from=build /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseContainerSupport","-Dspring.profiles.active=docker_production", "-Djava.security.egd=file:/dev/./urandom", "-Djava.awt.headless=true", "-jar", "logoass-docker.jar"]
