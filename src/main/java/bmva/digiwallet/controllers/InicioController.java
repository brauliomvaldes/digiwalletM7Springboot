package bmva.digiwallet.controllers;

import bmva.digiwallet.models.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
public class InicioController {
    @GetMapping("/inicio")
    public String principal(Model model, HttpSession session) {
        if(session.getAttribute("usuario")!=null) {
            UserEntity usuario = (UserEntity)session.getAttribute("usuario");

            model.addAttribute("nombreusuario", "¡ Hola ! "+usuario.getFirstname().toUpperCase()+" "+usuario.getLastname().toUpperCase());
            return "/wallet/inicio";
        }
        return "redirect:/logout";
    }

}
