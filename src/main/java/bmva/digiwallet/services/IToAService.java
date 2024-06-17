package bmva.digiwallet.services;

import java.util.List;

import bmva.digiwallet.models.TypeOfAccount;

public interface IToAService {

	List<TypeOfAccount> findAll();
}
