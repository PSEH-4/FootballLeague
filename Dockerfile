FROM openjdk:8-jdk-alpine
ADD target/FootballLeague-0.0.1-SNAPSHOT.jar FootballLeague-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/FootballLeague-0.0.1-SNAPSHOT.jar"]