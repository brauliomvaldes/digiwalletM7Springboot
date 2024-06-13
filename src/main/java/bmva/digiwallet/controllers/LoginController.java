package bmva.digiwallet.controllers;

import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.services.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = {"/login", "/"})
    public String iniciarSesion() {
        return "/auth/login";
    }

    // reemplazado por validación con spring security
    // @Autowired
    // private IUserService userService;
	/*
	 * @PostMapping("/validar") public String searchUsers(@RequestParam String
	 * username, @RequestParam String password, Model model, HttpSession session) {
	 * // por requerimientos de spring security, el UserDetails debe contener
	 * username
	 * 
	 * // Aquí debes implementar la lógica para buscar usuarios por correo y
	 * contraseña // Utiliza el repositorio y la anotación @Query para realizar la
	 * búsqueda // Retorna la lista de usuarios encontrados UserEntity usuario =
	 * userService.buscaUsuarioPorEmailYPassword(username, password); if(usuario !=
	 * null) { // almacena el usuario logeado session.setAttribute("usuario",
	 * usuario); session.setAttribute("msg", null); return "/wallet/inicio"; }
	 * return "redirect:/?error"; }
	 */
}
