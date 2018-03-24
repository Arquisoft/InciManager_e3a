function controlador(boolean vCircu, boolean vViento, 
					boolean presion, boolean humedad, boolean temperatura){
	$("#drivinVelocity").attr('disabled', vCircu);
	$("#windVelocity").attr('disabled', vViento);
	$("#preasure").attr('disabled', presion);
	$("#humedad").attr('disabled', humedad);
	$("#temperature").attr('disabled', temperatura);
}

$(document).ready(function(){
	$("#category").change(function(){
		if ($("#category option:selected").text()==="Otro"){
			$("#tags").attr('disabled', false).focus();
		} else {
			$("#tags").attr('disabled', true);
		}
		if ($("#category option:selected").text()==="Accidente en Carretera"){
			controlador(true, false, false, false, false);
		}
	})
}, 
function controlador(boolean vCircu, boolean vViento, 
		boolean presion, boolean humedad, boolean temperatura){
	$("#drivinVelocity").attr('disabled', vCircu);
	$("#windVelocity").attr('disabled', vViento);
	$("#preasure").attr('disabled', presion);
	$("#humedad").attr('disabled', humedad);
	$("#temperature").attr('disabled', temperatura);
});