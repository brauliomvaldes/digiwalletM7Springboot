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
		let fechaHoy = new Date();
		let dia = fechaHoy.getDate().toString().padStart(2, '0');
		let mes = (fechaHoy.getMonth() + 1).toString().padStart(2, '0'); // Enero es 0
		let año = fechaHoy.getFullYear();
		
		$("<p/>", {
			html: '<h2>Indicadores Económicos</h2><h4>'+
			'Fecha de Hoy '+`${dia}/${mes}/${año}`+
			'<table class="table table-align-center table-striped table-bordered">'+
			'<tr><td>valor Dolar $ ' + dailyIndicators.dolar.valor+'</td></tr>'+
			'<tr><td>valor Euro  $ ' + dailyIndicators.euro.valor+'</td></tr>'+
			'<tr><td>valor U.F.  $ ' + dailyIndicators.uf.valor+'</td></tr>'+
			'<tr><td>valor UTM   $ ' + dailyIndicators.utm.valor+'</td></tr>'+
			'<tr><td>valor IPC   % ' + dailyIndicators.ipc.valor+'</td></tr>'+
			'</table></h4>'
		}).appendTo(".indicadores");
	}).fail(function() {
		console.log('Error al consumir la API!');
	});


});