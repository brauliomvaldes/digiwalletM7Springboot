package bmva.digiwallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoDto {
	
	private String trf;
	private Date fecha;
	private String tipo; 
	private String numero;
	private BigDecimal monto;
	private String detalle;
}
