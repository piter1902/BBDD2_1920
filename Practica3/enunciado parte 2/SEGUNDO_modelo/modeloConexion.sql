select * from dblink_connect('remota', 'host=kandula.db.elephantsql.com  dbname=roslynet  user=roslynet  password=nInsMGh_8rdRTPbnApGMqWS7W2kt-Y93');



create or replace view v as
	(
		select DNI, Nombre, Apellido1 || ' ' || Apellido2 as Apellidos
		from dblink('remota', 'select "DNI", "Nombre", "Apellido1", "Apellido2" from "Personal"') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar)
	)
	union
	(
		select "DNI", "Nombre", "Apellido1" || ' ' || "Apellido2" as Apellidos
		from "Personal"
	);

	
select * from v order by DNI;

drop view v;
