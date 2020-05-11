SELECT * FROM Personal_view;

SELECT * FROM Medico_view;

SELECT * FROM Enfermero_view;

SELECT * FROM Paciente_view;

SELECT * FROM Asignados_view;

SELECT * FROM Atendidos_view;

SELECT * FROM Diagnostico_view;

SELECT * FROM Plantas_view;

SELECT * FROM Ingresos_view;

-- Selecciona los pacientes y las plantas en las que estuvieron ordenados ingresados, ordenados por fecha de ingreso

select ing."Fecha_Ingreso", pac."Nombre", pl."Nombre"
FROM  Ingresos_view ing JOIN Plantas_view pl ON ing."NumPlanta" = pl."NumPlanta" JOIN Paciente_view pac ON ing."DNI" = pac."DNI" ORDER BY ing."Fecha_Ingreso";

-- Diagnostico de un paciente ordenado según el médico
select med."Nombre" AS Nombre_Medico, pac."Nombre" AS nombre_paciente, diag."Descripcion", diag."FechaDiagnostico" as Fecha
FROM Diagnostico_view diag JOIN Paciente_view pac ON diag."DNI" = pac."DNI" JOIN Atendidos_view aten ON aten."DNIPaciente" = pac."DNI"
JOIN Medico_view med ON med."DNI" = aten."DNIMedico" ORDER BY med."Nombre"; 