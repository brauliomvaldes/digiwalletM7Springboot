package bmva.digiwallet.services;

import java.math.BigDecimal;
import java.util.List;

import bmva.digiwallet.dto.MovimientoDto;
import bmva.digiwallet.dto.TransactionDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Transaction;

public interface ITransactionService {

	Transaction saveTransaction(TransactionDto transactionDto);
	
	Transaction registrarOperacionEnTransacciones(String tipoOperacion, BigDecimal montoOperacion, Account cuenta);
	
	List<Transaction> findByIdSender(String id_sender);
	
	List<Transaction> findByIdUserAndIdAccount(String id_user, String id_account);
	
	List<MovimientoDto> mapeoMovimientos(List<Transaction> transactions, String idUsuario, String nroCuenta);
	
}
