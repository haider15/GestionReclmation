# Étape 1 : Build de l'application avec Gradle
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copier les fichiers nécessaires pour builder (optimisé pour le cache)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Télécharger les dépendances
RUN ./gradlew dependencies

# Copier le code source
COPY . .

# Build du projet (fichier .jar sera généré dans build/libs/)
RUN ./gradlew bootJar

# Étape 2 : Image finale légère avec OpenJDK 21
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copier le jar depuis l'image builder
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
