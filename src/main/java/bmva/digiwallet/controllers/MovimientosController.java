package bmva.digiwallet.controllers;

import bmva.digiwallet.dto.MovimientoDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Transaction;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.services.IAccountService;
import bmva.digiwallet.services.ITransactionService;
import bmva.digiwallet.services.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wallet")
public class MovimientosController {
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IAccountService accountService;

    // listado por cuenta
    @GetMapping("/movimientos/{id}")
    public String movimientosPorCuenta(@PathVariable String id, Model model, HttpSession session) {
    	System.out.println("id:"+id);
        if(session.getAttribute("usuario")!=null) {
            Account cuenta = accountService.buscarPorId(id);
            if(cuenta!=null) {
                // cuenta existente
                UserEntity usuario = (UserEntity)session.getAttribute("usuario");
                String idUsuario = usuario.getId();
                List<Transaction> movimientos = transactionService.findByIdUserAndIdAccount(idUsuario, id);
                List<MovimientoDto> tfrs = transactionService.mapeoMovimientos(movimientos, id);
                model.addAttribute("cuenta", cuenta.getNumber());
                model.addAttribute("movimientos", tfrs);
                model.addAttribute("nombreusuario", usuario.getFirstname().toUpperCase()+" "+usuario.getLastname().toUpperCase());
                return "/wallet/movimientos";
            }else {
                return "redirect:/wallet/cuentas";
            }
        }
        return "redirect:/logout";
    }  
	
}
