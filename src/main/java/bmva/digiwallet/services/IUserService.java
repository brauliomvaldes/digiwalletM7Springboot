package bmva.digiwallet.services;

import org.springframework.data.repository.query.Param;

import bmva.digiwallet.dto.UserDto;
import bmva.digiwallet.models.UserEntity;

public interface IUserService {

	public UserEntity save(UserDto userDto);
	
	public UserEntity buscaUsuarioPorEmailYPassword(String email, String password);
	
	public UserEntity findByEmail(String email);
	
	public UserEntity findByIdUsuario(@Param("id")String id);
}
