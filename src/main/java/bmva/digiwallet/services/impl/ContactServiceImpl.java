package bmva.digiwallet.services.impl;

import java.util.List;

import bmva.digiwallet.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmva.digiwallet.dto.ContactoDto;
import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.repository.IContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	@Autowired
	private IContactRepository contactRepository;
	
	@Override
	public List<Contact> findByUser(UserEntity user) {
		return contactRepository.findByUser(user);
	}

	@Override
	public Contact crearNuevoContacto(ContactoDto contactoDto) {
		Contact nuevoContacto = new Contact();
		nuevoContacto.setNumber(contactoDto.getNumber());
		nuevoContacto.setAlias(contactoDto.getAlias());
		nuevoContacto.setUser(contactoDto.getUser());
		nuevoContacto.setBank(contactoDto.getBank());
		nuevoContacto.setCurrencyy(contactoDto.getCurrencyy());
		nuevoContacto.setState(true);
		return contactRepository.save(nuevoContacto);
	}

	@Override
	public Contact buscarPorNroCuenta(String nrocuenta) {
		return contactRepository.buscarPorNroCuenta(nrocuenta);
	}

}
