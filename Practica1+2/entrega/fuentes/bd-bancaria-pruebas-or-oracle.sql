-- Funciones de obtener la referencia de un udt
create or replace function obtenerRefCuenta(codigoCuenta in int) return ref cuentaUdt is
	refCuenta ref cuentaUdt;
	cursor cr is select ref(c) from Cuenta c where c.Num_cuenta = codigoCuenta;
begin
	open cr;
	fetch cr into refCuenta;
	close cr;
	return refCuenta;
end obtenerRefCuenta;
/

create or replace function obtenerRefSucursal(codigoSucursal in integer) return ref sucursalUdt is
	refSucursal ref sucursalUdt;
	cursor cr is select ref(s) from Sucursal s where s.codigo = codigoSucursal;
begin
	open cr;
	fetch cr into refSucursal;
	close cr;
	return refSucursal;
end obtenerRefSucursal;
/

create or replace function obtenerRefCliente(codigoDNI in varchar) return ref clienteUdt is
	refCliente ref clienteUdt;
	cursor cr is select ref(c) from Cliente c where c.dni = codigoDNI;
begin
	open cr;
	fetch cr into refCliente;
	close cr;
	return refCliente;
end obtenerRefCliente;
/

-- insert de Cliente
insert into Cliente     values ('18064600D','Pedro','Tamargo',21,'Atiqueteimporta123456','666555444',NULL,listaCuentas());
insert into Cliente     values ('17589312Z','Juan Jose','Tambo',20,'Atiqueteimporta123456','555888222',NULL,listaCuentas());
insert into Cliente     values ('15879642W','Jesus','Villacampa',15,'Atiqueteimporta123456','456132789',NULL,listaCuentas());

-- insert de Cuenta
insert into Cuenta      values (68635266387780010337, 'ES1501283418593853658191', TO_DATE('07/02/2005', 'DD/MM/YYYY'), 100000, listapropietarios());
insert into Cuenta      values (00009418575377349236, 'ES0804871361214896945313', TO_DATE('15/10/2007', 'DD/MM/YYYY'), 1000,   listapropietarios());
insert into Cuenta      values (65638313471099711491, 'ES3520954465562344263487', TO_DATE('17/12/2015', 'DD/MM/YYYY'), 10000,  listapropietarios());
insert into Cuenta		values (cuenta_corrienteudt(65638313471099715555, 'ES0602851341284897945513', TO_DATE('28/05/2015', 'DD/MM/YYYY'), 150, listapropietarios(), obtenerRefSucursal(1)));

-- insert en tabla anidada Cliente.cuentas
insert into table (select c.cuentas from Cliente c where c.dni = '18064600D') values (obtenerRefCuenta(68635266387780010337));
insert into table (select c.cuentas from Cliente c where c.dni = '18064600D') values (obtenerRefCuenta(65638313471099711491));
insert into table (select c.cuentas from Cliente c where c.dni = '17589312Z') values (obtenerRefCuenta(65638313471099711491));
insert into table (select c.cuentas from Cliente c where c.dni = '15879642W') values (obtenerRefCuenta(68635266387780010337));
insert into table (select c.cuentas from Cliente c where c.dni = '15879642W') values (obtenerRefCuenta(65638313471099711491));

insert into table (select c.cuentas from Cliente c where c.dni = '18064600D') values (obtenerRefCuenta(65638313471099715555));
insert into table (select c.cuentas from Cliente c where c.dni = '15879642W') values (obtenerRefCuenta(65638313471099715555));

-- insert en tabla anidada Cuenta.propietarios
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 68635266387780010337) values (obtenerRefCliente('18064600D'));
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 65638313471099711491) values (obtenerRefCliente('18064600D'));
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 65638313471099711491) values (obtenerRefCliente('17589312Z'));
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 68635266387780010337) values (obtenerRefCliente('15879642W'));
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 65638313471099711491) values (obtenerRefCliente('15879642W'));

insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 65638313471099715555) values (obtenerRefCliente('18064600D'));
insert into table (select c.propietarios from Cuenta c where c.Num_cuenta = 65638313471099715555) values (obtenerRefCliente('15879642W'));



-- insert de Sucursal
insert into Sucursal    values (1,'Atiqueteimporta123456',974246678);
insert into Sucursal    values (2,'Ejea de las caballeras',976123456);
insert into Sucursal    values (3,'Molino Ecotambo',976458796);

-- insert de Transaccion
insert into Transaccion values (1, 68635266387780010337, obtenerRefCuenta(68635266387780010337), TO_DATE('28/03/2020', 'DD/MM/YYYY'), '11:11', 100, 'Descripcion necesaria', 1, obtenerRefSucursal(1));
insert into Transaccion values (2, 65638313471099711491, obtenerRefCuenta(65638313471099711491), TO_DATE('27/05/2020', 'DD/MM/YYYY'), '10:10', 50, 'Descripcion necesaria', 2, obtenerRefSucursal(2));
insert into Transaccion values (3, 00009418575377349236, obtenerRefCuenta(00009418575377349236), TO_DATE('28/01/2020', 'DD/MM/YYYY'), '12:12', 5, 'Descripcion necesaria', 3, obtenerRefSucursal(3));

-- insert Transferencia
insert into Transaccion values (transferenciaUdt(4, 00009418575377349236, obtenerRefCuenta(00009418575377349236), TO_DATE('28/01/2020', 'DD/MM/YYYY'), '12:12', 1000, 'Descripcion necesaria', 3, obtenerRefSucursal(3), obtenerRefCuenta(68635266387780010337), 68635266387780010337));
insert into Transaccion values (transferenciaUdt(5, 65638313471099711491, obtenerRefCuenta(65638313471099711491), TO_DATE('31/01/2020', 'DD/MM/YYYY'), '12:12', 10000, 'Descripcion necesaria', 3, obtenerRefSucursal(3), obtenerRefCuenta(68635266387780010337), 68635266387780010337));

-- insert Operacion
insert into Transaccion values (operacionUdt(6, 68635266387780010337, obtenerRefCuenta(68635266387780010337), TO_DATE('19/02/2020', 'DD/MM/YYYY'), '00:25', 10, 'Descripcion necesaria', 3, obtenerRefSucursal(3), 'ingreso'));
insert into Transaccion values (operacionUdt(7, 00009418575377349236, obtenerRefCuenta(00009418575377349236), TO_DATE('15/03/2020', 'DD/MM/YYYY'), '20:55', 55, 'Descripcion necesaria', 3, obtenerRefSucursal(3), 'retirada'));


-- Borramos las funciones creadas
drop function obtenerRefCuenta;
drop function obtenerRefSucursal;
drop function obtenerRefCliente;

-- Consultas
select p.num_cuenta_realizante, treat(value(p) as operacionUdt).tipo, p.importe from transaccion p where value(p) is of (operacionUdt);
select p.num_cuenta_realizante, treat(value(p) as transferenciaUdt).destinatario, p.importe from transaccion p where value(p) is of (transferenciaUdt);
