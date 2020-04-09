# Spring boot Periodo (Desafio 1)

Se utiliza:
* Maven 3.6.0 (gestor de dependencias)
* JavaSE-1.8 (librería JRE)
* SpringBoot 2.1.13 (framework de desarrollo)

## Instalar

1. Puese usar eclipse para importar el proyecto. File->Import->Project y seleccionar "Import maven project"
2. O, también puede usar los comandos de maven para compilar y ejecutar


## Ejecutar

1. Arrancar servidor (Botón derecho en el proyecto y Run as->spring boot app) desde eclipse o a través de los comandos de maven.
2. Acceder a la dirección "localhost:9090/api/periodo" desde el navegador
3. Considere que el api en http://localhost:8080/periodos/api debe estar operativa. En caso de cambiar, ajuste la ruta en el .resources.

## Ayuda

* Model (modelo) : carpeta src/main/java/com/luzplaz/example/desafio1/model
* Controllers (controlador): carpeta src/main/java/com/luzplaz/example/desafio1/controller
* Main: clase Deafio1Application en la carpeta src/main/java/com/luzplaz/example/desafio1
* Parámetros de configuración: fichero application.properties en la carpeta /src/main/resources
* Documentación disponible en http://localhost:9090/swagger-ui.html#
