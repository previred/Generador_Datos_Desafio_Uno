
# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\api-periodos-1.0.0.jar
```
*Nota*:
Debe estar disponible el puerto *8080* en el PC donde se ejecute esta API

# Generador Datos Desafio

*Nota*: no olvidar un rago de fechas no muy acotados, de lo contrario modificar ls variables de la PeriodosPerdidosSerice.java MAX - MIN
  Ejemplo:
  {
      "fechaCreacion": "2010-01-01",
      "fechaFin": "2019-01-01",
  }
Este proyecto expone un API REST que entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” de manera aleatoria
Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "2001-01-01",
    "fechaFin": "2010-01-01",
    "fechas": [
      "2010-03-01",
      "2010-05-01",
      "2010-09-01",
      "2001-05-01"]
      "fechasFaltantes": [
      "2010-04-01",
      "2010-06-01",
      "2010-07-01",
      "2001-08-01"]
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd
El servicio entrega una lista fechas con un minimo de 90 a 100 registros aleatorios.
Luego se deben exponer todas las fechas faltates con respecto al rango de las fechas aleatoria.
Se suguiere usar un rango de fechas establecidos para respetar las variables que dicen que puedes des como min 90 por el tema de cantidad de registros.

# Detalle de los sistemas

Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 1.5.11.RELEASE
Maven 3


# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8080/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8080/periodos/api'
```

