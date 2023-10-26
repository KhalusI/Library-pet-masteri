# Використовуємо офіційний образ Java
FROM openjdk:17

# Встановлюємо робочу директорію в контейнері
WORKDIR /app

# Копіюємо JAR-файл з комп'ютера до контейнера
COPY target/Library-0.0.1-SNAPSHOT.jar app.jar

# Виконуємо команду для запуску додатку
CMD ["java", "-jar", "app.jar"]