// Alternar entre destino de las transferencias

$(document).ready(function() {
	$('#destinotransferencia').change(function() {
		const selectedValue = $(this).val();
		if (selectedValue === 'PROPIAS') {
			$('#cuentaspropias').show();
			$('#cuentasterceros').hide();
		} else {
			$('#cuentaspropias').hide();
			$('#cuentasterceros').show();
		}
	});
	
	// tasas de conversion monetaria	
	$.getJSON('https://v6.exchangerate-api.com/v6/279566e6b5cbe95619058dca/pair/CLP/USD', function(data) {
		var tasa = data;	
		let fechaHoy = new Date();
		let dia = fechaHoy.getDate().toString().padStart(2, '0');
		let mes = (fechaHoy.getMonth() + 1).toString().padStart(2, '0'); // Enero es 0
		let año = fechaHoy.getFullYear();
		$("<p/>", {
			html: '<h3>Tasa Peso Chileno a Dolar</h3><h4>'+
			'Fecha de Hoy '+`${dia}/${mes}/${año}`+
			'<table class="table table-align-center table-striped table-bordered">'+
			'<tr><td>CLP->USD ' + tasa.conversion_rate+'</td></tr>'+
			'</table></h4>'
		}).appendTo(".tasas");
	}).fail(function() {
		console.log('Error al consumir la API!');
	});
	
	// indicadores económicos

	$.getJSON('https://mindicador.cl/api', function(data) {
		var dailyIndicators = data;
		
		$("<p/>", {
			html: '<h3>Indicadores Económicos</h3><h5>'+
			'<table class="table table-align-center table-striped table-bordered">'+
			'<tr><td>valor Dolar $ ' + dailyIndicators.dolar.valor+'</td></tr>'+
			'<tr><td>valor Euro  $ ' + dailyIndicators.euro.valor+'</td></tr>'+
			'<tr><td>valor U.F.  $ ' + dailyIndicators.uf.valor+'</td></tr>'+
			'<tr><td>valor UTM   $ ' + dailyIndicators.utm.valor+'</td></tr>'+
			'</table></h5>'
		}).appendTo(".indicadores");
	}).fail(function() {
		console.log('Error al consumir la API!');
	});


});