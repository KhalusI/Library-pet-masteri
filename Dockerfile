# Використовуємо офіційний образ Java
FROM openjdk:17

# Встановлюємо робочу директорію в контейнері
WORKDIR /app

# Копіюємо JAR-файл з комп'ютера до контейнера
COPY target/Library-0.0.1-SNAPSHOT.jar app.jar
ENV DB_URL=jdbc:mysql://your-database-url
ENV DB_USERNAME=your-username
ENV DB_PASSWORD=your-password

# Виконуємо команду для запуску додатку
CMD ["java", "-jar", "app.jar"]