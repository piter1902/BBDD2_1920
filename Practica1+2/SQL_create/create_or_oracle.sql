
-- Definicion del tipo cuente
create or replace type cuentaUdt;
/

-- Tipo lista de cuentas
create or replace type listaCuentas as table of ref cuentaUdt;
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
    Cuentas   listaCuentas
) not final;
/

-- Tipo lista de clientes
create or replace type listaPropietarios as table of ref clienteUdt;
/

-- Redefinicion del tipo cuenta
create or replace type cuentaUdt as object
(
    Num_cuenta     int,
    IBAN           varchar(40),
    Fecha_creacion date,
    Saldo          int,
    Propietarios   listaPropietarios
) not final;
/

-- Tipos derivados de cuenta
create or replace type cuenta_ahorroUdt under cuentaUdt
(
    Interes     int
) final;
/

-- Sucursal
--create or replace type sucursalUdt as object;
create or replace type sucursalUdt;
/

--create or replace type cuenta_corrienteUdt under cuentaUdt;
create or replace type cuenta_corrienteUdt under cuentaUdt
(
	Sucursal	   ref sucursalUdt
) final;
/

-- Transaccion
create or replace type transaccionUdt as object
(
    Num_transaccion       int,
    Num_cuenta_realizante int,
    Ref_Num_cuenta_realizante ref cuentaUdt,
    Fecha                 date,
    Hora                  varchar(10),
    Importe               int,
    Descripcion           varchar(280),
    Codigo                int,
    Sucursal              ref sucursalUdt
) not final;
/

-- Operacion
create or replace type operacionUdt under transaccionUdt
(
    Tipo                  varchar(10)
) final;
/

-- Transferencia
create or replace type transferenciaUdt under transaccionUdt
(
    ref_destinatario    ref cuentaUdt,
    destinatario        int   
) final;
/

-- Definicion de Sucursal
create or replace type sucursalUdt as object
(
    Codigo    int,
    Direccion varchar(100),
    Telefono  int
) final;
/

-- Creamos las tablas (a partir de aqui no esta claro)
CREATE table Cliente of clienteUdt
(
    DNI       NOT NULL,
    Nombre    NOT NULL,
    Apellido  NOT NULL,
    Edad      NOT NULL,
    Direccion NOT NULL,
    Telefono  NOT NULL,
    CONSTRAINT PK_Cliente PRIMARY KEY (DNI)
)object id system generated
nested table Cuentas store as CuentasTabla;

-- Tabla de cuentas
CREATE TABLE Cuenta of cuentaUdt
(
    Num_cuenta          NOT NULL,
    IBAN                NOT NULL,
    Fecha_creacion      NOT NULL,
    Saldo               NOT NULL,
    -- Interes             NOT NULL, -- Es de un hijo
    CONSTRAINT PK_Cuenta PRIMARY KEY (Num_cuenta)
) object id system generated
nested table Propietarios store as PropietariosTabla;

-- Establecemos las reestricciones a las tablas anidadas
alter table CuentasTabla	  add(scope for (column_value) is Cuenta);
alter table PropietariosTabla add(scope for (column_value) is Cliente);

-- No necesitamos hacer tablas hijas de Cuenta (Cuenta es tablas sustituible)

-- Tabla de Sucursal
create table Sucursal of sucursalUdt
(
    Codigo    not null,
    Direccion not null,
    --Telefono not null,
    CONSTRAINT PK_Sucursal PRIMARY KEY (Codigo)
) object id system generated;

-- Tabla de Transacciones
create table Transaccion of transaccionUdt
(
    Num_transaccion       not null,
    Num_cuenta_realizante not null,
    Ref_Num_cuenta_realizante not null,
    Fecha                 not null,
    Hora                  not null,
    Importe               not null,
    Descripcion           not null,
    Codigo                not null,
    Sucursal              not null,
    CONSTRAINT PK_Transaccion PRIMARY KEY (Num_transaccion),
    CONSTRAINT FK_CuentaRealizante FOREIGN KEY (Num_cuenta_realizante) REFERENCES Cuenta(Num_cuenta),
    CONSTRAINT FK_CuentaRealizante_Ref FOREIGN KEY (Ref_Num_cuenta_realizante) REFERENCES Cuenta,
    CONSTRAINT FK_Sucursal FOREIGN KEY (Sucursal) REFERENCES Sucursal
) object id system generated;