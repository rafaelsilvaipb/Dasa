FROM openjdk:8

EXPOSE 8080

ADD target/dasa-api.jar dasa-api.jar

ENTRYPOINT ["java", "-jar", "dasa-api.jar"]
