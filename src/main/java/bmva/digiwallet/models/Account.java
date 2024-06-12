package bmva.digiwallet.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {
	@Id
	@Column(name = "id")
	private String id;   // .setId(UUID.randomUUID().toString());
	// Relación muchos a uno con la entidad usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
	private UserEntity user;
	private String number;
	private BigDecimal balance;
	// Relación muchos a uno con la entidad currencyy
    @ManyToOne
    @JoinColumn(name = "currencyy_id")
	private Currencyy currencyy;
	private Date created_at;
	// Relación muchos a uno con la entidad type_of_account
    @ManyToOne
    @JoinColumn(name = "toa_id")
	private TypeOfAccount toa;
    //Relación muchos a uno con la entidad banks
    @ManyToOne
    @JoinColumn(name = "bank_id")
	private Bank bank;
	private boolean state;
	
	// Relación uno a muchos con la entidad Transaction Cuenta Sender
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionsSender = new ArrayList<Transaction>();
    
 // Relación uno a muchos con la entidad Transaction Cuenta Receiver
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionsReceiver = new ArrayList<Transaction>();

}
