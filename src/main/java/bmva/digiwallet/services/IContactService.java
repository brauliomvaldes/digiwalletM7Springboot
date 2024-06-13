package bmva.digiwallet.services;

import java.util.List;

import bmva.digiwallet.dto.ContactoDto;
import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.UserEntity;

public interface IContactService {

	public List<Contact> findByUser(UserEntity user);
	
	public Contact crearNuevoContacto(ContactoDto contactoDto);
	
	public Contact buscarPorNroCuentaYUsuario(String nrocuenta, String userId);
}
