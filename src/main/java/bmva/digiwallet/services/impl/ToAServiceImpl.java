package bmva.digiwallet.services.impl;

import java.util.List;

import bmva.digiwallet.services.IToAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmva.digiwallet.models.TypeOfAccount;
import bmva.digiwallet.repository.IToARepository;

@Service
public class ToAServiceImpl implements IToAService {

	@Autowired
	private IToARepository toaRepository;
	
	@Override
	public List<TypeOfAccount> findAll() {
		return toaRepository.findAll();
	}

}
