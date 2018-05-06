# language: es

Característica: Ser capaz de iniciar sesion como Agente
	
	Como agente registrado en el sitema quiero poder acceder al mismo para crear incidencias

	Escenario: Login
		Dado un agente de nombre "Agente1" contraseña "123456" y kind "person" registrado en el sistema
		Y situado en la página "/login"
		Cuando hago login con usuario "Agente1" y password "123456" y kind "person" introduciendo los datos en los campos
		Entonces soy redireccionada a la página "/home"