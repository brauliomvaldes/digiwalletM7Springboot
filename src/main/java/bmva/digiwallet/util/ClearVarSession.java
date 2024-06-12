package bmva.digiwallet.util;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

public class ClearVarSession {
	public static void clearMessages(HttpSession session, Model model) {
		
		session.removeAttribute("msg");
		model.addAttribute("msg", null);
		model.asMap().remove("msg");
		session.removeAttribute("msgcuentas");
		model.addAttribute("msgcuentas", null);
		model.asMap().remove("msgcuentas");
		session.removeAttribute("msgcontactos");
		model.addAttribute("msgcontactos", null);
		model.asMap().remove("msgcontactos");
		session.removeAttribute("msgtransferencia");
		model.addAttribute("msgtransferencia", null);
		model.asMap().remove("msgtransferencia");
	}
}
