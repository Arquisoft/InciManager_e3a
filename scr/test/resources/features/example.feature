# language: es

Característica: Poder hacer login con un agente

	Escenario: Hacer petición post de login con un agente

		Dado un agente registrado en la base de datos
		Cuando el agente intenta hacer login con el usuario "Pepe" y la clave "Pepe"
		Entonces consigue hacer login 
		Y el agente es redireccionado a la página "Perfil de usuario"