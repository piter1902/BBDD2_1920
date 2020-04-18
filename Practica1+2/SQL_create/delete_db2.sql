DROP TABLE Operacion;

DROP TABLE Transferencia;

DROP TABLE Transaccion;

DROP TABLE Poseer;

DROP TABLE Cuenta_ahorro;

DROP TABLE Cuenta_corriente;

DROP TABLE Sucursal;

DROP TABLE Cuenta;

DROP TABLE Cliente;

ALTER TYPE Transferencia_t drop ATTRIBUTE Num_cuenta_beneficiario;

DROP TYPE Transferencia_t;

DROP TYPE Operacion_t;

ALTER TYPE Transaccion_t drop ATTRIBUTE Num_cuenta_realizante;

ALTER TYPE Transaccion_t drop ATTRIBUTE Sucursal;

DROP TYPE Transaccion_t;

ALTER TYPE Poseer_t drop ATTRIBUTE DNI;

ALTER TYPE Poseer_t drop ATTRIBUTE Num_cuenta;

DROP TYPE Poseer_t;

DROP TYPE Cuenta_ahorro_t;

ALTER TYPE Cuenta_corriente_t drop ATTRIBUTE Sucursal;

DROP TYPE Cuenta_corriente_t;

DROP TYPE Sucursal_t;

DROP TYPE Cuenta_t;

DROP TYPE Cliente_t;