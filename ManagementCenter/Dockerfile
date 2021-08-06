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
### Run stage
###

FROM openjdk:11
LABEL maintainer alexander.scharfenberg@stud.h-da.de
EXPOSE 5000/udp

ENV ip "localhost"
ENV port 5000

RUN mkdir ./app
COPY --from=build /app/target/ManagementCenter-1.0-SNAPSHOT.jar /app/
COPY /run.sh /run.sh
ENTRYPOINT [ "sh", "/run.sh" ]
CMD ["java", "-jar", "/app/ManagementCenter-1.0-SNAPSHOT.jar"]
