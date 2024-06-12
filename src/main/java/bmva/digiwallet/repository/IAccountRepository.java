package bmva.digiwallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.UserEntity;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String>{

	public List<Account> findByUser(UserEntity user);

	@Query(value = "SELECT * FROM accounts WHERE id = ?1", nativeQuery = true)
    public Account buscarPorId(String id);
	
	@Query(value = "SELECT * FROM accounts WHERE number = ?1", nativeQuery = true)
    public Account buscarPorNroCuenta(String nrocuenta);
	
	@Query(value = "SELECT * FROM accounts WHERE user_id = ?1 AND id != ?2", nativeQuery = true)
	public List<Account> findByUserWithoutOneIdAccount(String userId, String idAccount);
	
}
