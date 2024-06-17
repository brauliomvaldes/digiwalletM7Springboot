package bmva.digiwallet.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import jakarta.persistence.*; 

import lombok.Data;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity {
	
	@Id
	private String id;   // .setId(UUID.randomUUID().toString());
	private String firstname; 
	private String lastname; 
	private String identity;  
	private String email;
	private String username; 
	private String password; 
	private Date created; 
	private boolean state;
	private String role;
	// solicitadas por springsecurity
	@Column(name="is_enabled")
	private boolean isEnabled;
	@Column(name="account_no_expired")
	private boolean accountNoExpired;
	@Column(name="account_no_locked")
	private boolean accountNoLocked;
	@Column(name="credential_no_expired")
	private boolean credentialNoExpired;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name= "user_roles", joinColumns = @JoinColumn(name= "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<RoleEntity> roles = new HashSet<>();
	
	
	// Relación uno a muchos con la entidad Cuenta
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<Account>();
    
 // Relación uno a muchos con la entidad Contacto
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<Contact>();
    

	public UserEntity() {}

    // para usar con DTO
	public UserEntity(String firstname, String lastname, String identity, String email, String username, String password,
					  Date created, boolean state, List<Account> accounts, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.identity = identity;
		this.email = email;
		this.username = username;
		this.password = password;
		this.created = created;
		this.state = state;
		this.accounts = accounts;
		this.role = role;
	}
    
}
