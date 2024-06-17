package bmva.digiwallet.services;

import java.util.List;

import bmva.digiwallet.dto.ContactoDto;
import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.UserEntity;

public interface IContactService {

	List<Contact> findByUser(UserEntity user);
	
	Contact crearNuevoContacto(ContactoDto contactoDto);
	
	Contact buscarPorNroCuentaYUsuario(String nrocuenta, String userId);
}
