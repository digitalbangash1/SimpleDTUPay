FROM openjdk:11
EXPOSE 8080
ADD target/code-with-quarkus-1.0.0-SNAPSHOT.jar code-with-quarkus-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/code-with-quarkus-1.0.0-SNAPSHOT.jar" ]
