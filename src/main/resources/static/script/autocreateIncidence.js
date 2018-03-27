$(document).ready(function() {    
    function load(){
    	var http = new XMLHttpRequest();
    	var url = "/inci";
    	//aquí hay que meter los valores aleatorios
    	
    	http.open("POST", url, true);
    	http.setRequestHeader("Content-Type", "application/json");

    	
    	//http.onreadystatechange = function() {
    	  //  if(http.readyState == 4 && http.status == 200) { 
    	       //aqui obtienes la respuesta de tu peticion
    	    //   alert(http.responseText);
    	    //}
    	//}
    	var myDate = new Date();
    	var fechaEntrada = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	myDate.setDate(15);
    	var fechaCaducidad = myDate.getUTCDate() + "/" + (myDate.getMonth()+1) + "/" + myDate.getFullYear();
    	
    	crearST(fechaEntrada, fechaCaducidad, http);
    	crearSH(fechaEntrada, fechaCaducidad, http);
    	
    }
	
    function crearST(fechaActual, fechaCaducidad, http){
    	
    	
    	http.send(JSON.stringify({
  		  login: "Agente1",
  		  password: "123456",
  		  nombreIncidencia: "Temperatura de sala de ordenadores",  
  		  descripcion: "Sensor automático ",  
  		  kind: "person",  
  		  fechaEntrada: fechaActual, 
  		  fechaCaducidad: fechaCaducidad,
  		  categorias: "fuego,meteorologica,accidente_carretera",
  		  propiedades: "temperatura/80,presion/50,humedad/30,velocidad_circulacion/120"  
  		}));
    } 
    
    function crearSH(fechaActual, fechaCaducidad, http){
    	
    }
    
    setInterval(load, 5000);
});