package bmva.digiwallet.controllers;

import bmva.digiwallet.dto.AccountDto;
import bmva.digiwallet.models.*;
import bmva.digiwallet.services.IAccountService;
import bmva.digiwallet.services.IBankService;
import bmva.digiwallet.services.ICurrencyyService;
import bmva.digiwallet.services.IToAService;
import bmva.digiwallet.util.ClearVarSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wallet")
public class CuentasController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBankService bankService;

    @Autowired
    private IToAService toaService;

    @Autowired
    private ICurrencyyService currencyyService;

    @GetMapping("/cuentas")
    public String cuentasUsuario(Model model, HttpSession session) {
        ClearVarSession.clearMessages(session, model);
        if(session.getAttribute("usuario")!=null) {
            UserEntity usuario = (UserEntity)session.getAttribute("usuario");
            List<Account> cuentas = accountService.findByUser(usuario);
            model.addAttribute("cuentas", cuentas);
            model.addAttribute("nombreusuario", usuario.getFirstname().toUpperCase()+" "+usuario.getLastname().toUpperCase());
            return "/wallet/cuentas";
        }
        return "redirect:/logout";
    }

    @GetMapping("/registrarcuentas")
    public String agregarCuenta(Model model, HttpSession session) {
        UserEntity usuario = (UserEntity)session.getAttribute("usuario");
        model.addAttribute("nombreusuario", usuario.getFirstname().toUpperCase()+" "+usuario.getLastname().toUpperCase());
        List<Bank> bancos = bankService.findAll();
        model.addAttribute("bancos", bancos);
        List<TypeOfAccount> toas = toaService.findAll();
        model.addAttribute("toas", toas);
        List<Currencyy> currencies = currencyyService.findAll();
        model.addAttribute("currencies", currencies);
        if(session.getAttribute("msgcuentas")!=null) {
            String msg = (String)session.getAttribute("msgcuentas");
            if(msg.equals("exito")) model.addAttribute("msgcuentas", "Creación de nueva exitosa");
            if(msg.equals("nrocuenta")) model.addAttribute("msgcuentas", "¡Error!, El número de cuenta proporcionado esta registrado a otro usuario");
            if(msg.equals("nrocuentapropia")) model.addAttribute("msgcuentas", "¡Error!, El número de cuenta proporcionado ya esta registrado a su nombre");
        }
        return "/wallet/registrocuentas";
    }

    @ModelAttribute("cuentabancaria")
    public AccountDto retornarNuevaCuentaRegistroDTO() {
        return new AccountDto();
    }

    @PostMapping("/registrarcuenta")
    public String registrarNuevaCuenta(@ModelAttribute("cuentabancaria") AccountDto cuentaDto,
                                       Model model, HttpSession session) {
        if(session.getAttribute("usuario")!=null) {
            UserEntity usuario = (UserEntity)session.getAttribute("usuario");
            // recupera id del usuario logeado
            String idUsuario = usuario.getId();
            // validar la cuenta bancaria no este ya registrada
            String nroCuenta = cuentaDto.getNumber();
            Account cuentaNueva = accountService.buscarPorNroCuenta(nroCuenta);
            if(cuentaNueva==null) {
                // cuenta disponible
                cuentaDto.setUser(usuario);
                accountService.save(cuentaDto);
                session.setAttribute("msgcuentas", "exito");
            }else {
                if(cuentaNueva.getUser().getId().equals(idUsuario)) {
                    session.setAttribute("msgcuentas", "nrocuentapropia");
                }else {
                    session.setAttribute("msgcuentas", "nrocuenta");
                }
            }
            return "redirect:/wallet/registrarcuentas";
        }
        return "redirect:/logout";
    }
}
