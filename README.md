# Descripcion del proyecto

Este proyecto fue desarrollado como parte de un challenge técnico. Se trata de una API REST construida con Java y Spring Boot que interactúa con la API pública de SpaceX para gestionar lanzamientos espaciales.

Además de consultar los datos, permite gestionar favoritos de forma local para simular una funcionalidad más completa tipo "wishlist".

---

## 🎯 Objetivos del proyecto

- Consumir una API pública externa (SpaceX)
- Aplicar arquitectura por capas (Controller / Service / Model)
- Manejo de DTOs para desacoplar capas
- Funcionalidad adicional como favoritos
- Buenas prácticas con Spring Boot

---


## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Lombok
- RestTemplate
- Maven

---

### Javadoc

Tras clonar el proyecto ir a la carpeta spacex/src/main/resources/documentation y abra el archivo index.html

---

## Uso

Clonar el proyecto

```bash
  git clone https://github.com/devgaf/spacex.git
```

Ir al directorio del proyecto

```bash
  cd spacex
```

Instalar depndencias

```bash
  mvn clean install
```

Poner en marcha el servidor

```bash
  mvn spring-boot:run
```

---

## 📚 Estructura general

```
src/
├── controller         # Controladores REST
├── dto                # Objetos de transferencia de datos
├── model              # Entidades y clases de dominio
├── service            # Lógica de negocio
├── repository         # Interfaces JPA
└── utils              # Clases auxiliares (ej: mapeadores)
```

---

## Endpoints

Aca se describe cada endpoint y su uso

La API se ejecuta por defecto en:  
👉 `http://localhost:8080/`

### Resumen de endpoints

**🚀 Lanzamientos**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/launches` | Obtiene todos los lanzamientos |
| GET    | `/launches/{id}` | Detalle de lanzamiento por ID |
| GET    | `/launches/name/{name}` | Filtra por nombre |
| GET    | `/launches/date/{year}` | Filtra por año de lanzamiento |

**⭐ Favoritos**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| PUT    | `/launches/{id}` | Marca un lanzamiento como favorito |
| GET    | `/favorites` | Lista los lanzamientos marcados como favoritos |
| DELETE | `/favorites/{id}` | Elimina un favorito |

### Detalles de endpoints

**GET /launches**
Obtiene todos los lanzamientos de SpaceX

**Ejemplo de respuesta JSON**

```json
[
    {
        "flight_number": int,
        "mission_name": string,
        "details": string,
        "rocket": {
            "rocket_id": string,
            "rocket_name": string,
            "active": boolean,
            "cost_per_launch": long,
            "company": string
        }
    },
]

```

**GET /favorites**

Obtiene todos los lanzamientos favoritos

**Ejemplo de respuesta JSON**

```json
[
    {
        "flight_number": int,
        "mission_name": string,
        "details": string,
        "rocket": {
            "rocket_id": string,
            "rocket_name": string,
            "active": boolean,
            "cost_per_launch": long,
            "company": string
        }
    },
]
```

**PUT /favorites/{flight_number}**

Agrega un lanzamiento a la lista de favoritos.
Se recibe el numero de vuelo como parametro se busca en la lista de lanzamientos y se agrega a la lista de favoritos
Retorna la lista de favoritos actualizada

**Ejemplo de respuesta JSON**

```json
[
    {
        "flight_number": int,
        "mission_name": string,
        "details": string,
        "rocket": {
            "rocket_id": string,
            "rocket_name": string,
            "active": boolean,
            "cost_per_launch": long,
            "company": string
        }
    },
]
```

**DELETE /favorites/{flight_number}**

Elimina un lanzamiento de la lista de favoritos.
Se recibe el numero de vuelo como parametro se busca en la lista de lanzamientos y se agrega a la lista de favoritos
Retorna la lista de favoritos actualizada

**Ejemplo de respuesta JSON**

```json
[
    {
        "flight_number": int,
        "mission_name": string,
        "details": string,
        "rocket": {
            "rocket_id": string,
            "rocket_name": string,
            "active": boolean,
            "cost_per_launch": long,
            "company": string
        }
    },
]
```

---

## 🌍 API externa utilizada

[SpaceX API](https://github.com/r-spacex/SpaceX-API)

---

## Autor

### Hola, soy Gaston Fernandez👋
>
> *Desarrollador de sistemas*

Puede vistiar mi LinkedIn (<https://www.linkedin.com/in/gastonfdz>)

### 🛠 Skills

- Java
  - Spring Boot
  - JPA
- SQL
- Angular
- Bootstrap

---

## Tabla de actualizaciones

| Fecha | Version | Descripcion |
| :---: | :---: | :---: |
| 05/02/2025 | 1.0.0 | Mapeo de spacex |
| 11/02/2025 | 1.1.0 | Se agrega lista de despegues favoritos |
| 07/04/2025 | 1.1.1 | Se modifica ReadMe para una mejor orgnaizacion y visualizacion de la informacion |

---
