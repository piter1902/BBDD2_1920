
-- Definicion del tipo cuente
create or replace type cuentaUdt;
/

-- Tipo lista de cuentas
create or replace type listaCuentas as varray(10) of ref cuentaUdt;
/

-- Tipo de cliente
create or replace type clienteUdt as object 
(
    DNI       varchar(10),
    Nombre    varchar(50),
    Apellido  varchar(50),
    Edad      int,
    Direccion varchar(100),
    Telefono  int,
    Email     varchar(50),
    Cuentas   listaCuentas,
) not final;
/

-- Tipo lista de clientes
create or replace type listaPropietarios as varray(10) of ref clienteUdt;
/

-- Redefinicion del tipo cuenta
create or replace type cuentaUdt as object
(
    Num_cuenta     int,
    IBAN           varchar(40),
    Fecha_creacion date,
    Saldo          int,
    Propietarios   listaPropietarios,
) not final;
/

-- Tipos derivados de cuenta
create or replace type cuenta_corrienteUdt under cuentaUdt final;
/

create or replace type cuenta_ahorroUdt under cuentaUdt
(
    Interes     int,
) final;
/

-- Sucursal
create or replace type sucursalUdt as object;

-- Transaccion
create or replace type transaccionUdt as object
(
    Num_transaccion       int,
    Num_cuenta_realizante ref cuentaUdt,
    Fecha                 date,
    Hora                  varchar(10),
    Importe               int,
    Descripcion           varchar(280),
    Tipo                  varchar(10),
    Codigo                varchar(50),
    Sucursal              ref 
) not final;
/

-- Operacion
create or replace type operacionUdt under transaccionUdt(
    Tipo                  varchar(10),
) final;
/

-- Transferencia
create or replace type transferenciaUdt under transaccionUdt
(
    destinatario    ref cuentaUdt,
) final;
/

-- Tipo para tabla anidada de transacciones
create or replace type realizadasUdt as table of ref transaccionUdt;
/

-- Definicion de Sucursal
create or replace type sucursalUdt as object
(
    Codigo    varchar(50),
    Direccion varchar(100),
    Telefono  int,
    Operaciones realizadasUdt,
) final;
/


-- Creamos las tablas (a partir de aqui no esta claro)

CREATE or REPLACE table Cliente of clienteUdt;

CREATE TABLE Cuenta of cuentaUdt
(
    Num_cuenta          NOT NULL,
    IBAN                varchar(40) NOT NULL,
    Fecha_creacion      date NOT NULL,
    Saldo               int NOT NULL,
    Interes             int NOT NULL,
    Propietarios        not null,
    CONSTRAINT PK_Cuenta PRIMARY KEY Num_cuenta,
    CONSTRAINT FK_Cliente FOREIGN KEY (Propietarios) REFERENCES Cliente
);

create or replace table Cliente of clienteUdt
(
    DNI       NOT NULL,
    Nombre    NOT NULL,
    Apellido  NOT NULL,
    Edad      NOT NULL,
    Direccion NOT NULL,
    Telefono  NOT NULL,
    --Email     NULL,
    --Cuentas not null, ??
    CONSTRAINT PK_Cliente PRIMARY KEY DNI,
    CONSTRAINT FK_Cuentas FOREIGN KEY(Cuentas) REFERENCES Cuentas,
);