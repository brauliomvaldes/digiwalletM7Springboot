package bmva.digiwallet.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidaRutTest {

	// validación con ingreso de rut válidos
	@ParameterizedTest
	@ValueSource(strings = { "3903815-3", "15151688-2", "21810438-k", "9125518-9", "13944528-7", "25520204-9",
			"9421531-5", "19302248-0", "23591913-3", "5862429-2", "17108732-5", "28061582-K", "4457031-9", "14365689-6",
			"29696919-2", "6096809-8", "10057330-K", "21864286-1", "4829135-K", "10757005-5", "27914756-1", "4471845-6",
			"13226569-0", "29280539-K", "6042182-k", "14772134-k", "27822871-1", "3791638-2", "19401299-3",
			"25087899-0" })
	void testTrueRut(String rut) {
		assertAll(() -> assertTrue(ToolService.validarRut(rut)));
	}
	
	// validación con ingreso de rut no válidos
	@ParameterizedTest
	@ValueSource(strings = { "3903815-0", "15151688-a", "21810439-k", "9125518-0", "13944528-b", "25520205-9",
			"9421531-0", "19302248-c", "23591914-3", "5862429-0", "17108732-d", "28061583-k", "4457031-0", "14365689-e",
			"29696910-2", "6096809-0", "10057330-f", "21864287-1", "4829135-0", "10757005-g", "27914757-1", "4471845-0",
			"13226569-h", "29280530-k", "6042182-0", "14772134-i", "27822872-1", "3791638-0", "19401299-j3",
			"25087891-0" })
	void testFalseRut(String fakerut) {
		assertAll(() -> assertFalse(ToolService.validarRut(fakerut)));
	}
	
}
