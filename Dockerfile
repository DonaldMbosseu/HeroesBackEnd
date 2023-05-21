FROM adoptopenjdk:16-jdk-hotspot
WORKDIR /app
COPY out/artifacts/TestAub_jar/TestAub.jar .
COPY src/main/resources/application.properties .
EXPOSE 8080
CMD ["java", "-jar", "TestAub.jar"]


