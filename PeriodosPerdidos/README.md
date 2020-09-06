# Requerimientos de sistema

Java 8
Spring-Boot 1.5.11.RELEASE
Maven 3

# Compilar y ejecutar el proyecto Periodos Faltantes
Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *PeriodosFaltantes* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\PeriodosPerdidos-0.0.1.jar
```
*Nota*:
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API y debe estar corriendo el proyecto *ApiPeriodos* en el puerto 8080

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/periodos/swagger-ui.html#/

Para consumir el servicio se debe invocar la siguiente URL:

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodos/missing'
```