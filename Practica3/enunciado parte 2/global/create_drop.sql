-- Eliminamos las vistas si existian
-- Drop de las vistas
drop view Personal_view;
drop view Medico_view;
drop view Enfermero_view;
drop view Paciente_view;
drop view Asignados_view;
drop view Atendidos_view;
drop view Diagnostico_view;
drop view Ingresos_view;

-- Establecemos la conexión con el remoto
select * from dblink_connect('remota', 'host=kandula.db.elephantsql.com  dbname=roslynet  user=roslynet  password=nInsMGh_8rdRTPbnApGMqWS7W2kt-Y93');

-- Creación de vistas

-- Personal
create or replace view Personal_view as 
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección"
        from "Medico" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección"
        from "Enfermero" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección"
        from "Paciente" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select DNI, Nombre, Apellido1 || ', ' || Apellido2 as "Apellidos", Telefono, Direccion
		from dblink('remota', 'select "DNI", "Nombre", "Apellido1", "Apellido2", "Telefono", "Direccion" from "Personal"') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar, Telefono varchar, Direccion varchar)
    )
    union
    (
        select DNI, Nombre, Apellido1 || ', ' || Apellido2 as "Apellidos", Telefono, Direccion
		from dblink('remota', 'select "DNI", "Nombre", "Apellido1", "Apellido2", "Telefono", "Direccion" from "Pacientes"') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar, Telefono varchar, Direccion varchar)
    );

-- Médicos
create or replace view Medico_view as
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección", p."Especialidad", p."NumColegiado"
        from "Medico" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select DNI, Nombre, Apellido1 || ', ' || Apellido2 as "Apellidos", Telefono, Direccion, Especialidad, Num_Colegiado as "NumColegiado"
		from dblink('remota', 'select pe."DNI", pe."Nombre", pe."Apellido1", pe."Apellido2", pe."Telefono", pe."Direccion", m."Especialidad", m."Num_Colegiado" from "Personal" pe join "Medicos" m on pe."DNI"=m."DNI"') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar, Telefono varchar, Direccion varchar, Especialidad varchar, Num_Colegiado varchar)
    );

-- Enfermeros
create or replace view Enfermero_view as
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección", p."Servicio"
        from "Enfermero" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select DNI, Nombre, Apellido1 || ', ' || Apellido2 as "Apellidos", Telefono, Direccion, Servicio
		from dblink('remota', 'select pe."DNI", pe."Nombre", pe."Apellido1", pe."Apellido2", pe."Telefono", pe."Direccion", m."Servicio" from "Personal" pe join "Enfermeros" m on pe."DNI"=m."DNI"') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar, Telefono varchar, Direccion varchar, Servicio varchar)
    );

-- Pacientes
create or replace view Paciente_view as
    (
        select p."DNI", p."Nombre", p."Apellidos", p."NumContacto" as "Telefono", d."Calle" || ', ' || d."NumBloque" || ', ' || d."NumPiso" || ', ' || d."CodPostal" as "Dirección"
        from "Paciente" p join "Direccion" d on p."ID" = d."ID"
    )
    union
    (
        select DNI, Nombre, Apellido1 || ', ' || Apellido2 as "Apellidos", Telefono, Direccion
		from dblink('remota', 'select pe."DNI", pe."Nombre", pe."Apellido1", pe."Apellido2", pe."Telefono", pe."Direccion" from "Pacientes" pe') as (DNI varchar, Nombre varchar, Apellido1 varchar, Apellido2 varchar, Telefono varchar, Direccion varchar)
    );

-- Asignados
create or replace view Asignados_view as
    (
        select "NumPlanta", "DNI", "Fecha"
        from "Pertenece"
    )
    union
    (
        select Num_Planta, DNI, NULL as "Fecha"
        from dblink('remota', 'select "Num_Planta", "DNI" from "Asignados"') as (Num_Planta int, DNI varchar)
    );

-- Atendidos
create or replace view Atendidos_view as
    (
        select "DNIMedico", "DNIPaciente"
        from "MedicoPaciente"
    )
    union
    (
        select DNIMedico, DNIPaciente
        from dblink('remota', 'select a."DNI_medico", a."DNI_paciente" from "Atendidos" a') as (DNIMedico varchar, DNIPaciente varchar)
    );

-- Diagnostico
create or replace view Diagnostico_view as
    (
        select "ID", "DNI", "FechaDiagnostico", "Descripcion"
        from "Diagnostico"
    )
    union
    (
        select ID, DNI, FechaDiagnostico, Descripcion
        from dblink('remota', 'select "ID", "DNI", "Fecha", "Descripcion" from "Diagnostico"') as (ID int, DNI varchar, FechaDiagnostico date, Descripcion text)
    );

-- Ingresos
create or replace view Ingresos_view as
    (
        select "NumPlanta", "DNI", "Fecha" as "Fecha_Ingreso", NULL as "Fecha_Alta"
        from "Ingresos"
    )
    union
    (
        select NumPlanta, DNI, Fecha_Ingreso, Fecha_Alta
        from dblink('remota', 'select "DNI", "Num_Planta", "Fecha_Ingreso", "Fecha_Alta" from "Ingresos"') as (DNI varchar, NumPlanta int, Fecha_Ingreso date, Fecha_Alta date)
    );

