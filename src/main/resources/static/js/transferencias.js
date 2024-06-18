
$(document).ready(function() {
	// key para acceder a tipos de cammbio
	let key = "279566e6b5cbe95619058dca";
	// inicializa monto a transferir en cero
	let inicializador = 1;
	$("#monto-transferir").val(inicializador);
	$("#factor-conversion").val(inicializador);

	// fecha actual
	let fechaHoy = new Date();
	let dia = fechaHoy.getDate().toString().padStart(2, '0');
	let mes = (fechaHoy.getMonth() + 1).toString().padStart(2, '0'); // Enero es 0
	let año = fechaHoy.getFullYear();
	// pinta fecha
	$(".titulo-fecha").append(`<h4>Fecha de Hoy ${dia}/${mes}/${año}<h4>`);

    // cambia
    const checkCuentasPropias = ()=>{
        let cuentaseleccionada = $("#selectpropias").val();
        listadocuentas.forEach(function(cuenta) {
            // busca la id seleccionada en el listado de cuentas para transferir
            if(cuenta.idAccount === cuentaseleccionada){
                // si la moneda destino es la misma del usuario origen
                if(moneda === cuenta.symbolCurrency){
                    console.log("mismas monedas entre sus cuentas");
                    // oculta indicadores
                    desactivaIndicadores();
                }else{
                    // muestra indicadores
                    activaIndicadores();
                }
            }
        });
    }

    // cambia
    const checkCuentasContactos = ()=>{
        let cuentaseleccionada = $("#selectterceros").val();
        listadocuentas.forEach(function(cuenta) {
            // busca la id seleccionada en el listado de cuentas para transferir
            if(cuenta.numberAccount === cuentaseleccionada){
                // si la moneda destino es la misma del usuario origen
                if(moneda === cuenta.symbolCurrency){
                    // oculta indicadores
                    desactivaIndicadores();
                }else{
                    // muestra indicadores
                    activaIndicadores();
                }
            }
        });
    }

    // muestra indicadores
    const activaIndicadores = ()=>{
            $("#tipos-indicadores").removeAttr("hidden");
            $("#tipos-indicadores").removeAttr("disabled");
            $("#factor-conversion").removeAttr("disabled");
    }
    // oculta indicadores
    const desactivaIndicadores = ()=> {
            $('#tipos-indicadores').prop('disabled', true);
            $("#tipos-indicadores").prop("hidden", true);
            $("#factor-conversion").prop("disabled", true);
            // factor a 1
            $("#factor-conversion").val(inicializador);
            // por modificar el factor
            recalculo();
    }

// Alternar entre destino de las transferencias
	// escucha cambio de tipo de cuenta
	$('#destinotransferencia').change(function() {
		const selectedValue = $(this).val();
		if (selectedValue === 'PROPIAS') {
			$('#cuentaspropias').show();
			$('#cuentasterceros').hide();
			checkCuentasPropias();
		} else {
			$('#cuentaspropias').hide();
			$('#cuentasterceros').show();
			checkCuentasContactos();
		}
	});
	// escucha cambio de cuentas
	$('#selectpropias').change(function(){
	    checkCuentasPropias();
	});
    $('#selectterceros').change(function(){
        checkCuentasContactos();
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
			$("#total-transferir").append(`<h3> ${total.toFixed(2)}</h3>`);
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
			'<tr class="table"><td>CLP >> USD factor: ' + tasa.conversion_rate + '</td><td><button class="btn btn-success" id="btn-clp-usd"><<</button></td></tr>');
		// selecciona el boton creado
		const boton = document.querySelector("#btn-clp-usd");
		// crea evento click al botón seleccionado
		boton.addEventListener("click", function() { cambiarFactor(tasa.conversion_rate) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// pesos a euros
	$.getJSON(`https://v6.exchangerate-api.com/v6/${key}/pair/CLP/EUR`, function(data) {
		var tasa = data;
		$("#tipos-conversion").append(
			'<tr class="table"><td>CLP >> EUR factor: ' + tasa.conversion_rate + '</td><td><button class="btn btn-success" id="btn-clp-eur"><<</button></td></tr>');
		// selecciona el boton creado
		const boton = document.querySelector("#btn-clp-eur");
		// crea evento click al botón seleccionado
		boton.addEventListener("click", function() { cambiarFactor(tasa.conversion_rate) });
	}).fail(function() {
		console.log('Error al consumir la API!');
	});

	// euros a dolares
	$.getJSON(`https://v6.exchangerate-api.com/v6/${key}/pair/EUR/USD`, function(data) {
		var tasa = data;
		$("#tipos-conversion").append(
			'<tr class="table"><td>EUR >> USD factor: ' + tasa.conversion_rate + '</td><td><button class="btn btn-success" id="btn-eur-usd"><<</button></td></tr>');
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
			'<tr class="table"><td>USD >> EUR factor: ' + tasa.conversion_rate + '</td><td><button class="btn btn-success" id="btn-usd-eur"><<</button></td></tr>');
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
			'<tr class="table"><td>valor Dolar</td><td>$' + dailyIndicators.dolar.valor + '</td></tr>' +
			'<tr class="table"><td>valor Euro</td><td>$' + dailyIndicators.euro.valor + '</td></tr>' +
			'<tr class="table"><td>valor U.F.</td><td>$' + dailyIndicators.uf.valor + '</td></tr>' +
			'<tr class="table"><td>valor U.T.M.</td><td>$' + dailyIndicators.utm.valor + '</td></tr>'
		);
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
			text: "Si cuenta origen y destino tiene misma moneda, el factor será igual a 1 descartando otros valores.\n\nUna vez realizada la transferencia, no es posible reversar los fondos",
			icon: "info",
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

    // por modificar el factor al inicio
    recalculo();
	// check inicial
	checkCuentasContactos();
	checkCuentasPropias();

});