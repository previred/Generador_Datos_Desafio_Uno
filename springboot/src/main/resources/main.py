import requests

#print("Hola Mundo")

if __name__ == '__main__':
	#Peticion GET Api Periodos 
    url = 'http://127.0.0.1:8080/periodos/api'
    headers = {'Content-Type': 'application/json', 'Accept': 'application/json'}

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
    	response_json = response.json()
    	fechaCreacion = response_json['fechaCreacion']
    	fechaFin = response_json['fechaFin']
    	fechas = response_json['fechas']
		
    	print('Peticion GET Api Periodos')
    	print(response_json)


	#Peticion POST a Rest Service Java - Spring Boot enviando JSON de Api Periodos
    url = 'http://localhost:8084/servicesREST/JP/validarPeriodos'
    headers = {'Content-Type': 'application/json', 'Accept': 'application/json'}

    response = requests.post(url, headers=headers, json=response_json)

    if response.status_code == 200:
    	response_json = response.json()
    	print('Peticion POST a Rest Service Java - Spring Boot enviando JSON de Api Periodos')
    	print(response_json)