# Descripción del proyecto

Proyecto que obtiene datos de dos APIs externas y fusiona los datos obtenidos en un formato especifico

## Documentacion

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

### GET /launches

Obtiene todos los lanzamientos de SpaceX

#### Ejemplo de respuesta JSON

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
