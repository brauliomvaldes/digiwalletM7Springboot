package bmva.digiwallet.dto;

import bmva.digiwallet.models.Bank;
import bmva.digiwallet.models.Currencyy;
import bmva.digiwallet.models.TypeOfAccount;
import bmva.digiwallet.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
	private UserEntity user;
	private String number;
	private Currencyy currencyy;
	private TypeOfAccount toa;
	private Bank bank;
}
