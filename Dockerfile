FROM maven:3.9.14-eclipse-temurin-21
#FROM ubuntu:24.04

USER root

#RUN apt-get update && apt-get install -y docker-compose-plugin
#RUN chmod +x entrypoint.sh
RUN mkdir -p /appium_tests
WORKDIR /appium_tests

COPY . .

#RUN mvn clean test
ENTRYPOINT [ "./entrypoint.sh" ]