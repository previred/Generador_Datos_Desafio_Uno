# Generador Datos Desafio

Este proyecto expone un API REST que entrega la siguiente información:
	-id: identificador 
	-fechaCreacion: Fecha de inicio de la secuencia 
	-fechaFin: Fecha de fin de la secuencia 
	-fechas: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
	-fechasFaltantes: Lista de fechas 'perdidas'. Corresponde a las fechas que fueron omitidas durante el proceso de generación aleatoria dentro del rango de fecha de la secuencia.
	
Ejemplo:

	{
	    "id": 1,
	    "fechaCreacion": "1990-11-01",
	    "fechaFin": "2015-06-01",
	    "fechas": [
	        "1990-11-01",
	        "1991-01-01",
	        "1991-03-01",
	        "1991-07-01",
	        "1991-11-01",
	        .
	        .
	    ],
	    "fechasFaltates": [
	        "1990-12-01",
	        "1991-02-01",
	        "1991-04-01",
	        "1991-05-01",
	        "1991-06-01",
	        .
	        .        
	    ]
	}

# Detalle de los Sistemas
Java 8 Spring-Boot 1.9.RELEASE
Maven 3

# Compilar y ejecutar 
Para copilar el proyecto se requiere Java y Maven instalado. Ingresar al directorio 'api-desafio-uno' y ejecutar el siguiente comando maven:
    
    mvn package

Luego de compilar el proyecto ingresar al directorio target ejecutar el siguiente comando java:

    java -jar .\api-desafio-uno-0.0.1-SNAPSHOT.jar
    
Nota: 
 1) Realizar despliegue de proyecto Generador_Datos_Desafio_Uno de acuerdo a lo indicado en:
    https://github.com/previred/Generador_Datos_Desafio_Uno
 2) Debe estar disponible el puerto 8081 en el PC donde se ejecute esta API


# Consumo del servicio

Para consumir el servicio se debe invocar la siguiente URL:
 
	curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8081/desafio/periodosFaltanes'