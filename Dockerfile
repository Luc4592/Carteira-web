FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/investment-portfolio-app-1.0-SNAPSHOT.jar investment-portfolio-app.jar
ENTRYPOINT ["java","-jar","/investment-portfolio-app.jar"]