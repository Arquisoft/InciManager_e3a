function comprobador() {
	if ($("#category option:selected").text() === "Accidente en Carretera") {
		controlador(false, false, true, true, false);
	} else if ($("#category option:selected").text() === "Inundación") {
		controlador(true, true, true, false, false);
	} else if ($("#category option:selected").text() === "Accidente Aéreo") {
		controlador(false, false, false, false, false);
	} else if ($("#category option:selected").text() === "Incendio") {
		controlador(true, true, true, true, false);
	} else if ($("#category option:selected").text() === "Meteorología") {
		controlador(true, false, false, false, false);
	} else if ($("#category option:selected").text() === "Otro") {
		controlador(false, false, false, false, false);
	}
}

function controlador(vCircu, vViento, presion, humedad, temperatura) {
	$("#drivinVelocity").attr('disabled', vCircu);
	$("#windVelocity").attr('disabled', vViento);
	$("#preasure").attr('disabled', presion);
	$("#humedad").attr('disabled', humedad);
	$("#temperature").attr('disabled', temperatura);
}


function validar(){
	if (isNaN($("#drivinVelocity:text")) && $("#drivinVelocity").prop('disabled')===false){
		alert("Solo se admiten números en las propiedades.");
		event.returnValue=false;
	} else {
		event.returnValue=true;
	}
}