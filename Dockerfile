# Gunakan image dasar OpenJDK
FROM openjdk:17-jdk-alpine

# Set working directory di dalam container
WORKDIR /app

# Salin file JAR dari build ke dalam container
COPY ./target/temperatureConverter-0.0.1-SNAPSHOT.jar temperatureConverter-0.0.1-SNAPSHOT.jar

# Mengatur versi aplikasi
ARG APP_VERSION=v1.0
LABEL version="${APP_VERSION}"

# Ekspose port yang digunakan aplikasi (misalnya 8071)
EXPOSE 8071

# Perintah untuk menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "temperatureConverter-0.0.1-SNAPSHOT.jar"]
