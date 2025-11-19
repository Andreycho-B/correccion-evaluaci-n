# Sistema de Citas para Centro de Ayuda Emocional y Psicológica

Sistema completo de gestión de citas desarrollado con Spring Boot 3.3.5, diseñado para centros de ayuda emocional y psicológica.

## Características

- **Arquitectura en capas**: Separación clara entre entidades, DTOs, repositorios, servicios y controladores
- **API REST profesional**: Endpoints RESTful con validaciones y manejo de excepciones
- **Spring Security**: Autenticación y autorización con rol SuperAdmin
- **Interfaz web moderna**: Diseño minimalista y calmado con Thymeleaf
- **Base de datos MySQL**: Persistencia con JPA/Hibernate
- **CRUD completo**: Gestión de usuarios, profesionales, servicios y citas

## Tecnologías

- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL 8.0
- Maven

## Requisitos

- JDK 21 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior

## Configuración

1. Crear la base de datos MySQL:
```sql
CREATE DATABASE Sistema_Andrey;
```

2. Configurar las credenciales en `application.properties` si es necesario (por defecto usa root/root)

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

5. Acceder a la aplicación en: `http://localhost:8080`

## Credenciales por defecto

- **Email**: admin@sistema.com
- **Contraseña**: admin123
- **Rol**: SUPERADMIN

## Estructura del proyecto

```
src/main/java/com/andrey/sistema_citas/
├── config/          # Configuraciones de seguridad y datos iniciales
├── controller/      # Controladores REST y Web
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA
├── exception/      # Excepciones personalizadas y manejadores
├── repository/     # Repositorios JPA
├── security/       # Configuración de seguridad
├── service/        # Lógica de negocio
└── util/           # Utilidades y mappers

src/main/resources/
├── static/         # Recursos estáticos (CSS, JS)
└── templates/      # Plantillas Thymeleaf
```

## API REST Endpoints

### Usuarios
- GET `/api/usuarios` - Listar todos los usuarios
- GET `/api/usuarios/{id}` - Obtener usuario por ID
- POST `/api/usuarios` - Crear nuevo usuario
- PUT `/api/usuarios/{id}` - Actualizar usuario
- DELETE `/api/usuarios/{id}` - Eliminar usuario

### Profesionales
- GET `/api/profesionales` - Listar todos los profesionales
- GET `/api/profesionales/{id}` - Obtener profesional por ID
- GET `/api/profesionales/especialidad/{especialidad}` - Buscar por especialidad
- POST `/api/profesionales` - Crear nuevo profesional
- PUT `/api/profesionales/{id}` - Actualizar profesional
- DELETE `/api/profesionales/{id}` - Eliminar profesional

### Servicios
- GET `/api/servicios` - Listar todos los servicios
- GET `/api/servicios/{id}` - Obtener servicio por ID
- GET `/api/servicios/buscar?nombre={nombre}` - Buscar por nombre
- POST `/api/servicios` - Crear nuevo servicio
- PUT `/api/servicios/{id}` - Actualizar servicio
- DELETE `/api/servicios/{id}` - Eliminar servicio

### Citas
- GET `/api/citas` - Listar todas las citas
- GET `/api/citas/{id}` - Obtener cita por ID
- GET `/api/citas/usuario/{usuarioId}` - Citas por usuario
- GET `/api/citas/profesional/{profesionalId}` - Citas por profesional
- GET `/api/citas/estado/{estado}` - Citas por estado
- POST `/api/citas` - Crear nueva cita
- PUT `/api/citas/{id}` - Actualizar cita
- DELETE `/api/citas/{id}` - Eliminar cita

## Autor

Desarrollado por Andrey para evaluación académica.

## Licencia

Este proyecto es de uso educativo.
