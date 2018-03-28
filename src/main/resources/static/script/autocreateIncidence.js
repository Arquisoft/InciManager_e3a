$(document).ready(function() { 
	function generateRequest(){
		var http = new XMLHttpRequest();
    	var url = "/inci";
    	
    	http.open("POST", url, true);
    	http.setRequestHeader("Content-Type", "application/json");
    	return http;
	}
	function simularSensorDeZoo(){
    	var myDate = new Date();
    	var fechaActual = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	myDate.setDate(4);
    	var fechaCaducidad = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	generateRequest().send(JSON.stringify({
  		  login: "S2",
  		  password: "123456",
  		  nombreIncidencia: "Sensor de zoológico",  
  		  descripcion: "Sensor automático que se encuentra en el zoológico",  
  		  kind: "sensor",  
  		  fechaEntrada: fechaActual, 
  		  fechaCaducidad: fechaCaducidad,
  		  categorias: "automatico,ambiente",
  		  propiedades: "temperatura/"+ getRandomArbitrary(-50, 100) +",presion/"+ getRandomArbitrary(0, 2) +",humedad/" + getRandomArbitrary(0, 101)  
  		}));   
    } 
	
    function simularSensorDeServidores(){
    	var myDate = new Date();
    	var fechaActual = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	myDate.setDate(15);
    	var fechaCaducidad = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	generateRequest().send(JSON.stringify({
  		  login: "S1",
  		  password: "123456",
  		  nombreIncidencia: "Sensor de la sala de servidores del edifio central",  
  		  descripcion: "Sensor automático que se encuentra en la sala de servidores del edificio central",  
  		  kind: "sensor",  
  		  fechaEntrada: fechaActual, 
  		  fechaCaducidad: fechaCaducidad,
  		  categorias: "automatico,ambiente",
  		  propiedades: "temperatura/"+ getRandomArbitrary(-50, 100) +",presion/"+ getRandomArbitrary(0, 2) +",humedad/" + getRandomArbitrary(0, 101)  
  		}));   
    } 
    
    function getRandomArbitrary(min, max) {
    	  return Math.random() * (max - min) + min;
    	}
    
    
    setInterval(simularSensorDeServidores, 4995);
    setInterval(simularSensorDeZoo, 4997);
});