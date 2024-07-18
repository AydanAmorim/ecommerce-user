#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /user

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk

WORKDIR /user

COPY --from=build /ecommerce-user/target/*.jar ./ecommerce-user.jar

EXPOSE 7073

ENTRYPOINT ["java","-jar","ecommerce-user.jar"]