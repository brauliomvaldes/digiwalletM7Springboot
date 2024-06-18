package bmva.digiwallet.services;

import java.math.BigDecimal;
import java.util.List;

import bmva.digiwallet.dto.AccountDto;
import bmva.digiwallet.dto.CuentasDeLaTransferenciaDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.UserEntity;

public interface IAccountService{
	
	Account save(AccountDto account);
	
	List<Account> findByUser(UserEntity user);

	Account buscarPorId(String idCuenta);
	
	Account buscarPorNroCuenta(String nrocuenta);
	
	Account depositar(BigDecimal nuevoBalance, String id);
	
	Account retirar(BigDecimal montoOperacion, String id);
	
	List<Account> findByUserWithoutOneIdAccount(String userId, String idAccount);

	List<CuentasDeLaTransferenciaDto> recolectarCuentasParaLaTransferencia(List<Account> suscuentas, List<Contact> suscontactos);
}
