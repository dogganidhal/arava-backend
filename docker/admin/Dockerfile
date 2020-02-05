FROM gradle:latest
WORKDIR /app/arava

COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .
COPY core core
COPY admin admin

RUN gradle build

CMD cd /app/arava && java -jar admin/build/libs/admin.jar