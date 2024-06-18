package bmva.digiwallet.services;

import bmva.digiwallet.dto.CuentasDeLaTransferenciaDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Contact;
import org.springframework.data.repository.query.Param;

import bmva.digiwallet.dto.UserDto;
import bmva.digiwallet.models.UserEntity;

import java.util.List;

public interface IUserService {

	UserEntity save(UserDto userDto);
	
	UserEntity buscaUsuarioPorEmailYPassword(String email, String password);
	
	UserEntity findByEmail(String email);
	
	UserEntity findByIdUsuario(@Param("id") String id);

}
