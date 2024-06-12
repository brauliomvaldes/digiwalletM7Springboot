package bmva.digiwallet.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import bmva.digiwallet.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import bmva.digiwallet.dto.UserDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Role;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional
	public UserEntity save(UserDto userDto) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
		UserEntity user = new UserEntity();
		user.setId(UUID.randomUUID().toString());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setIdentity(userDto.getIdentity());
		user.setCreated(new Date());
		user.setUsername(userDto.getEmail());
		user.setState(true);
		user.setCredentialNoExpired(true);
		user.setAccountNoExpired(true);
		user.setEnabled(true);
		user.setAccountNoLocked(true);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setAccounts(new ArrayList<Account>());
		user.setRole(Role.ROLE_USER.toString());
		try {
		    // Realiza la operación de guardado		    
			return userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
		    // Maneja la excepción de violación de integridad de datos
		    System.out.println("Se produjo una violación de integridad de datos: " + e.getMessage());
		    // Realiza acciones adicionales según sea necesario
		} catch (JpaSystemException e) {
		    // Maneja otras excepciones específicas de JPA
			System.out.println("Se produjo un error específico de JPA: " + e.getMessage());
		    // Realiza acciones adicionales según sea necesario
		} catch (Exception e) {
		    // Maneja cualquier otra excepción no específica
			System.out.println("Se produjo un error inesperado: " + e.getMessage());
		    // Realiza acciones adicionales según sea necesario
		}
		return null;
	}

	@Override
    public UserEntity buscaUsuarioPorEmailYPassword(String email, String rawPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null; // si el usuario no se encuentra o la contraseña no coincide
    }

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
 
}
