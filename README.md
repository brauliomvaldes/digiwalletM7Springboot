# digiwalletSpringboot

Proyecto Web MVC y api rest, utilizando Springframework las siguientes dependencias:

- springboot con spring security, spring data JPA, base de datos MySQL, spring security con estrategía básica con login de usuario en base de datos empleando correo y contraseña, motor plantilla thymeleaf, pruebas unitarias básicas junit/moquito 

- Versión JRE 17
- Packaging WAR
- Nombre base de datos: digiwalletm7
- Test unitario para validación de rut y login. 

La base de datos la componen las siguientes tablas: 
- UserEntity, Account y Transaction, que utilizan un campo Id basado en UUID versión 4 de tipo String.
- Banks, Currencyy, Type_of_account, que utilizan un campo Id de tipo Long autoincrementable.
- Se adjunta archivo "import.sql" y "datos wallet" para poblar base de datos.

La wallet funciona de la siguiente forma:
- Para acceder se debe crear un usuario, luego se ingresa por medio de su correo y password.
- Los usuarios se crean sin cuentas asociadas.
- Se debe crear al menos dos usuarios para poder realizar transferencias a otros usuarios.
- Las cuentas se crean con saldo inicial cero y se define el tipo de cuenta, banco y moneda.
- El usuario puede cargar dinero a su cuenta mediante la operación de abono o retirar dinero, simulando efectivo.
- Cada usuario puede tener más de una cuenta para realizar traspasos de dinero entre sus cuentas. 
- Si el usuario desea transferir a otro usuario primero debe agregarlo como contacto y dicho usuario debe tener asignada una cuenta para poder realizarle transferencias. 
- Cuando se opera con distintas monedas, se habilita la opción de aplicar un factor para aplicar tipo de cambio.

Algunas funcionalidades del proyecto son:
* Informa tipos de cambio o indicadores económicos mediante el consumo de la api en los sitios https://app.exchangerate-api.com/  y  https://mindicador.cl/
* Visualizar cuentas y contactos del usuario.
* Visualizar los movimientos efectuados por el usuario, listado general o por cada cuenta, en orden descendente.
* Api para visualizar los movimientos del usuario de todas sus cuentas y por cada cuenta, se debe proporcional el id del usuario y de la cuenta a consultas según el caso, id del tipo UUID.
* Un movimiento de cuenta se considera abonos o retiros, transferencias entre sus propias cuentas así como las efectuadas a sus contactos.

