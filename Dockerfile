FROM openjdk:11
WORKDIR /app/arava

ARG jar
ENV JAR=$jar

COPY $jar /app/arava/app.jar

EXPOSE 8080

CMD java -jar /app/arava/app.jar