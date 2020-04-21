
-- Declaración de constructor del tipo Sucursal
CREATE FUNCTION Sucursal_t
        (Codigo    varchar(50),
         Direccion varchar(100),
         Telefono  int)
    RETURNS Sucursal_t
    LANGUAGE SQL
    RETURN Sucursal_t()..Codigo(Codigo)..Direccion(Direccion)
        ..Telefono(Telefono);

-- insert de Cliente
insert into Cliente     values (Cliente_t('1'), '18064600D','Pedro','Tamargo',21,'C/ Maria de Luna, 1','666555444',NULL);
insert into Cliente     values (Cliente_t('2'), '17589312Z','Juan Jose','Tambo',20,'C/ Xinto, 1','555888222','jtambo@live.com');
insert into Cliente     values (Cliente_t('3'), '15879642W','Jesus','Villacampa',15,'C/ Mayor, 12 ','456132789','jesusvillacampasagaste@gmail.com');

-- insert de Sucursal
insert into Sucursal    values (Sucursal_t('1'), 1,'Atiqueteimporta123456',974246678);
insert into Sucursal    values (Sucursal_t('2'), 2,'Ejea de las caballeras',976123456);
insert into Sucursal    values (Sucursal_t('3'), 3,'Molino Ecotambo',976458796);
insert into Sucursal    values (Sucursal_t('4'), 4,'Ibercaja Plaza Aragon',976458766);

-- insert de Cuenta
insert into Cuenta_ahorro      values (Cuenta_ahorro_t('1'), 68635266387780010337, 'ES1501283418593853658191', TO_DATE('07/02/2005', 'DD/MM/YYYY'), 100000, 1);
insert into Cuenta_ahorro      values (Cuenta_ahorro_t('2'), 00009418575377349236, 'ES0804871361214896945313', TO_DATE('15/10/2007', 'DD/MM/YYYY'), 1000, 2);
insert into Cuenta_corriente   values (Cuenta_corriente_t('3'), 65638313471099711491, 'ES3520954465562344263487', TO_DATE('17/12/2015', 'DD/MM/YYYY'), 10000,  Sucursal_t('1'));
insert into Cuenta_corriente   values (Cuenta_corriente_t('4'), 65638313471099715555, 'ES0602851341284897945513', TO_DATE('28/05/2015', 'DD/MM/YYYY'), 150, Sucursal_t('4'));

-- insert de poseer con los clientes y cuentas anteriores

insert into Poseer      values (Poseer_t('1'),Cliente_t('1'), Cuenta_t('1'));
insert into Poseer      values (Poseer_t('2'),Cliente_t('2'), Cuenta_t('3'));
insert into Poseer      values (Poseer_t('3'),Cliente_t('2'), Cuenta_t('4'));
insert into Poseer      values (Poseer_t('4'),Cliente_t('4'), Cuenta_t('2'));

-- insert de Transferencias
insert into Transferencia values (Transferencia_t('1'), 1, Cuenta_t('1'), TO_DATE('28/03/2020', 'DD/MM/YYYY'), '11:11', 100, 'Descripcion necesaria', 1, Sucursal_t('2'), Cuenta_t('2'));
insert into Transferencia values (Transferencia_t('2'), 2, Cuenta_t('3'), TO_DATE('27/05/2020', 'DD/MM/YYYY'), '10:10', 50, 'Descripcion necesaria', 2, Sucursal_t('2'), Cuenta_t('1'));
insert into Transferencia values (Transferencia_t('3'), 3, Cuenta_t('4'), TO_DATE('28/01/2020', 'DD/MM/YYYY'), '12:12', 5, 'Descripcion necesaria', 3, Sucursal_t('1'), Cuenta_t('3'));

-- insert Operacion
insert into Operacion values (Operacion_t('4'),4, Cuenta_t('1'), TO_DATE('19/02/2020', 'DD/MM/YYYY'), '00:25', 10, 'Descripcion necesaria', 3, Sucursal_t('3'), 'ingreso');
insert into Operacion values (Operacion_t('5'), 5, Cuenta_t('2'), TO_DATE('15/03/2020', 'DD/MM/YYYY'), '20:55', 55, 'Descripcion necesaria', 3, Sucursal_t('2'), 'retirada');
-- select p.num_cuenta_realizante, treat(value(p) as operacionUdt).tipo, p.importe from transaccion p where value(p) is of (operacionUdt);

-- Diferentes consultas

-- Nombre del cliente con una cuenta determinada
select Cliente->Nombre from Poseer where Cuenta->Num_cuenta=68635266387780010337;

-- Clientes que han realizado una Transacción en la fecha indicada

select Cliente->Nombre, Cuenta->Num_cuenta from Poseer where Cuenta=(select Num_cuenta_realizante from Transaccion where Fecha=TO_DATE('27/05/2020', 'DD/MM/YYYY'));

-- Seleccionar la Sucursal a la que está asociada la cuenta corriente del cliente con el DNI indicado

select Sucursal->Codigo FROM Cuenta_corriente WHERE Num_cuenta=(select Cuenta->Num_cuenta FROM Poseer WHERE Cliente->DNI='18064600D'); 
select Sucursal->Codigo FROM Cuenta_corriente WHERE Num_cuenta IN (select Cuenta->Num_cuenta FROM Poseer WHERE Cliente->DNI='17589312Z'); 
select Sucursal->Codigo FROM Cuenta_corriente WHERE Num_cuenta=(select Cuenta->Num_cuenta FROM Poseer WHERE Cliente->DNI='15879642W'); 
