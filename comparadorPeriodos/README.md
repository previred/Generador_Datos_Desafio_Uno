Solución Desafío 1: Periodos perdidos
=====================================

Para la solución se considero el Nivel 3

Para ejecutar se debe considerar lo siguiente:
- Configurar uri del servicio gdd en el archivo application.properties, por defecto esta el valor http://localhost:8080/periodos/api 
- Generar jar con el comando mvn package
- Ejecutar jar desde la carpeta de destino target con el comando java -jar comparadorPeriodos-0.0.1-SNAPSHOT.jar
- Ejecutar el servicio compare del endpoint http://localhost:8081/comparator-period/compare