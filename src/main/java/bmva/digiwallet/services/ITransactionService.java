package bmva.digiwallet.services;

import java.math.BigDecimal;
import java.util.List;

import bmva.digiwallet.dto.MovimientoDto;
import bmva.digiwallet.dto.TransactionDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Transaction;

public interface ITransactionService {

	public Transaction saveTransaction(TransactionDto transactionDto);
	
	public Transaction registrarOperacionEnTransacciones(String tipoOperacion, BigDecimal montoOperacion, Account cuenta);
	
	public List<Transaction> findByIdSender(String id_sender);
	
	public List<Transaction> findByIdUserAndIdAccount(String id_user, String id_account);
	
	public List<MovimientoDto> mapeoMovimientos(List<Transaction> transactions, String idUsuario);
	
}
