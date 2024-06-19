package bmva.digiwallet.services.impl;

import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.repository.IUserRepository;
import bmva.digiwallet.services.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
	@Mock
    private IUserRepository userRepository; // Simulamos el repositorio

    @InjectMocks
    private UserServiceImpl userService; // Clase que queremos probar

    @Test
    public void testBuscarPorEmail() {
        // Datos sintéticos
        String emailUsuario = "braulio@gmail.com";
        String passwordUsuario = "123";
        UserEntity usuarioEsperado = new UserEntity();
        usuarioEsperado.setId("1d36e53c-1f03-4184-bd92-e048e123b20a");
        usuarioEsperado.setEmail("braulio@gmail.com");
        usuarioEsperado.setPassword("$2a$10$0SDf.pMGIMuHoOmA06qv6OwnQjasbAWnvlMUngok6MjLXcSQxg1pG");
       

     // Configuración del mock
        when(userRepository.findByEmail(emailUsuario)).thenReturn(usuarioEsperado);
        when(userService.buscaUsuarioPorEmailYPassword(emailUsuario, passwordUsuario)).thenReturn(usuarioEsperado);

        // Llamada al método del servicio
        UserEntity usuarioEncontrado = userService.findByEmail(emailUsuario);
        UserEntity usuarioEncontradoLogin = userService.buscaUsuarioPorEmailYPassword(emailUsuario, passwordUsuario);
        
        // Verificación
        assertNotNull(usuarioEncontrado);
        assertEquals("1d36e53c-1f03-4184-bd92-e048e123b20a", usuarioEncontrado.getId());
        assertEquals(emailUsuario, usuarioEncontrado.getEmail());

        // Verificación Usuario Login
        assertNotNull(usuarioEncontradoLogin);
        assertEquals("$2a$10$0SDf.pMGIMuHoOmA06qv6OwnQjasbAWnvlMUngok6MjLXcSQxg1pG", usuarioEncontradoLogin.getPassword());
        assertEquals(emailUsuario, usuarioEncontradoLogin.getEmail());
        
    }
}
