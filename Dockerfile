FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/investment-portfolio-app-0.0.1-SNAPSHOT.jar investment-portfolio-app.jar
ENTRYPOINT ["java","-jar","/investment-portfolio-app.jar"]