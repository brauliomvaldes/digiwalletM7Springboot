# digiwalletM7Springboot

Proyecto Web MVC, utilizando framework Springframework, incluye los proyectos springboot, data JPA, base de datos MySQL, plantilla thymeleaf, pruebas unitarias junit/moquito y login con springsecurity(*).

- Versión JRE 17
- Nombre base de datos: digiwalletm7
- El archivo "application.properties" incluye datos de conexion a la base de datos (usaurio y password) y las credenciales de acceso para login springsecurity, esto es, usuario=admin, password=super

La base de datos la componen las siguientes tablas: 

- UserEntity, Account y Transaction, que utilizan un campo Id basado en UUID versión 4
- Banks, Currencyy, Type_of_account, que utilizan un campo Id de tipo Long autoincrementable.
- Se debe poblar las tablas banks, currencyy y type_of_account, para esto, se adjunta archivo "import.sql".

Algunas funcionalidades del proyecto son:

* Abonos y retiros de dinero en la misma cuenta, simulando giros o depósitos en efectivo.

* Crear cuentas y contactos para realizar movimientos bancarios.

* Los contactos son otros usuarios de la wallet y deben tener cuentas asociadas, esto se valida durante la creación del contacto.

* Transferencias entre las cuentas del usuario y a sus contactos. 

* Informa el valor del dolar mediante el consumo de la api https://mindicador.cl/

* Visualizar cuentas y contactos del usuario.

* Visualizar los movimientos efectuados por el usuario por cada cuenta, en orden descendente.

* Un movimiento de cuenta se considera abonos o retiros, transferencias entre sus propias cuentas así como las efectuadas a sus contactos.

* Incluye test unitario para validación de rut y login. 

(*)El proyecto incluye la capa de seguridad spring security pero no esta implementada completamente ya que estimé que el proyecto no cumple con los requerimientos mínimos para su real implementación, esto es, la creación de roles y sus respectivas "autorizaciones". 

Siendo así, que respecto al ingreso (login) a la App pasando por la capa de seguridad de spring security, se requiere utilizar las credenciales en memoria que estan configuradas para estos efectos, luego de esto, es requesito crear y almacenar en base de datos, sus propias credenciales para operar con la App (doble autenticación "cuneta") y al hacer "logout" la capa de spring security "tediosamente" vuelve a presentarse.

Espero implementar spring security en la versión módulo 7.
