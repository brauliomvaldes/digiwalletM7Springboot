package bmva.digiwallet.services.impl;

import java.util.List;

import bmva.digiwallet.services.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmva.digiwallet.models.Bank;
import bmva.digiwallet.repository.IBankRepository;

@Service
public class BankServiceImpl implements IBankService {
	
	@Autowired
	private IBankRepository bankRepository;

	@Override
	public List<Bank> findAll() {
		return bankRepository.findAll();
	}

}
