# Email Microservice

This microservice send an email using the [AWS SES](https://aws.amazon.com/pt/ses/).
Technologies used: Java, Java Spring, AWS.

- [Installation](#installation)
- [Configuration](#configuration)
- [Execute](#execute)
- [Documentation](#documentation)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/felipebabel/email-microservice.git
```

2. Install dependencies with Maven
## Configuration

1. It's necessary to install the Lombok Plugin in settings.

![img.png](img.png)
2. It's necessary to add your AWS credentials in the environment variables.

![img_5.png](img_5.png)

3. Prerequisite: JAVA 17

## Execute

1. To run the microservice, run task or run for
the [EmailMicroserviceApplication.java](src/main/java/com/emailmicroservice/EmailMicroserviceApplication.java) file.


```task
mvn spring-boot:run
```
2. The API will be accessible at http://localhost:8080

3. Postman request example:

![img_1.png](assets/img/img_1.png)

## Documentation

[Swagger UI](http://localhost:8080/swagger-ui/index.html#/)