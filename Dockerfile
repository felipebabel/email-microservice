FROM openjdk:21-jdk
RUN mkdir /emailms
WORKDIR /emailms
COPY target/*.jar /emailms/emailms.jar
CMD ["java", "-jar", "/emailms/emailms.jar"]
