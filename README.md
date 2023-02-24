# CreditManagement
Spring Boot Project for DefineX

<p>With the identity number, name-surname, monthly income, phone information, date of birth and the collateral (optional) identity number, the credit score service is sent to the default credit score service and the credit score of the relevant person is obtained and the credit result is shown to the user according to the following rules. (There may be two options as Approval or Rejection.) </p>

## EER DIAGRAM

</hr>

![Ekran Görüntüsü (879)](https://user-images.githubusercontent.com/54955167/221320115-c2de1ccf-7379-4f17-aa87-5622816f543a.png)

## MODELS

</hr>

![Ekran Görüntüsü (880)](https://user-images.githubusercontent.com/54955167/221320231-f41815d2-9fea-4090-90d7-51c4ebd65a91.png)

## API ENDPOINTS

</hr>

![Ekran Görüntüsü (881)](https://user-images.githubusercontent.com/54955167/221320298-5a87e295-bd6e-4390-92bc-7678c265f938.png)

## RUN

</hr>

### Local
<li> MongoDB: http://localhost:8081/db/credit/ </li>
<li> Spring Boot : http://localhost:8080 </li>
<li> Run Spring Boot App: mvn spring-boot:run </li>
<li> Package app: maven package </li>

### Docker
<li> Run Docker image: docker run --name credit -d -p 9090:8080 credit:1.0 </li>

## Swagger Endpoints

</hr>
<li> http://localhost:8080/swagger-ui/index.html#/  </li>

## Technology Stack: 

</hr>

<li> Java 17 </li>
<li> Spring Boot </li>
<li> MongoDb </li>
<li> Swagger </li>
<li> JUnit and Mockito /li>
<li> N-Layered Architecture </li>

