[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6f1eae5c3b7749a3ba299ae6c548e3a9)](https://app.codacy.com/app/ameliafb/InciManager_e3a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e3a&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e3a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e3a)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e3a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e3a)


# InciManagement_e3a
InciManagement_e3a

#Instrucciones

Este módulo recoge incidencias enviadas por Agentes de tipo sensor (por servicio rest) y de tipo persona, entidad (vía formu
lario web).
La aplicación funciona con una bd MongoDB (no hay que hacer nada al respecto).
La autenticación de estos agentes se realiza mediante el módulo Agentes_e3a, con lo que hay que descargarlo y arrancarlo
para que este módulo funcione correctamente.

Para probar hay que tener funcionando:
módulo Agents_e3a, InciManager_e3a, y un servidor kafka

1.- Enviar una incidencia desde un sensor:
  Desde el Advanced Rest Cliente de Google (por ejemplo) enviar una petición post a al url: http://localhost:8090/inci
En el cuerpo escribir la incidencia en formato JSON:

{
  "login": "Agente1",
  "password": "123456",
  "nombreIncidencia": "fuego en bosque con cabañas",
  "descripcion": "Fuego en bosque en zona anexa a carretera N634 en pk513",
  "kind": "person",
  "fechaEntrada": "25/03/2018",
  "fechaCaducidad": "19/03/2018",
  "categorias": "fuego,meteorologica,accidente_carretera",
  "propiedades": "temperatura/80,presion/50,humedad/30,velocidad_circulacion/120"
}

Si la autenticación del agente es correcta se guardará la incidencia en la BD y se enviará a kafka.
Las categorías están predefinidas, actualmente, para probar hay: accidente_carretera, fuego, inundacion, accidente_aereo, 
meteorologica
Y las propiedades también: temperatura, presion, humedad, velocidad_viento, velocidad_circulacion
	
2.- Enviar una incidencia vía web o consultar las enviadas:
conectarse a: localhost:8990
usuario: Agente1, password: 123456, tipo: person


# Authors
- Saúl Castillo Valdés (@saulcasti)
- Pelayo Díaz Soto (@PelayoDiaz)
- Amelia Fernández Braña (@ameliafb)
- Francisco Javier Riedemann Wistuba (@FJss23)

