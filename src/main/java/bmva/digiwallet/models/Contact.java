package bmva.digiwallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class Contact {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// Relación muchos a uno con la entidad usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
	private UserEntity user;
	private String number;
	private String alias;
	private String bank;
	private String currencyy;
	private boolean state;

}
