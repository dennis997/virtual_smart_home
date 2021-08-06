###
### Build stage
###
FROM maven:3.8.1-openjdk-11 AS build
LABEL maintainer dennis.hilz@stud.h-da.de
LABEL stage build

RUN mkdir ./app
COPY . /app/
WORKDIR /app

RUN mvn -f ./pom.xml clean package

###
### Run stage
###

FROM openjdk:11
LABEL maintainer dennis.hilz@stud.h-da.de
EXPOSE 5000/udp


ENV ip "localhost"
ENV port 5000
ENV location "bathroom"
ENV interval 5000

RUN mkdir ./app
COPY --from=build /app/target/Sensor-1.0-SNAPSHOT.jar /app/
COPY /run.sh /run.sh
ENTRYPOINT [ "sh", "/run.sh" ]

#CMD ["java", "-jar", "/app/Sensor-1.0-SNAPSHOT.jar"]
