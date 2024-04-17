# Build Stage
FROM openjdk:17-alpine AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw
# Faça o download das dependências do pom.xml
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Definição de produção para a imagem do Spring boot
FROM openjdk:17-alpine as production
ARG DEPENDENCY=/app/target/dependency

# Copiar as dependências para o build artifact
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-cp", "app:app/lib/*","com.example.planisa.PlanisaApplication"]
