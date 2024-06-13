package bmva.digiwallet.controllers;

import bmva.digiwallet.dto.UserDto;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.services.IUserService;
import bmva.digiwallet.services.impl.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegistroUsuarioController {
    @Autowired
    private IUserService userService;

    @ModelAttribute("usuario")
    public UserDto retornarNuevoUsuarioRegistroDTO() {
        return new UserDto();
    }

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro() {
        return "/auth/registro";
    }

    @PostMapping("/registro")
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UserDto registroDTO) {

        if(ToolService.validarRut(registroDTO.getIdentity())) {
            UserEntity nuevousuario = userService.findByEmail(registroDTO.getEmail());
            if (nuevousuario != null) { return "redirect:/auth/registro?email"; }
            // no existe el email registrado

            UserEntity user = userService.save(registroDTO);
            if (user != null) {
                return "redirect:/auth/registro?exito";
            }
        }else {
            return "redirect:/auth/registro?rut";
        }
        return "redirect:/auth/registro?error";
    }

}
