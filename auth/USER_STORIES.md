# Especificaciones de Desarrollo - Sistema de Gestión Académica

Este documento consolida las historias de usuario, casos de uso, arquitectura técnica y estrategias de prueba implementadas en el proyecto.

---

## 1. Arquitectura Técnica y Estándares (1 pto)

### 1.1 REST-FULL API
El sistema sigue los principios de una API REST-FULL:
- **Recursos:** Identificados por URLs sustantivas (ej: `/api/courses`).
- **Métodos HTTP:** Uso correcto de `GET` (lectura), `POST` (creación), `PUT` (actualización completa), `PATCH` (actualización parcial) y `DELETE` (eliminación).
- **Códigos de Estado:** `200 OK`, `201 Created`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, etc.
- **Stateless:** La autenticación se maneja vía JWT, no se mantiene sesión en el servidor.

### 1.2 Mappers y DTOs (MapStruct)
- **Desacoplamiento:** Las entidades de JPA (`model/`) nunca se exponen directamente al cliente.
- **DTOs:** Objetos especializados para entrada y salida (`dtos/`).
- **Mapeo Automático:** Uso de interfaces `@Mapper` (MapStruct) para transformar datos de forma segura y eficiente en la capa de controlador.

---

## 2. Historias de Usuario (HU) y Criterios de Aceptación (CA)

### HU1: Gestión Académica de Cursos (Admin)
**Descripción:** Como administrador, quiero gestionar los cursos para mantener la oferta educativa.
- [  ] **CA1.1:** Crear curso con código único, nombre y créditos.
- [  ] **CA1.2:** Asignar un profesor a un curso mediante su ID.
- [  ] **CA1.3:** Listar todos los cursos incluyendo detalles del profesor y estudiantes.

### HU2: Gestión de Actividades (Profesor)
**Descripción:** Como profesor, quiero crear actividades evaluativas para mis cursos.
- [  ] **CA2.1:** Crear actividad ligada a un curso con fecha límite y peso porcentual.
- [  ] **CA2.2:** Ver lista de actividades por curso.

### HU3: Entregas de Estudiantes (Estudiante)
**Descripción:** Como estudiante, quiero subir mis tareas para ser evaluado.
- [  ] **CA3.1:** Crear una entrega (`Submission`) para una actividad específica.
- [  ] **CA3.2:** Actualizar el contenido de una entrega antes de la fecha límite.

### HU4: Evaluación y Notas (Profesor)
**Descripción:** Como profesor, quiero calificar las entregas y dar feedback.
- [  ] **CA4.1:** Calificar una entrega (`grade`) y añadir comentarios (`feedback`).
- [  ] **CA4.2:** El sistema debe restringir esta acción solo a usuarios con rol `PROFESSOR` o `ADMIN`.

---

## 3. Casos de Uso (Use Cases) (2 pto)

Impelemtación de las HU

- **Caso de Uso: Autenticación**
    - **Actor:** Usuario (Admin, Profesor, Estudiante).
    - **Flujo:** El usuario envía credenciales -> El sistema valida -> Devuelve un Token JWT.
- **Caso de Uso: Matrícula Express**
    - **Actor:** Admin/Profesor.
    - **Flujo:** Selecciona curso -> Selecciona estudiante -> El sistema vincula ambos registros en la BD.
- **Caso de Uso: Calificación de Tarea**
    - **Actor:** Profesor.
    - **Flujo:** Ver entregas de la actividad -> Seleccionar una entrega -> Ingresar nota y comentario -> El sistema actualiza y persiste.

---

## 4. Configuración de Seguridad (1 pto)

Debe usar JWT como metodo de authenticación

---

## 5. Estrategia de Pruebas (1 pto)

### 5.1 Pruebas con Postman / REST Client

Realice al menos 3 pruebas en Postman usando variables.
