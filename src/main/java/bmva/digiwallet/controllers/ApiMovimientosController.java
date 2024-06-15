package bmva.digiwallet.controllers;

import bmva.digiwallet.dto.MovimientoDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Transaction;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.services.IAccountService;
import bmva.digiwallet.services.ITransactionService;
import bmva.digiwallet.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wallet")
public class ApiMovimientosController {
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IAccountService accountService;
    
    @Autowired
    private IUserService userService;
    
    // api listado por cuenta se debe suministrar número de la cuenta y el id del usuario
	@GetMapping("/api/trfs/usuario-cuenta")
	public ResponseEntity<List<MovimientoDto>> movimientosPorUsuarioCuenta(@RequestParam String idUsuario, @RequestParam String idCuenta) {
        List<Transaction> movimientos = transactionService.findByIdUserAndIdAccount(idUsuario, idCuenta);
        if(movimientos != null) {
        	List<MovimientoDto> tfrs = transactionService.mapeoMovimientos(movimientos, idCuenta, idCuenta);
        	if(tfrs != null) {
        		return ResponseEntity.ok(tfrs);   		
        	}
        }
        return ResponseEntity.notFound().build();
	}   
    
    // api listado por cuenta se debe suministrar número de la cuenta y el id del usuario
	@GetMapping("/api/trfs/usuario")
	public ResponseEntity<List<MovimientoDto>> movimientosPorUsuario(@RequestParam String idUsuario) {
		// todas las transferencias 
		List<MovimientoDto> transferencias = new ArrayList<>();
		UserEntity user = userService.findByIdUsuario(idUsuario);
		if(user != null) {
			// busca cuentas del usuario
			List<Account> cuentas = accountService.findByUser(user);
			cuentas.forEach(account->{
				// extrae transferencias de la cuenta del usuario
				List<Transaction> movimientos = transactionService.findByIdUserAndIdAccount(idUsuario, account.getId());
				// transferencias por cuentas
				List<MovimientoDto> listado = transactionService.mapeoMovimientos(movimientos, account.getId(), account.getNumber());				
				if(listado.size() > 0) {
					transferencias.addAll(listado);
				}
			});
		}
		return ResponseEntity.ok(transferencias);   		
	}  
	
	
}
