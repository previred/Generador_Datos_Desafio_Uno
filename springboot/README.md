# Respuesta al Desafio Generador Datos
Este proyecto expone un Servicio REST que recibe la estructura de un periodo comprendido por una fecha de creación, una fecha fin y una lista de fechas dentro del rango de fechas, e internamente valida y devuelve una lista de fechas faltantes comprendidas entre la fecha de creacion y fecha fin del periodo que no se encuentren en la lista de fechas del periodo de entrada.  Recibe mediante POST un archivo JSON y entrega como respuesta un archivo JSON que contienen los datos del original mas las fechas faltantes.

El Servicio recibe la siguiente información:
- *id*: identificador
- *fechaCreacion*: Fecha de inicio de la secuencia
- *fechaFin*: Fecha de fin de la secuencia
- *fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
- El formato de las fechas es yyyy-MM-dd

-Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1968-08-01",
    "fechaFin": "1971-06-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1971-05-01"]
}
```

El Servicio entrega la siguiente información:
- *id*: identificador
- *fechaCreacion*: Fecha de inicio de la secuencia
- *fechaFin*: Fecha de fin de la secuencia
- *fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
- *fechasFaltantes*: Lista de fechas comprendidas en el rango de fechas que se encuentra entre  “fechaCreacion” y “fechaFin” faltantes dentro de la lista *fechas*.
- El formato de las fechas es yyyy-MM-dd

-Ejemplo.
```json
{
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
      "1969-03-01",
      "1969-05-01",
      "1969-09-01",
      "1970-01-01"],
    "fechasFaltantes": [
      "1969-04-01",
      "1969-06-01",
      "1969-07-01",
      "1969-08-01",
      "1969-10-01",
      "1969-11-01",
      "1969-12-01"]

}
```

# Detalle de los sistemas

Java 8
Spring-Boot 2.0.5.RELEASE
Maven 3.6.2


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *springboot* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar springboot-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8084* en el PC donde se ejecutará el servicio

# Uso de Python para la ejecucion del programa
Adicional a la solicion realizada, al iniciar la aplicacion se expone mediante un servicio REST un metodo para la generacion mediante POST (recibiendo un JSON como estrada), de manera de que pueda ser probada la funcionalidad a través de python o realizando prueba con alguna herramienta de testing. Use para las pruebas SoapUI.

Para ejecutar el python se debe descargar el archivo (ubicado eb la carpeta resources) en el computador donde se probará la funcionalidad (tengo pytho 3.8.0). Este debe tener instalado Python.
-Ejecutar el comando
```bash
python3 main.py
```
El script puthon contiene la llamada al Generador de Codigos y con la salida JSON realiza llamada POST al servicio REST, y se obtendrá como salida el JSON inicial junto al JSON final resultante.
