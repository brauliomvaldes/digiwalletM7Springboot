package bmva.digiwallet.services;

import java.util.List;

import bmva.digiwallet.models.TypeOfAccount;

public interface IToAService {

	public List<TypeOfAccount> findAll();
}
