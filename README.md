# Шаблон микросервисов автосалона

Учебный Java-проект с двумя Spring Boot микросервисами для backend-системы автосалона. В проекте есть отдельные базы данных PostgreSQL, миграции Liquibase, авторизация через Keycloak, обмен событиями через RabbitMQ, gRPC-взаимодействие между сервисами и интеграционные тесты на Testcontainers.

## Сервисы

- `order-service` отвечает за заказы автомобилей, индивидуальные заказы, заявки на тест-драйв и пользовательские сценарии покупки.
- `storage-service` отвечает за автомобили, комплектации, доступность машин, компоненты конфигуратора и сборочные заказы.

## Технологии

- Java 21
- Spring Boot 3.3
- Gradle
- PostgreSQL
- Liquibase
- Spring Security OAuth2 Resource Server
- Keycloak
- RabbitMQ
- gRPC
- MapStruct
- Lombok
- Testcontainers

## Структура проекта

```text
.
├── order-service
│   ├── src/main/java
│   ├── src/main/resources
│   └── src/integrationTest/java
├── storage-service
│   ├── src/main/java
│   ├── src/main/resources
│   └── src/integrationTest/java
├── docker
│   └── keycloak
├── docker-compose.yml
├── build.gradle
└── settings.gradle
```

## Локальный запуск инфраструктуры

Для запуска PostgreSQL, Keycloak и RabbitMQ нужен Docker.

```bash
docker compose up -d
```

После запуска будут доступны:

- PostgreSQL для локальной разработки: `localhost:5432`
- PostgreSQL для `order-service`: `localhost:5433`
- PostgreSQL для `storage-service`: `localhost:5434`
- Keycloak: `http://localhost:8081`
- RabbitMQ: `localhost:5672`
- RabbitMQ Management UI: `http://localhost:15672`

Логины и пароли в `docker-compose.yml` и `application.yml` предназначены только для локальной разработки.

## Запуск сервисов

Запуск `order-service`:

```bash
./gradlew :order-service:bootRun
```

Запуск `storage-service`:

```bash
./gradlew :storage-service:bootRun
```

Порты по умолчанию:

- `order-service`: `8082`
- `storage-service`: `8083`
- gRPC-сервер `storage-service`: `9090`
- Keycloak realm issuer: `http://localhost:8081/realms/car-shop`

## Swagger

После запуска сервисов документация API доступна по адресам:

- `http://localhost:8082/swagger-ui/index.html`
- `http://localhost:8083/swagger-ui/index.html`

## Миграции базы данных

Миграции Liquibase лежат отдельно в каждом сервисе:

- `order-service/src/main/resources/db/changelog`
- `storage-service/src/main/resources/db/changelog`

Миграции применяются автоматически при старте сервиса.

## Тесты

Запуск unit-тестов:

```bash
./gradlew test
```

Запуск интеграционных тестов:

```bash
./gradlew integrationTest
```

Для интеграционных тестов должен быть запущен Docker, так как тесты используют Testcontainers.

## Что не коммитить

В публичный репозиторий не должны попадать:

- `.env` и другие локальные конфиги с секретами;
- `.idea`, `.gradle`, `build` и другие локальные файлы IDE/сборки;
- реальные production-пароли, токены и приватные ключи.
