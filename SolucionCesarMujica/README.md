# Mi Solucion Generador Datos Desafio tomando como referencia el Nivel 3 de las soluciones propuestas

Este proyecto expone un API REST que consume un servios Rest de Previred y entrega la siguiente información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”
*fechasFaltantes*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” y no estan en el arreglo de fechas generadas aleatoriamente
Ejemplo.
```json
{"id":1,
"fechaCreacion":"1992-05-01",
"fechaFin":"2017-12-01",
"fechas":["1992-08-01","1992-11-01","1993-03-01","1994-06-01","1994-08-01","1994-09-01","1994-10-01","1994-11-01","1995-04-01","1995-09-01","1996-01-01","1996-03-01","1996-06-01","1996-10-01","1997-01-01","1998-02-01","1998-04-01","1998-06-01","1998-09-01","1998-10-01","1998-11-01","1999-07-01","1999-12-01","2000-01-01","2000-02-01","2000-08-01","2000-09-01","2001-03-01","2001-06-01","2001-07-01","2001-08-01","2001-11-01","2001-12-01","2002-03-01","2002-05-01","2002-07-01","2002-10-01","2002-11-01","2003-01-01","2003-03-01","2003-10-01","2004-03-01","2004-05-01","2004-06-01","2004-07-01","2004-10-01","2005-09-01","2005-10-01","2006-01-01","2006-05-01","2006-09-01","2006-11-01","2006-12-01","2007-01-01","2007-08-01","2007-10-01","2008-05-01","2008-08-01","2008-10-01","2009-09-01","2010-06-01","2010-07-01","2010-09-01","2010-10-01","2010-11-01","2011-02-01","2011-03-01","2011-05-01","2011-08-01","2011-09-01","2011-12-01","2012-03-01","2012-04-01","2012-05-01","2012-08-01","2013-01-01","2013-07-01","2013-08-01","2014-08-01","2014-11-01","2015-03-01","2015-06-01","2015-07-01","2015-08-01","2015-10-01","2015-12-01","2016-03-01","2016-04-01","2016-05-01","2016-06-01","2017-04-01","2017-06-01","2017-08-01","2017-10-01"],
"fechasFaltantes":["1992-05-01","1992-06-01","1992-07-01","1992-09-01","1992-10-01","1992-12-01","1993-01-01","1993-02-01","1993-04-01","1993-05-01","1993-06-01","1993-07-01","1993-08-01","1993-09-01","1993-10-01","1993-11-01","1993-12-01","1994-01-01","1994-02-01","1994-03-01","1994-04-01","1994-05-01","1994-07-01","1994-12-01","1995-01-01","1995-02-01","1995-03-01","1995-05-01","1995-06-01","1995-07-01","1995-08-01","1995-10-01","1995-11-01","1995-12-01","1996-02-01","1996-04-01","1996-05-01","1996-07-01","1996-08-01","1996-09-01","1996-11-01","1996-12-01","1997-02-01","1997-03-01","1997-04-01","1997-05-01","1997-06-01","1997-07-01","1997-08-01","1997-09-01","1997-10-01","1997-11-01","1997-12-01","1998-01-01","1998-03-01","1998-05-01","1998-07-01","1998-08-01","1998-12-01","1999-01-01","1999-02-01","1999-03-01","1999-04-01","1999-05-01","1999-06-01","1999-08-01","1999-09-01","1999-10-01","1999-11-01","2000-03-01","2000-04-01","2000-05-01","2000-06-01","2000-07-01","2000-10-01","2000-11-01","2000-12-01","2001-01-01","2001-02-01","2001-04-01","2001-05-01","2001-09-01","2001-10-01","2002-01-01","2002-02-01","2002-04-01","2002-06-01","2002-08-01","2002-09-01","2002-12-01","2003-02-01","2003-04-01","2003-05-01","2003-06-01","2003-07-01","2003-08-01","2003-09-01","2003-11-01","2003-12-01","2004-01-01","2004-02-01","2004-04-01","2004-08-01","2004-09-01","2004-11-01","2004-12-01","2005-01-01","2005-02-01","2005-03-01","2005-04-01","2005-05-01","2005-06-01","2005-07-01","2005-08-01","2005-11-01","2005-12-01","2006-02-01","2006-03-01","2006-04-01","2006-06-01","2006-07-01","2006-08-01","2006-10-01","2007-02-01","2007-03-01","2007-04-01","2007-05-01","2007-06-01","2007-07-01","2007-09-01","2007-11-01","2007-12-01","2008-01-01","2008-02-01","2008-03-01","2008-04-01","2008-06-01","2008-07-01","2008-09-01","2008-11-01","2008-12-01","2009-01-01","2009-02-01","2009-03-01","2009-04-01","2009-05-01","2009-06-01","2009-07-01","2009-08-01","2009-10-01","2009-11-01","2009-12-01","2010-01-01","2010-02-01","2010-03-01","2010-04-01","2010-05-01","2010-08-01","2010-12-01","2011-01-01","2011-04-01","2011-06-01","2011-07-01","2011-10-01","2011-11-01","2012-01-01","2012-02-01","2012-06-01","2012-07-01","2012-09-01","2012-10-01","2012-11-01","2012-12-01","2013-02-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-09-01","2013-10-01","2013-11-01","2013-12-01","2014-01-01","2014-02-01","2014-03-01","2014-04-01","2014-05-01","2014-06-01","2014-07-01","2014-09-01","2014-10-01","2014-12-01","2015-01-01","2015-02-01","2015-04-01","2015-05-01","2015-09-01","2015-11-01","2016-01-01","2016-02-01","2016-07-01","2016-08-01","2016-09-01","2016-10-01","2016-11-01","2016-12-01","2017-01-01","2017-02-01","2017-03-01","2017-05-01","2017-07-01","2017-09-01","2017-11-01","2017-12-01"]}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd (la estructura mostrada en el ejemplo corresponde a una salida real de la ejecucion de mi solucion)
El servicio entrega un Json con los parametros de entrada y asu vez una lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” y no estan en el arreglo de fechas generadas aleatoriamente

# Detalle de los sistemas

Swagger 2.9.2 con 
Java 8
Spring-Core 5.2.5
Maven 3.6.3
Tomcat 9
Eclipse 4.16

# Compilar y ejecutar el proyecto

Para compilar el proyecto se requiere Java, tomcat(de manera practica) y Maven instalado. es un servicio Rest en Java, 
importar proyecto en Eclipse como un proyecto Maven Existente, generar el WAR utilizando los comandos "clean compile package" por comodidad 
y practicidad instale un tomcat para desplegar el proyecto (habilite el puerto 8081 para no chocar con el servicio de Previred)

una vez desplegado invocar la URL

http://localhost:8081/ValidarFechasRest/services/ValidarFechasPrevired/validarFechasJSON

*Nota*:
Deben estar disponibles los puertos *8080 y 8081* en el PC donde se ejecute esta API y el tomcat(o el servidor de su preferencia)

# Visualizar Documentación y consumir la API

La verdad es que nunca he trabajado con SWAGGER y aunque intente configurarlo me imagino que no levanta si no se ejecuta con SpringBot, de poder visualizarse deberia verse en

http://127.0.0.1:8081/swagger-ui.html#/

*Nota*:
En el archivo JsonIO.txt se encuantran los Json de entrada y salida para una corrida del codigo.

Mi experiencia con Git, SpringBot y SWAGGER es nula, con Rest he trabajo muy poco, investigando pude lograr llegar a este punto, espero que de igual manera esto denote mi interes en aprender algo nuevo y de seguir creciendo profesionalmente, antes que todo gracias por la oportunidad!