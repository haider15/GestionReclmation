# Étape 1 : Build avec Gradle + JDK 21
FROM gradle:8.4-jdk21 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

# Utilisation de --no-daemon pour éviter de laisser le daemon Gradle tourner dans le conteneur
RUN gradle build -x test --no-daemon

# Étape 2 : Exécution avec JDK 21 JRE (version plus légère que JDK)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copier uniquement le jar produit
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"   ]
