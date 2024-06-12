package bmva.digiwallet.services;

import java.util.List;

import bmva.digiwallet.models.Bank;

public interface IBankService {

	public List<Bank> findAll();
}
