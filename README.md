# 👕 backend‑ecommerce‑dark

API backend de un e‑commerce de ropa, construido con **Java**, **Spring Boot** y **MySQL**. Ideal para aplicar patrones REST, gestión de datos, seguridad básica y mejores prácticas para microservicios.

---

## 🗂️ Índice

* [✨ Características](#-características)
* [⚙️ Requisitos](#-requisitos)
* [📦 Instalación & Configuración](#-instalación--configuración)
* [🚀 Ejecución](#-ejecución)
* [🧩 Endpoints principales](#-endpoints-principales)
* [🏛️ Arquitectura & estilo](#-arquitectura--estilo)
* [🔧 Mejoras sugeridas](#-mejoras-sugeridas)
* [🤝 Contribuciones](#-contribuciones)
* [⚖️ Licencia](#-licencia)

---

## ✨ Características

* API REST para manejo de **productos**, **usuarios/clientes**, **carritos** y **órdenes**.
* Usando **Spring Data JPA** para acceder a la base de datos MySQL.
* Endpoints CRUD para productos y usuarios + procesos de compra.
* Buenas prácticas: capas (`Controller`→`Service`→`Repository`), validaciones y manejo de errores.
* Servidor embebido gracias a Spring Boot, listo para deploy o contenerización ([baeldung.com][1], [medium.com][2]).

---

## ⚙️ Requisitos

* Java JDK 11+ (recomendado 17)
* Maven o Gradle
* MySQL en local o servidor remoto

---

## 📦 Instalación & Configuración

```bash
git clone https://github.com/FrancoDavid/backend-ecommerce-dark.git
cd backend-ecommerce-dark
```

Configura `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASENA
spring.jpa.hibernate.ddl-auto=update
```

Asegúrate de crear la base `ecommerce_db` previamente.

---

## 🚀 Ejecución

Con Maven:

```bash
./mvnw spring-boot:run
```

Con Gradle:

```bash
./gradlew bootRun
```

La API estará disponible en `http://localhost:8080/api/...`

---

## 🧩 Endpoints principales

* `/api/products` — GET, POST, PUT, DELETE productos
* `/api/users` — gestión de usuarios (CRUD)
* `/api/cart` — CRUD de carrito (agregar, quitar items)
* `/api/orders` — creación de órdenes y listado
* `/api/checkout` — procesar compra: carrito → orden

*(Ajusta según implementación exacta)*

---

## 🏛️ Arquitectura & estilo

* **Capas bien definidas**:

  * `Controller`: define los endpoints REST
  * `Service`: lógica de negocio (carrito, cálculo del total, stock)
  * `Repository`: acceso a datos con JPA
  * `Entity`: mapeo objeto-relacional
* **Transaccionalidad** usando `@Transactional` en servicios
* **Validaciones** usando `@Valid`, DTOs y excepciones globales estilo `@ControllerAdvice`
* **Auto-configuración** con Spring Boot — servidor embebido y JPA listo para producción ([github.com][3], [github.com][4], [medium.com][2])

---

## 🔧 Mejoras sugeridas

* Seguridad con **Spring Security + JWT**
* Paginación y filtros en listados `GET /products`
* Documentación con **Swagger/OpenAPI**
* Tests unitarios e integración (`JUnit`, `Mockito`, `@SpringBootTest`)
* Contenerización con **Docker + Docker Compose** + CI/CD
* Migración a microservicios (usuarios, productos, órdenes) usando Spring Cloud/Eureka ([medium.com][2], [github.com][5])

---

## 🤝 Contribuciones

¡Tus aportes son bienvenidos!

1. Haz fork del repositorio
2. Crea una rama `feature/tu-mejora`
3. Implementa tus cambios con commits claros
4. Envía un pull request explicando tus mejoras

---

## ⚖️ Licencia

Licencia **MIT**. Consulta `LICENSE` para más detalles.
