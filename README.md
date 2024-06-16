# digiwalletM7Springboot

Proyecto Web MVC y api rest, utilizando Springframework las siguientes dependencias:

- springboot con spring security, spring data JPA, base de datos MySQL, spring security con estrategía básica con login de usuario en base de datos empleando correo y contraseña, motor plantilla thymeleaf, pruebas unitarias básicas junit/moquito 

- Versión JRE 17
- Packaging WAR
- Nombre base de datos: digiwalletm7
- El archivo "application.properties" incluye datos de conexion a la base de datos (usuario y password) las que deberán ser sustituidas por las mysql local.

La base de datos la componen las siguientes tablas: 

- UserEntity, Account y Transaction, que utilizan un campo Id basado en UUID versión 4 de tipo String.

- Banks, Currencyy, Type_of_account, que utilizan un campo Id de tipo Long autoincrementable.

- Se debe poblar las tablas banks, currencyy y type_of_account, para esto, se adjunta archivo "import.sql".

Algunas funcionalidades del proyecto son:

* Abonos y retiros de dinero en la misma cuenta, simulando giros o depósitos en efectivo.

* Crear cuentas y contactos para realizar movimientos bancarios.

* Permite las transferencias entre las cuentas del usuario y a sus contactos. 

* Los contactos son otros usuarios de la wallet y deben tener cuentas asociadas, esto se valida durante la creación del contacto.

* En transferencias ofrece opcion de seleccionar tipos de cambio o indicadores económicos mediante el consumo de la api en los sitios https://app.exchangerate-api.com/  y  https://mindicador.cl/

* Visualizar cuentas y contactos del usuario.

* Visualizar los movimientos efectuados por el usuario, listado general o por cada cuenta, en orden descendente.

* Api para visualizar los movimientos del usuario de todas sus cuentas y por cada cuenta, se debe proporcional el id del usuario y de la cuenta a consultas según el caso, id del tipo UUID.

* Un movimiento de cuenta se considera abonos o retiros, transferencias entre sus propias cuentas así como las efectuadas a sus contactos.

* Incluye test unitario para validación de rut y login. 

