package bmva.digiwallet.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	private String id;  
	private String firstname; 
	private String lastname; 
	private String identity;  
	private String email;
	private String username; 
	private String password; 
	private Date created; 
	private boolean state;
	private String role;
	
	// constructor sin el id
 
	// constructor sólo con email
	public UserDto(String email) {
		super();
		this.email = email;
	}

	// parael registro de usuarios
	public UserDto(String firstname, String lastname, String identity, String email, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.identity = identity;
		this.email = email;
		this.role = role;
	} 
	
	// para pasar a la vista
	public UserDto(String firstname, String lastname, String identity, String email, String username, String password,
			Date created, boolean state, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.identity = identity;
		this.email = email;
		this.username = username;
		this.password = password;
		this.created = created;
		this.state = state;
		this.role = role;
	}
	
}
