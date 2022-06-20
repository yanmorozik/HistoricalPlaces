FROM openjdk:8
ADD /target/historicalplaces-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
