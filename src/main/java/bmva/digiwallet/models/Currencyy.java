package bmva.digiwallet.models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Currencyy{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name; 
	private String symbol; 
	private boolean state;
	// Relación uno a muchos con la entidad Cuenta
    @OneToMany(mappedBy = "currencyy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<Account>();
	
}
