# email-microservice

#### Execute Microservice

To run the microservice, run task or run for the [EmailMicroserviceApplication.java](src/main/java/com/emailmicroservice/EmailMicroserviceApplication.java) file.

```task
mvn spring-boot:run
```

# TO DO LIST
Swagger
readme
json return
forma de primeiro configurar o AWS

localhost:8080/api/email

### JSON Example

Here is a JSON example to send an e-mail.   

```json
{
  "to": "yourEmail@email.com",
  "subject": "your subject",
  "body": "your body"
}