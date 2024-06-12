package bmva.digiwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmva.digiwallet.models.TypeOfAccount;

@Repository
public interface IToARepository extends JpaRepository<TypeOfAccount, Long>{

}
