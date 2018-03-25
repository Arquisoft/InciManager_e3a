# language: es

Característica: Poder hacer login con un agente

	Escenario: Hacer petición post de login con un agente

		Dado un agente registrado en la base de datos
		Cuando el agente intenta hacer login con el usuario "Agente1", la clave "123456" y kind "person"
		Entonces consigue hacer login 
		Y el agente es redireccionado a la página "Home"
		
Característica: Poder ver incidencias con un agente iniciado en sesión

	Escenario: Seleccionar la opcion de "Ver Incidencias" de la barra de navegación

		Dado un agente iniciado en sesión
		Cuando el agente intenta acceder a las incidencias que envia
		Entonces es redireccionado a la página "list"
		Y consigue ver sus incidencias
		
Característica: Poder enviar incidencias con un agente iniciado en sesión

	Escenario: Seleccionar la opcion de "Enviar Incidencias" de la barra de navegación y rellenar el formulario

		Dado un agente iniciado en sesión
		When el agente intenta acceder a enviar incidencias 
		Entonces es redireccionado a la página "create"
		Y el puede rellanar el formulario
		Y ser redirigido a la página "Ver incidencias" donde ver la incidencia creada
		
Característica: Poder ver los detalles de las incidencias con un agente iniciado en sesión

	Escenario: Poder acceder a los detalles de cada incidencia desde la pagina "List"

		Dado un agente iniciado en sesión
		When el agente intenta ver los detalles de una incidencia
		Entonces es redireccionado a la página "details"
		Y el puede ver los detalles de la incidencia