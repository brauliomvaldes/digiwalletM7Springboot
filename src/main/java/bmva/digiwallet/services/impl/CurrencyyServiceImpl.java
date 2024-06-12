package bmva.digiwallet.services.impl;

import java.util.List;

import bmva.digiwallet.services.ICurrencyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmva.digiwallet.models.Currencyy;
import bmva.digiwallet.repository.ICurrencyyRepository;

@Service
public class CurrencyyServiceImpl implements ICurrencyyService {

	@Autowired
	private ICurrencyyRepository currencyyRepository;
	
	@Override
	public List<Currencyy> findAll() {
		return currencyyRepository.findAll();
	}

}
