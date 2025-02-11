# Descripcion del proyecto

Proyecto que obtiene datos de dos APIs externas y fusiona los datos obtenidos en un formato especifico

## Tabla de actualizaciones

| Fecha | Version | Descripcion |
| :---: | :---: | :---: |
| 05/02/2025 | 1.0.0 | Mapeo de spacex |
| 11/02/2025 | 1.1.0 | Se agrega lista de despegues favoritos |

### Javadoc

Tras clonar el proyecto ir a la carpeta spacex/src/main/resources/documentation y abra el archivo index.html

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

## Endpoints

Aca se describe cada endpoint y su uso

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
