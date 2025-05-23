# 🏛️ Cerad System

Cerad System es una plataforma modular desarrollada en **Java 17 + Spring Boot 3.x** que gestiona la **autenticación de usuarios** y el **procesamiento masivo de archivos CSV mediante Spring Batch**. Utiliza una arquitectura de **microservicios** con configuración centralizada en **Spring Cloud Config Server**.

---

## 🚀 Funcionalidades Principales

✅ Autenticación con JWT (auth-service)  
✅ Configuración centralizada con Config Server (config-server)  
✅ Procesamiento de archivos CSV con Spring Batch (cerad-service)  
✅ Manejo de entornos y ramas en config-repo (develop, feature/*)  
✅ (Opcional) Integración con Eureka Service Registry

---

## 🧱 Arquitectura de módulos



## 📦 Microservicios principales

- **config-server**: Configuración centralizada vía Spring Cloud Config.
- **service-registry (Eureka)**: Registro y descubrimiento de servicios.
- **auth-service**: Microservicio de autenticación y gestión de usuarios con JWT.
- **cerad-service**: CRUD de documentos y procesamiento masivo de archivos CSV con Spring Batch.
- **commonlib**: Librería compartida (DTOs, utilidades).

---

## 🧰 Tecnologías usadas

- Java 17
- Spring Boot 3.3.x
- Spring Cloud 2023.0.1 (Eureka, Config Server, Gateway)
- MySQL / PostgreSQL
- Maven
- Docker y Docker Compose
- Feign Client (comunicación entre servicios)
- (Opcional) RabbitMQ para eventos

---

## 📂 Estructura del proyecto

```bash

cerad-system/
├── auth-service/
│ ├── src/main/java/com/cerad/auth/
│ ├── src/main/resources/bootstrap.properties
│ ├── src/main/resources/application.properties
│ └── pom.xml
├── config-server/
├── commonlib/
└── config-repo/ (como submódulo git o externo)
└── README.md
```
---
```
[config-repo (git)] --> [config-server] --> [auth-service] & [cerad-service]
                                      ↳ Eureka (service-registry)
```
---
## 🚀 Requisitos

- Java 17
- Maven
- Docker
- Git
- IDE: IntelliJ IDEA (recomendado)

---
## 📝 Plantilla de commits

Este proyecto utiliza el estándar [Conventional Commits](https://www.conventionalcommits.org/es/v1.0.0/) para mantener un historial limpio y comprensible.

Puedes usar la plantilla `.gitmessage.txt` incluida en este repositorio:

```bash

git config --global commit.template ".gitmessage.txt"
git config --global core.editor "code --wait"
git commit 
````
``git commit - Por defecto se abre el Vscode``


## 🧑‍💻 Autor

Desarrollado por **Edwin H**  
📅 2025

Este proyecto está en constante evolución como parte de mi especialización en backend con Java y arquitectura de microservicios.