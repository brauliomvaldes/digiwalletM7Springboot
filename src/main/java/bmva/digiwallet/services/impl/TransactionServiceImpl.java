package bmva.digiwallet.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import bmva.digiwallet.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bmva.digiwallet.dto.MovimientoDto;
import bmva.digiwallet.dto.TransactionDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Transaction;
import bmva.digiwallet.repository.ITransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private ITransactionRepository transactionRepository;
	
	@Transactional
	public Transaction saveTransaction(TransactionDto transactionDto) {
		
		Random rand = new Random(); 
		Transaction transaction = new Transaction();
		
		transaction.setId(UUID.randomUUID().toString());
		// generar número de la transaferencia
		Integer nroTransferencia = rand.nextInt(1000000); // el nro trf será aleatorio para fines prácticos
		transaction.setNumber(nroTransferencia.toString());
		transaction.setAmount_sender(transactionDto.getAmount());
		transaction.setAmount_receiver(transactionDto.getFactorAmount());
		transaction.setDate(new Date());
		transaction.setDetail(transactionDto.getDetail());
		transaction.setState(true);
		transaction.setSender(transactionDto.getSender());
		transaction.setReceiver(transactionDto.getAccount());
		 
		return transactionRepository.save(transaction);
	}
	
	public List<MovimientoDto> mapeoMovimientos(List<Transaction> transactions, String idCuenta, String nroCuenta) {
		// lista de movimiento de la misma cuenta
		// que figura como sender o receiver
		List<MovimientoDto> tfrs = new ArrayList<>();
		transactions.forEach(t->{
			MovimientoDto trf = new MovimientoDto();
			// recupera id de las cuentas referidas en la transferencia
			String idCuentaSender = t.getSender().getId();
			
			// si la cuenta aparece en sender y receiver es una operacion de retirar/depositar
			if(t.getSender().getNumber().equals(t.getReceiver().getNumber())) {
				// corresponde a una operación
				if(t.getAmount_sender().compareTo(BigDecimal.ZERO)==0) {
					// abono de dinero
					trf.setMonto(t.getAmount_receiver());
					trf.setTipo("abono de dinero");
				}else {
					// retiro de dinero
					// muestra valor en negativo
					trf.setMonto(BigDecimal.ZERO.subtract(t.getAmount_sender()));
					trf.setTipo("retiro de dinero");
				}
				
			}else {
				// evalue si la cuenta aparece como sender o salida de dinero
				// si es asi, el monto en sender debe figurar negativo
				if(idCuentaSender.equals(idCuenta)) {
					// salida de dinero de la cuenta
					// tipo giro
					trf.setTipo("traspaso de dinero");
					// registra el monto en valor negativo
					trf.setMonto(BigDecimal.ZERO.subtract(t.getAmount_sender()));   // muestra el monto que sale de la cuenta sender
					// toma la cuenta del receptor del dinero
					trf.setNumero(t.getReceiver().getNumber());
					
				}else {
					// entrada de dinero
					// tipo abono
					trf.setTipo("recepcion de dinero");
					trf.setMonto(t.getAmount_receiver());  // muestra el monto que estra a la cuenta receiver
					// toma la cuenta del emisor del dinero
					trf.setNumero(t.getSender().getNumber());
				}				
			}
			trf.setCuenta(nroCuenta);  // identifica cada movimiento con su cuenta afectada 
			trf.setTrf(t.getNumber()); // referencia de la cuenta que ocasiona el movimiento
			trf.setFecha(t.getDate());
			trf.setDetalle(t.getDetail());
			tfrs.add(trf);
		});
		return tfrs;
	}

	@Override
	public List<Transaction> findByIdUserAndIdAccount(String id_user, String id_account) {
		return transactionRepository.findByIdUserAndIdAccount(id_user, id_account);
	}

	@Override
	public List<Transaction> findByIdSender(String id_sender) {
		return transactionRepository.findByIdSender(id_sender);
	}

	@Override
	@Transactional
	public Transaction registrarOperacionEnTransacciones(String tipoOperacion, BigDecimal montoOperacion,
			Account cuenta) {
		Random rand = new Random(); 
		Transaction transaction = new Transaction();
		
		transaction.setId(UUID.randomUUID().toString());
		// generar número de la transaferencia
		Integer nroTransferencia = rand.nextInt(1000000); // el nro trf será aleatorio para fines prácticos
		transaction.setNumber(nroTransferencia.toString());
		transaction.setDate(new Date());
		transaction.setState(true);
		if(tipoOperacion.equals("SUMAR")) {
			// se ingresa dinero a la cuenta 
			transaction.setDetail("abono directo o dinero recibido");
			// simula entreda de dinero, receiver simula sender que envia el dinero
			transaction.setAmount_sender(BigDecimal.ZERO);
			transaction.setAmount_receiver(montoOperacion);
		}else{
			// se resta dinero de la cuenta
			transaction.setDetail("retiro o giro de dinero");
			// simula salida de dinero, sender envia dinero
			transaction.setAmount_sender(montoOperacion);
			transaction.setAmount_receiver(BigDecimal.ZERO);
		}
		
		transaction.setSender(cuenta);
		transaction.setReceiver(cuenta);
		
		return transactionRepository.save(transaction);
	}

	
	
}
