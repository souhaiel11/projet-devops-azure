# ---- Stage 1 : Compilation ----
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# -o = offline mode après premier téléchargement
# --fail-at-end = continue même si erreur partielle
# -Dmaven.wagon.httpconnectionManager.ttlSeconds = timeout réseau
RUN mvn package -DskipTests \
    -Dmaven.wagon.httpconnectionManager.ttlSeconds=120 \
    -Dmaven.wagon.http.retryHandler.count=5 \
    -Dmaven.wagon.http.pool=false

# ---- Stage 2 : Exécution ----
FROM eclipse-temurin:8-jre-jammy
WORKDIR /app
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
COPY --from=build /app/target/projet1.jar app.jar
RUN chown appuser:appgroup app.jar
USER appuser
EXPOSE 8085
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/.urandom","-jar","app.jar"]
