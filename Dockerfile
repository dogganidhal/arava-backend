FROM gradle:latest
WORKDIR /app/arava

COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .
COPY core core
COPY api api

RUN gradle build

CMD cd /app/arava && gradle api:bootRun