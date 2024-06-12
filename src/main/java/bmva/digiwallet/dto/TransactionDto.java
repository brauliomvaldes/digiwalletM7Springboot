package bmva.digiwallet.dto;

import java.math.BigDecimal;

import bmva.digiwallet.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDto {
	private BigDecimal amount; // monto de la transferencia 
	private BigDecimal factoramount; // monto de la transferencia aplicado factor, va al destino
	private String detail; 
	private Account account; // cuenta destino
	private Account sender; // cuenta origen
	private String number; // nuero de la transaccion, se genera aleatoriamente
	private BigDecimal factor;
}
