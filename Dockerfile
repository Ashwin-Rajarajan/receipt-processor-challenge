FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle gradlew gradlew.bat /app/
COPY gradle /app/gradle
RUN ./gradlew build --no-daemon || true
COPY . /app

# Added the step below to run tests and fail the build if any test case fails.
# You can uncomment the line below for the same.
#RUN ./gradlew test --no-daemon
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
