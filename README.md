<p align="center">
  <img src="LogoTC.jpg" width="180" title="Tech Consult">
</p>

*Nota*: 
-El Nuevo metodo de la API desarrollado para este Desafio, fue realizado como se indico en el NIVEL 3 agregando un nuevo metodo a la API
-Se implementa el uso de ApiException para procesar excepciones de la clase ApiApiController mediante el uso del @ExceptionHandler y suministrar información del error.
-Se implementan nuevas propiedades en el aplication.Propertie las cuales se usan como valiablesclase FileProperties
-Se cambia la ejecución del proyecto al Puerto 8081 dentro del properties
-Se Implementa la clase UtilTransform para transladar un codigo existente con la finalidad de reutilizarlo para el nuevo desarrollo.
-Se implemente la clase GenerarArchivo para arrojar un archivo en formato .txt con el resultado del proceso del Servicio /PeriodosFaltantes
-La ubicación del archivo y nombre del archivo se encuentran coniguradas en el aplicacion.properties.
-Se respeta la estrutura del Json Solicitado; sin embargo considero que para un mejor manejo de las excepciones el objeto salida podria contener la siguientes estructura, permitiendo en caso de error retornar un mismo objeto pero con un codigo de errro, un mensaje y PeriodosFaltantes vacio o con la información suministrada por el servicio GDD
Ejemplo:```json
{	"Codigo": 200
	"Mensaje": "OK"
	"PeriodoFaltantes":{
		"id": 1,
		"fechaCreacion": "1986-08-01",
		"fechaFin": "1987-05-01",
		"fechas": [
			"1986-08-01",
			"1986-09-01",
			"1986-11-01",
			"1987-03-01"
			],
		"fechasFaltantes": [
			"1986-10-01",
			"1986-12-01",
			"1987-01-01",
			"1987-02-01",
			"1987-04-01",
			"1987-05-01",
			]
		}
}
```



#"Edwyn Mayorga" Implementación del Desafio
Se incluye en la API REST un nuevo metodo @GET  /apiPeriodosFaltantes que entrega la siguiente Información
*id*: Ientificador (Obtenido de la respuesta del servidio GDD)
*fechaCreacion*: Fecha de inicio de la Secuencia (Obtenido de la respuesta del servidio GDD)
*fechaFin*: Fecha fin de la secuencia (Obtenido de la respuesta del servidio GDD)
*fechas*: Listado de fechas cuyo rango corresponde la las dechan entre 'fechaCreacion' y 'fechaFin'  (Obtenido de la respuesta del servidio GDD)
*fechasFaltantes*: Listado de fechas faltantes entre 'fechaCreacion' y 'fechaFin'  y que no se recibieron en el atributo 'fechas' 
Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "1986-08-01",
    "fechaFin": "1987-05-01",
    "fechas": [
        "1986-08-01",
        "1986-09-01",
        "1986-11-01",
        "1987-03-01"
		],
    "fechasFaltantes": [
        "1986-10-01",
        "1986-12-01",
		"1987-01-01",
		"1987-02-01",
		"1987-04-01",
		"1987-05-01",
		]
}
```
*Nota*: 
El servicio responde las fechas faltantes que no fueron calculadas por el Servicio GDD
Genera un archivo enformato .txt con el Json de entrada entregado por el servicio GDD y con los periodos faltantes calculado, el archivo lo deja ubicado en la carpeta target del proyecto ( la información del archivo y su ubicación puede ser modificada en el aplication.properties"


# Detalle de los sistemas

Swagger Codegen 2.3.1 (OpenApi 2.0)
Java 8
Spring-Boot 2.5.3
Maven 3

# "Edwyn Mayorga" Antes de compilar 
se debe gatantizar que la configuración del archivo settings.xml se encuentre direccionando al repositorio https://repo1.maven.org/maven2/

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
Debe estar disponible el puerto *8081* en el PC donde se ejecute esta API o en su defecto Modificar el puerto en el aplication.properties

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8081/periodos/swagger-ui.html#/

Para consumir el servicio del GDD se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodos/api'
```

Para consuir el nuevo servicio de la API se debe Invacar la siguiente URL
```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/periodos/apiPeriodosFaltantes'
```

# Regenerar API a partir de yaml
Esta sección me genero errores no me tomaba las nuevas clases y los paquetes que corresponden a los archivos que agregue