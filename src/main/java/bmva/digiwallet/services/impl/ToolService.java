package bmva.digiwallet.services.impl;

public class ToolService {
	
	public static boolean validarRut(String rut) {
		try {
			rut = rut.toUpperCase().replace("-", "").replace(".", ""); // pasa a mayúscula, Eliminamos el guión, puntos
			String digitoVerificador = rut.substring(rut.length() - 1);
			rut = rut.substring(0, rut.length() - 1); // rut sin rv
			int contador = 2;
			int acumulador = 0;
			for (int i = rut.length() - 1; i >= 0; i--) {
				int digito = Integer.parseInt(rut.substring(i, i + 1));
				acumulador = acumulador + (digito * contador);
				contador++;
				if (contador > 7)
					contador = 2; // reinicia
			}
			int resto = acumulador % 11;
			resto = 11 - resto;
			String digitoEsperado = String.valueOf(resto);
			if (resto > 9) {
				digitoEsperado = (resto == 11) ? "0" : "K";
			}
			return digitoVerificador.equals(digitoEsperado); // compara los dv obtenidos
		} catch (NumberFormatException e) {
			System.out.println("El DNI/RUT no es numérico, reintente:");
			return false;
		}
	}

}
