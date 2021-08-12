###
### Build stage
###
FROM maven:3.8.1-openjdk-11 AS build
LABEL maintainer alexander.scharfenberg@stud.h-da.de
LABEL stage build

RUN mkdir ./app
COPY . /app/
WORKDIR /app
RUN mvn package

###
### Run stage###

FROM openjdk:11
LABEL maintainer alexander.scharfenberg@stud.h-da.de

RUN mkdir ./app
COPY --from=build /app/target/CloudServer-1.0-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/CloudServer-1.0-SNAPSHOT.jar"]
