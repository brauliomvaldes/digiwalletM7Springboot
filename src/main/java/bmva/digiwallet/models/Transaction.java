package bmva.digiwallet.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
	
	@Id
	private String id;  // .setId(UUID.randomUUID().toString());
	private String number;
	private BigDecimal amount_sender; 
	private BigDecimal amount_receiver;
	private Date date;
	private String detail; 
	private boolean state; 

	//Relación muchos a uno con la entidad account sender
    @ManyToOne
    @JoinColumn(name = "sender_id")
	private Account sender; 
    
  //Relación muchos a uno con la entidad account receiver
    @ManyToOne
    @JoinColumn(name = "receiver_id")
	private Account receiver; 
		
}
