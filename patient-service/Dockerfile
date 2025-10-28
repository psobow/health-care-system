# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# copy parent and module poms
COPY pom.xml .
COPY patient-service/pom.xml patient-service/pom.xml

# pre-fetch dependencies, cached via BuildKit
RUN --mount=type=cache,target=/root/.m2 \
     mvn -B -q -f /app/pom.xml -pl patient-service -am dependency:go-offline

# copy sources of the module
COPY patient-service/src patient-service/src

# Build only patient-service, skipping tests; reuse cached ~/.m2
RUN --mount=type=cache,target=/root/.m2 \
     mvn -B -q -f /app/pom.xml -pl patient-service -am -DskipTests package


# Runtime stage
FROM eclipse-temurin:21-jre AS runner
WORKDIR /app/patient-service
COPY --from=builder /app/patient-service/target/patient-service-*.jar /app/patient-service/app.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","./app.jar"]
