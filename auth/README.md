# Sistema de Gestión de Autenticación y Permisos con Spring MVC

Una aplicación web completa implementada con **Spring Boot**, **Spring MVC**, **Spring Security**, **Thymeleaf**, **CSS** y **JavaScript** que proporciona un sistema robusto de gestión de usuarios, roles y permisos.

## 📋 Características Principales

✅ **Autenticación de Usuarios**
- Registro de nuevos usuarios
- Login seguro con encriptación de contraseñas (BCrypt)
- Logout

✅ **Gestión de Usuarios**
- Listar todos los usuarios registrados
- Crear nuevos usuarios
- Editar información de usuarios
- Eliminar usuarios
- Asignar roles a usuarios

✅ **Gestión de Roles**
- Criar roles personalizados
- Listar todos los roles
- Editar roles existentes
- Eliminar roles
- Agregar y remover permisos de roles

✅ **Gestión de Permisos**
- Listar todos los permisos disponibles
- Asignar permisos a roles
- Control granular de acceso

✅ **Control de Acceso Basado en Permisos**
- Validación de permisos en cada operación
- Usuario administrador con todos los permisos
- Otros usuarios creados con permisos limitados
- Protección de rutas con Spring Security

✅ **Interfaz Moderna**
- Diseño responsivo y atractivo
- Bootstrap-like con CSS personalizado
- Interacciones dinámicas con JavaScript
- Modales para operaciones rápidas

## 🔧 Tecnologías Utilizadas

- **Backend**: Spring Boot 3.5.13, Spring MVC, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript Vanilla
- **Base de Datos**: H2 (en memoria)
- **Build Tool**: Gradle 8.14.4
- **Java**: Versión 17+

## 🚀 Guía de Inicio Rápido

### Prerrequisitos
- Java 17 o superior
- Gradle 8.14.4 (incluido en el proyecto)

### Instalación y Ejecución

1. **Navegar al directorio del proyecto:**
```bash
cd /root/work-space/compu2/2026-1-G5/auth
```

2. **Compilar el proyecto:**
```bash
./gradlew clean build -x test
```

3. **Ejecutar la aplicación:**
```bash
./gradlew bootRun
```

La aplicación estará disponible en: **http://localhost:8080**

## 📊 Datos Iniciales

La aplicación crea automáticamente al iniciar:

### Usuario Administrador
- **Usuario**: `admin`
- **Contraseña**: `admin123`
- **Email**: `admin@icesi.edu.co`
- **Rol**: ADMIN (con todos los permisos)

### Permisos Iniciales

#### Permisos de Usuarios
- `USER_LIST` - Listar usuarios
- `USER_CREATE` - Crear usuarios
- `USER_EDIT` - Editar usuarios
- `USER_DELETE` - Eliminar usuarios
- `USER_ROLE_ASSIGN` - Asignar roles a usuarios
- `USER_ROLE_REMOVE` - Remover roles de usuarios

#### Permisos de Roles
- `ROLE_LIST` - Listar roles
- `ROLE_CREATE` - Crear roles
- `ROLE_EDIT` - Editar roles
- `ROLE_DELETE` - Eliminar roles
- `ROLE_PERMISSION_ADD` - Agregar permisos a roles
- `ROLE_PERMISSION_REMOVE` - Remover permisos de roles

#### Permisos de Permisos
- `PERMISSION_LIST` - Listar permisos

## 📁 Estructura del Proyecto

```
auth/
├── src/main/java/co/icesi/auth/
│   ├── AuthApplication.java                    # Clase principal
│   ├── config/
│   │   ├── SecurityConfig.java                # Configuración de Spring Security
│   │   ├── CustomPermissionEvaluator.java     # Evaluador de permisos personalizado
│   │   └── DataInitializer.java               # Inicializador de datos
│   ├── controller/
│   │   ├── AuthController.java                # Control de login/registro
│   │   ├── UserController.java                # Gestión de usuarios
│   │   ├── RoleController.java                # Gestión de roles
│   │   └── PermissionController.java          # Visualización de permisos
│   ├── model/
│   │   ├── User.java                          # Entidad de Usuario
│   │   ├── Role.java                          # Entidad de Rol
│   │   └── Permission.java                    # Entidad de Permiso
│   ├── repository/
│   │   ├── UserRepository.java                # Repositorio de Usuarios
│   │   ├── RoleRepository.java                # Repositorio de Roles
│   │   └── PermissionRepository.java          # Repositorio de Permisos
│   └── service/
│       ├── UserService.java                   # Servicio de Usuarios
│       ├── RoleService.java                   # Servicio de Roles
│       ├── PermissionService.java             # Servicio de Permisos
│       └── CustomUserDetailsService.java      # Servicio de detalles de usuario
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html                         # Página de login
│   │   ├── register.html                      # Página de registro
│   │   ├── dashboard.html                     # Dashboard principal
│   │   ├── layout.html                        # Layout base
│   │   ├── users/
│   │   │   ├── list.html                      # Listar usuarios
│   │   │   └── form.html                      # Formulario de usuario
│   │   ├── roles/
│   │   │   ├── list.html                      # Listar roles
│   │   │   └── form.html                      # Formulario de rol
│   │   └── permissions/
│   │       └── list.html                      # Listar permisos
│   ├── static/
│   │   ├── css/
│   │   │   └── styles.css                     # Estilos principales
│   │   └── js/
│   │       └── main.js                        # JavaScript principal
│   └── application.properties                 # Configuración de la aplicación
├── build.gradle                               # Configuración de Gradle
├── gradlew                                    # Wrapper de Gradle
└── README.md                                  # Este archivo
```

## 🔐 Flujo de Seguridad

1. **Autenticación**: Los usuarios inician sesión con sus credenciales
2. **Carga de Permisos**: Se cargan todos los permisos del usuario basados en sus roles
3. **Autorización**: Cada acción verifica si el usuario tiene los permisos necesarios
4. **Control de Acceso**: Las vistas se adaptan según los permisos del usuario

## 🎯 Casos de Uso

### 1. Registro de Usuario
- Acceder a `/register`
- Completar el formulario con datos personales
- El usuario se crea con roles vacíos (debe ser asignado por admin)

### 2. Login
- Acceder a `/login`
- Ingresar usuario y contraseña
- Será redirigido al dashboard

### 3. Gestión de Usuarios (requiere permiso USER_LIST)
- Ver la lista de usuarios registrados
- Crear nuevos usuarios (requiere USER_CREATE)
- Editar información de usuarios (requiere USER_EDIT)
- Eliminar usuarios (requiere USER_DELETE)
- Asignar roles a usuarios (requiere USER_ROLE_ASSIGN)

### 4. Gestión de Roles (requiere permiso ROLE_LIST)
- Ver todos los roles disponibles
- Crear nuevos roles (requiere ROLE_CREATE)
- Editar roles existentes (requiere ROLE_EDIT)
- Eliminar roles (requiere ROLE_DELETE)
- Agregar/remover permisos (requiere ROLE_PERMISSION_ADD/REMOVE)

### 5. Gestión de Permisos (requiere permiso PERMISSION_LIST)
- Ver todos los permisos disponibles del sistema
- Comprender qué permisos controla qué funcionalidades

## 🔑 Operaciones Protegidas por Permisos

| Operación | Permiso Requerido |
|-----------|-------------------|
| Listar usuarios | USER_LIST |
| Crear usuario | USER_CREATE |
| Editar usuario | USER_EDIT |
| Eliminar usuario | USER_DELETE |
| Asignar rol a usuario | USER_ROLE_ASSIGN |
| Remover rol de usuario | USER_ROLE_REMOVE |
| Listar roles | ROLE_LIST |
| Crear rol | ROLE_CREATE |
| Editar rol | ROLE_EDIT |
| Eliminar rol | ROLE_DELETE |
| Agregar permiso a rol | ROLE_PERMISSION_ADD |
| Remover permiso de rol | ROLE_PERMISSION_REMOVE |
| Listar permisos | PERMISSION_LIST |

## 📝 Ejemplos de Flujo de Trabajo

### Workflow 1: Crear un rol de "Editor" con permisos limitados

1. Iniciar sesión como administrador
2. Ir a Roles → Nuevo Rol
3. Crear rol "EDITOR"
4. Editar el rol recién creado
5. Agregar permisos: USER_LIST, ROLE_LIST, PERMISSION_LIST
6. Guardar cambios

### Workflow 2: Crear un usuario y asignarle el rol "Editor"

1. Iniciar sesión como administrador
2. Ir a Usuarios → Nuevo Usuario
3. Completar formulario del usuario
4. Guardar
5. En la lista de usuarios, click en "Agregar Rol"
6. Seleccionar el rol "EDITOR"
7. Confirmar

### Workflow 3: Usuario "Editor" trabaja en el sistema

1. El usuario "Editor" inicia sesión
2. Solo ve las funciones para las que tiene permisos
3. Puede listar usuarios, roles y permisos
4. No puede crear, editar o eliminar usuarios/roles

## 🛠️ Configuración Personalizada

### Archivo `application.properties`

```properties
# Puerto del servidor
server.port=8080

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:authdb

# Habilitar consola H2
spring.h2.console.enabled=true

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
```

### Cambiar a una Base de Datos Permanente

Para usar una base de datos real en lugar de H2 en memoria:

1. Modificar `application.properties`:
```properties
# Para PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL94Dialect
```

2. Agregar la dependencia en `build.gradle`:
```gradle
runtimeOnly 'org.postgresql:postgresql'
```

## 🧪 Pruebas

Para ejecutar las pruebas:

```bash
./gradlew test
```

## 📚 API de Endpoints

### Públicos (sin autenticación)
- `GET /` - Redirige a login
- `GET /login` - Página de login
- `GET /register` - Página de registro
- `POST /api/register` - Crear nuevo usuario

### Protegidos (requieren autenticación)
- `GET /dashboard` - Dashboard principal
- `GET /users` - Listar usuarios
- `GET /users/new` - Formulario de nuevo usuario
- `POST /users` - Crear usuario
- `GET /users/{id}/edit` - Editar usuario
- `POST /users/{id}/update` - Actualizar usuario
- `POST /users/{id}/delete` - Eliminar usuario
- `POST /users/{userId}/add-role` - Agregar rol a usuario
- `GET /roles` - Listar roles
- `GET /roles/new` - Formulario de nuevo rol
- `POST /roles` - Crear rol
- `GET /roles/{id}/edit` - Editar rol
- `POST /roles/{id}/update` - Actualizar rol
- `POST /roles/{id}/delete` - Eliminar rol
- `POST /roles/{roleId}/add-permission` - Agregar permiso a rol
- `POST /roles/{roleId}/remove-permission` - Remover permiso de rol
- `GET /permissions` - Listar permisos
- `GET /logout` - Cerrar sesión

## 🐛 Solución de Problemas

### El servidor no inicia
- Verificar que el puerto 8080 está disponible
- Verificar que tienes Java 17 o superior instalado

### Error "Usuario no encontrado" al login
- Usar las credenciales del administrador: `admin` / `admin123`

### No puedo ver ciertas opciones de menú
- Algunos elementos solo se muestran si tienes los permisos requeridos
- Solicita al administrador que te asigne los roles necesarios

### Cambios no se guardan
- Asegúrate de tener el permiso para realizar esa acción
- Comproba que no hay errores en la consola

## 📞 Contacto y Soporte

Para reportar problemas o sugerencias, contacta al equipo de desarrollo.

## 📄 Licencia

Este proyecto es proporcionado por ICESI para propósitos educativos.

---

**Última actualización**: 2026-04-08
**Versión**: 1.0.0
