package bmva.digiwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmva.digiwallet.models.Bank;

@Repository
public interface IBankRepository extends JpaRepository<Bank, Long>{

}
