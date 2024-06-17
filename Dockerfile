FROM openjdk:18-oracle

WORKDIR /app


COPY target/app-students-0.0.1-SNAPSHOT.jar app.jar


COPY src/main/resources/data/students.txt src/main/resources/data/students.txt

ENV CREATE_STUDENTS_ON_START=false

CMD ["java", "-jar", "app.jar"]
