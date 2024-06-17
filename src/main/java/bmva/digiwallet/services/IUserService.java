package bmva.digiwallet.services;

import org.springframework.data.repository.query.Param;

import bmva.digiwallet.dto.UserDto;
import bmva.digiwallet.models.UserEntity;

public interface IUserService {

	UserEntity save(UserDto userDto);
	
	UserEntity buscaUsuarioPorEmailYPassword(String email, String password);
	
	UserEntity findByEmail(String email);
	
	UserEntity findByIdUsuario(@Param("id") String id);
}
