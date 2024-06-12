package bmva.digiwallet.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import bmva.digiwallet.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bmva.digiwallet.dto.AccountDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.repository.IAccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;
	
	@Transactional
	public Account save(AccountDto accountdto) {
		String uuid = UUID.randomUUID().toString();
		Account account = new Account();
		account.setId(uuid);
		account.setBalance(BigDecimal.ZERO);
		account.setBank(accountdto.getBank());
		account.setCreated_at(new Date());
		account.setCurrencyy(accountdto.getCurrencyy());
		account.setNumber(accountdto.getNumber());
		account.setState(true);
		account.setToa(accountdto.getToa());
		account.setUser(accountdto.getUser());
		return accountRepository.save(account);
	}

	@Override
	public List<Account> findByUser(UserEntity user) {
		return accountRepository.findByUser(user);
	}

	@Override
	public Account buscarPorId(String idCuenta) {
		return accountRepository.buscarPorId(idCuenta);
	}

	@Override
	public Account buscarPorNroCuenta(String nrocuenta) {
		return accountRepository.buscarPorNroCuenta(nrocuenta);
	}
	
	@Override
	public Account depositar(BigDecimal montoOperacion, String id) {
		Account cuenta = accountRepository.buscarPorId(id);
		if(cuenta!=null) {
			BigDecimal balance = cuenta.getBalance();
			cuenta.setBalance(balance.add(montoOperacion));
			accountRepository.save(cuenta);
		}
		return cuenta;
	}
	
	@Override
	public Account retirar(BigDecimal montoOperacion, String id) {
		Account cuenta = accountRepository.buscarPorId(id);
		if(cuenta!=null) {
			BigDecimal balance = cuenta.getBalance();
			cuenta.setBalance(balance.subtract(montoOperacion));
			accountRepository.save(cuenta);
		}
		return cuenta;
	}

	@Override
	public List<Account> findByUserWithoutOneIdAccount(String userId, String idAccount) {
		return accountRepository.findByUserWithoutOneIdAccount(userId, idAccount);
	}

}
