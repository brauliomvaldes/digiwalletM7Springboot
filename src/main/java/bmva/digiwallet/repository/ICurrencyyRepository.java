package bmva.digiwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmva.digiwallet.models.Currencyy;

@Repository
public interface ICurrencyyRepository extends JpaRepository<Currencyy, Long>{

}
