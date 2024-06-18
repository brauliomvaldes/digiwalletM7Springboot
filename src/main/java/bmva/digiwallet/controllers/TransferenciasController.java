package bmva.digiwallet.controllers;

import bmva.digiwallet.dto.CuentasDeLaTransferenciaDto;
import bmva.digiwallet.dto.TransactionDto;
import bmva.digiwallet.models.Account;
import bmva.digiwallet.models.Contact;
import bmva.digiwallet.models.Transaction;
import bmva.digiwallet.models.UserEntity;
import bmva.digiwallet.services.IAccountService;
import bmva.digiwallet.services.IContactService;
import bmva.digiwallet.services.ITransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/wallet")
public class TransferenciasController {
    @Autowired
    IAccountService accountService;

    @Autowired
    IContactService contactService;

    @Autowired
    ITransactionService transactionService;

    @ModelAttribute("procesotransferencia")
    public TransactionDto retornarTransferenciaDto() {
        return new TransactionDto();
    }

    @PostMapping("/transferencias")
    public String transferenciasEntreCuentas(@ModelAttribute("procesotransferencia") TransactionDto transactionDto,
                                             @RequestParam("selectedDestino") String selectedDestino, Model model, HttpSession session) {
        session.setAttribute("msgtransferencia", null);
        if (session.getAttribute("usuario") != null && session.getAttribute("sender") != null) {
            BigDecimal balance = (BigDecimal) session.getAttribute("balance");
            String idCuenta = (String) session.getAttribute("idcuenta");
            BigDecimal montoTransferir = transactionDto.getAmount();
            // el monto ingresado es positivo
            if (montoTransferir.compareTo(BigDecimal.ZERO) > 0) {
                // si el saldo es mayor al monto a transaferir
                if (balance.compareTo(montoTransferir) >= 0) {
                    // tiene saldo disponible para la transferencia
                    // almacena cuenta destino
                    Account receiver;
                    if (selectedDestino.equals("PROPIAS")) {
                        // cuenta destino viene desde cuentas del usuario
                        receiver = transactionDto.getAccount(); // si es cuentas propias se almaceno receiver
                    } else {
                        // cuenta destino viene desde sus contactos
                        // el nro de la cuenta de contacto se almacenó en Number
                        String nroCuentaReceiverMisContactos = transactionDto.getNumber();
                        receiver = accountService.buscarPorNroCuenta(nroCuentaReceiverMisContactos);
                    }
                    // recupera id de las monedas de las cuentas origen y  destino
                    String idCurrencySender = accountService.buscarPorId(idCuenta).getCurrencyy().getId().toString();  // trae el id de la moneda de la cuenta sender
                    String idCurrencyReceiver = receiver.getCurrencyy().getId().toString(); // trae el id de la moneda de la cuenta receiver
                    // si ambas monedas son iguales
                    if(idCurrencySender.equals(idCurrencyReceiver)){
                        // factor de conversión debe ser igual a 1
                        transactionDto.setFactor(BigDecimal.ONE);  // aplica regla de negocio
                    }
                    // almacena cuenta del destinatario
                    transactionDto.setAccount(receiver);
                    // revisar factor de conversión
                    BigDecimal factor = transactionDto.getFactor();
                    // si es mayor a cero
                    if (factor.compareTo(BigDecimal.ZERO) > 0) {
                        // aplicar factor al monto de la transacción
                    	BigDecimal montoTransferirADestinatario = transactionDto.getAmount();
                    	montoTransferirADestinatario = montoTransferirADestinatario.multiply(factor);
                        transactionDto.setFactorAmount(montoTransferirADestinatario);
                    } else {
                        // copia el mismo monto original
                        transactionDto.setFactorAmount(transactionDto.getAmount());
                    }
                    // recupera cuenta origen de la transferencia
                    Account sender = (Account) session.getAttribute("sender");
                    transactionDto.setSender(sender);
                    Transaction trf = transactionService.saveTransaction(transactionDto);
                    if (trf != null) {

                        // aumenta el saldo del destinatario
                        accountService.depositar(trf.getAmount_receiver(), trf.getReceiver().getId());

                        // rebajar el saldo del origen
                        accountService.retirar(trf.getAmount_sender(), trf.getSender().getId());
                        session.setAttribute("msgtransferencia", "exito");
                    } else {
                        // error an crear tranferencia
                        session.setAttribute("msgtransferencia", "error");
                    }
                } else {
                    // saldo insuficiente
                    session.setAttribute("msgtransferencia", "saldo");
                }
            } else {
                // monto es negativo
                session.setAttribute("msgtransferencia", "negativo");
            }

            session.setAttribute("sender", null);
            return "redirect:/wallet/transferir/" + idCuenta;
        }
        return "redirect:/logout";
    }

    @GetMapping("/transferir/{id}")
    public String transferirDinero(@PathVariable String id, Model model, HttpSession session) {
        // si no viene resultado de operación anterior
        if(session.getAttribute("msgtransferencia") == null){
            // borrar mensaje
            model.addAttribute("msgtransferencia", null);
        }
        if (session.getAttribute("usuario") != null) {
            UserEntity usuario = (UserEntity) session.getAttribute("usuario");
            String idUsuario = usuario.getId();
            // valida cuenta referida exista
            Account cuenta = accountService.buscarPorId(id);
            if (cuenta != null) {
                // saldo de la cuenta seleccioanda
                BigDecimal balance = cuenta.getBalance();

                // consulta si la cuenta tiene saldo mayou a cero
                if (balance.compareTo(BigDecimal.ZERO) <= 0) {
                    // mensaje de advertencia
                    model.addAttribute("msgtransferencia", "¡Atención! Saldo insuficiente para realizar transferencias de dinero");
                    System.out.println("balance cero");
                }else {
                    // borrar mensajes anteriores
                    model.addAttribute("msgtransferencia", null);
                }
                // reunir todas las cuentas del usuario
                List<Account> listaCuentasDelUsuario = accountService.findByUserWithoutOneIdAccount(idUsuario, id);

                // reunir las cuentas de sus contactos
                List<Contact> listaCuentasContactosDelUsuario = contactService.findByUser(usuario);

                // reunir las cuentas y simbolos de sus monedas que participan en la transferencia
                List<CuentasDeLaTransferenciaDto> cuentasdelusuario = accountService.recolectarCuentasParaLaTransferencia(listaCuentasDelUsuario, listaCuentasContactosDelUsuario);

                // envia a la vista cuentas y simboles de su moneda
                model.addAttribute("cuentasdelusuario", cuentasdelusuario);

                // envia datos de la cuenta origen a la vista
                // el número de la cuenta
                model.addAttribute("nrocuenta", cuenta.getNumber());
                // el saldo en la cuenta
                model.addAttribute("balance", cuenta.getBalance());
                // el tipo de moneda
                model.addAttribute("moneda", cuenta.getCurrencyy().getSymbol());
                // nombre del usuario
                model.addAttribute("nombreusuario", usuario.getFirstname().toUpperCase() + " " + usuario.getLastname().toUpperCase());
                // sus cuentas
                model.addAttribute("suscuentas", listaCuentasDelUsuario);
                // sus contactos
                model.addAttribute("suscontactos", listaCuentasContactosDelUsuario);
                // almacena datos para procesar operacion transferencia
                session.setAttribute("nrocuenta", cuenta.getNumber());
                session.setAttribute("balance", balance);
                session.setAttribute("idcuenta", id);
                session.setAttribute("sender", cuenta);

                if (session.getAttribute("msgtransferencia") != null) {
                    String msg = (String) session.getAttribute("msgtransferencia");

                    if (msg.equals("exito")) model.addAttribute("msgtransferencia", "Operación realizada existosamente");
                    if (msg.equals("saldo")) model.addAttribute("msgtransferencia", "¡Error!, El monto ingresado supera el saldo disponible");
                    if (msg.equals("negativo")) model.addAttribute("msgtransferencia", "¡Error!, Debe ingresar un monto positivo");
                    if (msg.equals("error")) model.addAttribute("msgtransferencia", "¡Error!, No fue posible realizar la operación, intente más tarde");
                }
                return "/wallet/transferencias";
            } else {
                // intento de transferencia por cuenta nula no válido
                session.setAttribute("sender", null);
                return "/wallet/cuentas";
            }

        }
        return "redirect:/logout";
    }

}
