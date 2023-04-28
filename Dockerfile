FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv6Addresses=false"]
