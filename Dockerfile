FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve


COPY src src
RUN ./mvnw package

FROM openjdk:11
WORKDIR credit
COPY --from=build target/*.jar CreditManagement.jar
ENTRYPOINT ["java","-jar","CreditManagement.jar"]