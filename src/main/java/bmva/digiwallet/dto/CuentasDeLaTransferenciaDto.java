package bmva.digiwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentasDeLaTransferenciaDto {
    private String idAccount;
    private String numberAccount;
    private String symbolCurrency;
}
