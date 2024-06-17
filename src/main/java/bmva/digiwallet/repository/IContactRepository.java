package bmva.digiwallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.UserEntity;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long>{
	
	List<Contact> findByUser(UserEntity user);
	
	
	@Query(value = "SELECT * FROM contacts WHERE number = ?1", nativeQuery = true)
    Contact buscarPorNroCuenta(String nrocuenta);
	
}
