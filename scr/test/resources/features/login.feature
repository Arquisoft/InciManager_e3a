# language: en

Feature: Poder hacer login con un agente

	Scenario: Hacer petición post de login con un agente

		Given un agente registrado en la base de datos
		When el agente intenta hacer login con el usuario "Agente1", la clave "123456" y kind "person"
		Then consigue hacer login 
		And el agente es redireccionado a la página "Home"