package bmva.digiwallet.controllers;
import bmva.digiwallet.util.ClearVarSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public String cerrarSesion(Model model, HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("nrocuenta");
        session.removeAttribute("balance");
        ClearVarSession.clearMessages(session, model);
        return "/auth/login";
    }
}
