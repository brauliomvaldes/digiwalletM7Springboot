// Alternar entre destino de las transferencias

$(document).ready(function() {

	// key para acceder a tipos de cammbio
	let key = "279566e6b5cbe95619058dca";
	// fecha actual
	let fechaHoy = new Date();
	let dia = fechaHoy.getDate().toString().padStart(2, '0');
	let mes = (fechaHoy.getMonth() + 1).toString().padStart(2, '0'); // Enero es 0
	let año = fechaHoy.getFullYear();

	// pinta fecha
	$(".titulo-fecha").append(`<h4>Fecha de Hoy ${dia}/${mes}/${año}<h4>`);

	// escucha cambio de tipo de cuenta
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

	// calcula total de la transferencia
	$("#monto-transferir").change(function() {
		recalculo();
	});

	// calcula total de la transferencia
	$("#factor-conversion").change(function() {
		recalculo();
	});
	
	// calcula el monto real a transferir
	const recalculo = ()=>{
		let monto = parseInt($("#monto-transferir").val());
		let factor = parseFloat($("#factor-conversion").val());
		if ((!isNaN(monto) && monto > 0) && (!isNaN(factor) && factor > 0)) {
			// limpia lista elementos creados para los destinatarios
			limpiar();
			let total = monto * factor;
			$("#total-transferir").append(`<h3>$ ${total.toFixed(2)}</h3>`);
		}else{
			swal("Error!", "Revise los montos ingresados", "error");
		}
	}
	

	// limpia lista elementos creados para los destinatarios
	const limpiar = () => {
		const list = document.getElementById("total-transferir");
		while (list.hasChildNodes()) {
			list.removeChild(list.firstChild);
		}
	}

	// tasas de conversion monetaria
	// pesos a dolares	
	$.getJSON(`https://v6.exchangerate-api.com/v6/${key}/pair/CLP/USD`, function(data) {
		var tasa = data;
		$("#tipos-conversion").append(
			'<tr class="table"><td>1 CLP -> ' + tasa.conversion_rate + ' USD</td><td><button class="btn btn-success" id="btn-clp-usd"><<</button></td></tr>');
		// selecciona el boton creado
		const boton = document.querySelector("#btn-clp-usd");
		// crea evento click al botón seleccionado
		boton.addEventListener("click", function() { cambiarFactor(tasa.conversion_rate) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// euros a dolares
	$.getJSON(`https://v6.exchangerate-api.com/v6/${key}/pair/EUR/USD`, function(data) {
		var tasa = data;
		$("#tipos-conversion").append(
			'<tr class="table"><td>1 EUR -> ' + tasa.conversion_rate + ' USD</td><td><button class="btn btn-success" id="btn-eur-usd"><<</button></td></tr>');
		// selecciona el boton creado
		const boton = document.querySelector("#btn-eur-usd");
		// crea evento click al botón seleccionado
		boton.addEventListener("click", function() { cambiarFactor(tasa.conversion_rate) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// dolares a euros
	$.getJSON(`https://v6.exchangerate-api.com/v6/${key}/pair/USD/EUR`, function(data) {
		var tasa = data;
		$("#tipos-conversion").append(
			'<tr class="table"><td>1 USD -> ' + tasa.conversion_rate + ' EUR</td><td><button class="btn btn-success" id="btn-usd-eur"><<</button></td></tr>');
		// selecciona el boton creado
		const boton = document.querySelector("#btn-usd-eur");
		// crea evento click al botón seleccionado
		boton.addEventListener("click", function() { cambiarFactor(tasa.conversion_rate) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// indicadores económicos
	$.getJSON('https://mindicador.cl/api', function(data) {
		var dailyIndicators = data;
		$("#indicadores").append(
			'<tr class="table"><td>valor U.F.  $ ' + dailyIndicators.dolar.valor + '</td><td><button class="btn btn-success" id="btn-usd-clp"><<</button></td></tr>' +
			'<tr class="table"><td>valor U.F.  $ ' + dailyIndicators.euro.valor + '</td><td><button class="btn btn-success" id="btn-eur-clp"><<</button></td></tr>' +
			'<tr class="table"><td> - </td><td> - </td></tr>' +
			'<tr class="table"><td>valor U.F.  $ ' + dailyIndicators.uf.valor +  '</td><td></td> - </tr>' +
			'<tr class="table"><td>valor UTM   $ ' + dailyIndicators.utm.valor + '</td><td></td> - </tr>'
		);
		// selecciona el boton creado
		const boton4 = document.querySelector("#btn-usd-clp");
		// crea evento click al botón seleccionado
		boton4.addEventListener("click", function() { cambiarFactor(dailyIndicators.dolar.valor) });
		// selecciona el boton creado
		const boton5 = document.querySelector("#btn-eur-clp");
		// crea evento click al botón seleccionado
		boton5.addEventListener("click", function() { cambiarFactor(dailyIndicators.euro.valor) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// evalua el indicador o tasa seleccionado para acualizar el factor de conversión monetaria
	const cambiarFactor = (factor) => {
		$('#factor-conversion').val(factor);
		recalculo();
	}

	// intercepta transferencia, submit del formualario para pedir confirmación
	$('#form-transferencia').on('submit', function(event) {
		event.preventDefault(); // Detiene el envío del formulario
		swal({
			title: "¿ Esta seguro de transferir ?",
			text: "Si cuenta origen y destino tiene misma moneda, el factor de conversión será igual a 1 independientemente de los factores señalados en el formulario.\nUna vez realizada la transferencia, no es posible reversar los fondos",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
			.then((confirmado) => {
				if (confirmado) {
					this.submit(); // Continúa con el envío del formulario si es válido					
				} else {
					swal("¡ Usuario ha cancelado la transferencia !");
				}
			});
	});

});