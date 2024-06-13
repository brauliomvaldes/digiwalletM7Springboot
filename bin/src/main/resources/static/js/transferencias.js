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

	$.getJSON('https://mindicador.cl/api', function(data) {
		var dailyIndicators = data;
		
		$("<p/>", {
			html: '(El valor actual del Dolar es $' + dailyIndicators.dolar.valor+')'
		}).appendTo(".valordolar");
	}).fail(function() {
		console.log('Error al consumir la API!');
	});


});


