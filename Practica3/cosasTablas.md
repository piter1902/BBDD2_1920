# Tablas
- DIRECCION
### Estructura
Nombre        ?Nulo?      Tipo
ID_DIRECCION  NOT NULL    NUMBER(38)
CALLE         NOT NULL    VARCHAR2(60)
NUMERO        NOT NULL    NUMBER(38)
PISO          NOT NULL    VARCHAR2(20)
CIUDAD        NOT NULL    VARCHAR2(20)
### Constraints
"CONSTRAINT_NAME"	"COLUMN_NAME"	"CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"DIRECCIONPK"	    "ID_DIRECCION"	"P"	
"SYS_C009454"	    "CALLE"	        "C"	                """CALLE"" IS NOT NULL	
"SYS_C009455"	    "NUMERO"	    "C"	                """NUMERO"" IS NOT NULL	
"SYS_C009456"	    "PISO"	        "C"	                """PISO"" IS NOT NULL	
"SYS_C009457"	    "CIUDAD"	    "C"	                """CIUDAD"" IS NOT NULL	
---
- CODPOSTAL
### Estructura
Nombre       ?Nulo?   Tipo
CALLE        NOT NULL VARCHAR2(60)
CIUDAD       NOT NULL VARCHAR2(20)
CODPOSTAL    NOT NULL NUMBER(38)
### Constraints
"CONSTRAINT_NAME"	"COLUMN_NAME"	"CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"CODPOSTALPK"	    "CIUDAD"	    "P"	
"CODPOSTALPK"	    "CALLE"	        "P"	
"SYS_C009459"	    "CALLE"	        "C"	                """CALLE"" IS NOT NULL	
"SYS_C009460"	    "CIUDAD"	    "C"	                """CIUDAD"" IS NOT NULL	
"SYS_C009461"	    "CODPOSTAL"	    "C"	                """CODPOSTAL"" IS NOT NULL
---
- TITULAR
### Estructura
Nombre               ?Nulo?   Tipo
DNI                  NOT NULL VARCHAR2(9)
NOMBRE               NOT NULL VARCHAR2(50)
APELLIDO1            NOT NULL VARCHAR2(25)
APELLIDO2                     VARCHAR2(25)
DIRECCION            NOT NULL NUMBER(38)
TELEFONO             NOT NULL VARCHAR2(11)
FECHA_NACIMIENTO     NOT NULL DATE
### Constraints
"CONSTRAINT_NAME"       "COLUMN_NAME"      "CONSTRAINT_TYPE"    "SEARCH_CONDITION"    "REFERENCES_TABLE"
"SYS_C009463"           "DNI"              "C"                  """DNI"" IS NOT NULL    
"SYS_C009464"           "NOMBRE"           "C"                  """NOMBRE"" IS NOT NULL    
"SYS_C009465"           "APELLIDO1"        "C"                  """APELLIDO1"" IS NOT NULL    
"SYS_C009466"           "DIRECCION"        "C"                  """DIRECCION"" IS NOT NULL    
"SYS_C009467"           "TELEFONO"         "C"                  """TELEFONO"" IS NOT NULL    
"SYS_C009468"           "FECHA_NACIMIENTO" "C"                  """FECHA_NACIMIENTO"" IS NOT NULL    
"TITULARDIRECCIONFK"    "DIRECCION"        "R"                                        "DIRECCION"
"TITULARPK"             "DNI"              "P"        
---
- CODENTIDADES
### Estructura
Nombre           ?Nulo?   Tipo
BANCO            NOT NULL VARCHAR2(20)
CODIGO           NOT NULL VARCHAR2(50)
### Constraints
"CONSTRAINT_NAME"    "COLUMN_NAME"    "CONSTRAINT_TYPE"    "SEARCH_CONDITION"    "REFERENCES_TABLE"
"CODBANCOSPK"        "BANCO"          "P"                      
"SYS_C009471"        "BANCO"          "C"                  """BANCO"" IS NOT NULL    
"SYS_C009472"        "CODIGO"         "C"                  """CODIGO"" IS NOT NULL    
---
- CUENTA
### Estructura
Nombre            ?Nulo?   Tipo
CCC               NOT NULL CHAR(20)
FECHACREACION     NOT NULL DATE
SALDO             NOT NULL NUMBER(6)
TITULAR           NOT NULL VARCHAR2(9)
### Constraints
"CONSTRAINT_NAME"    "COLUMN_NAME"    "CONSTRAINT_TYPE"    "SEARCH_CONDITION"    "REFERENCES_TABLE"
"CUENTAPK"           "CCC"            "P"        
"CUENTATITULARFK"    "TITULAR"        "R"                                        "TITULAR"
"SYS_C009474"        "CCC"            "C"                  """CCC"" IS NOT NULL    
"SYS_C009475"        "FECHACREACION"  "C"                  """FECHACREACION"" IS NOT NULL    
"SYS_C009476"        "SALDO"          "C"                  """SALDO"" IS NOT NULL    
"SYS_C009477"        "TITULAR"        "C"                  """TITULAR"" IS NOT NULL    
---
- CUENTAAHORRO
### Estructura
Nombre 				 ?Nulo?   Tipo
CCC					   NOT NULL CHAR(20)
TIPOINTERES		 NOT NULL NUMBER(2)
### Constraints
"CONSTRAINT_NAME"    "COLUMN_NAME"    "CONSTRAINT_TYPE"    "SEARCH_CONDITION"    "REFERENCES_TABLE"
"CUENTAAHORROFK"    "CCC"             "R"                                        "CUENTA"
"SYS_C009480"       "TIPOINTERES"     "C"                  """TIPOINTERES"" IS NOT NULL    
"SYS_C009481"       "CCC"             "P"    
---
- SUCURSAL
### Estructura
Nombre           ?Nulo?   Tipo
CODOFICINA       NOT NULL NUMBER(4)
DIR              NOT NULL CHAR(50)
TFNO             NOT NULL NUMBER(9)
### Constraints
"CONSTRAINT_NAME"   	"COLUMN_NAME"	  "CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"SYS_C009483"         "DIR"           "C"               """DIR"" IS NOT NULL    
"SYS_C009484"         "TFNO"          "C"               """TFNO"" IS NOT NULL    
"SYS_C009485"         "CODOFICINA"    "P"
---
- CUENTACORRIENTE
### Estructura
Nombre                    ?Nulo?   Tipo
CCC                       NOT NULL CHAR(20)
SUCURSAL_CODOFICINA       NOT NULL NUMBER(4)
### Constraints
"CONSTRAINT_NAME"	            "COLUMN_NAME"	        "CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"CUENTACORRIENTEOFICINAFK"  	"SUCURSAL_CODOFICINA"	"R"	                                    "SUCURSAL"
"CUENTACORRIENTEPKFK"	        "CCC"	                "R"	                                    "CUENTA"
"SYS_C009486"	                "SUCURSAL_CODOFICINA"	"C"	                """SUCURSAL_CODOFICINA"" IS NOT NULL	
"SYS_C009487"	                "CCC"	                "P"
---
- OPERACION
### Estructura
Nombre              ?Nulo?   Tipo
NUMOP               NOT NULL NUMBER(9)
DESCRIPCIONOP                CHAR(50)
FECHAOP             NOT NULL DATE
HORAOP              NOT NULL CHAR(5)
CANTIDADOP          NOT NULL NUMBER(6)
CCC                 NOT NULL CHAR(20)
### Constraints
"CONSTRAINT_NAME"    "COLUMN_NAME"    "CONSTRAINT_TYPE"    "SEARCH_CONDITION"    "REFERENCES_TABLE"
"OPERACIONFK"        "CCC"            "R"                                        "CUENTA"
"OPERACIONPK"        "NUMOP"          "P"        
"OPERACIONPK"        "CCC"            "P"        
"SYS_C009490"        "FECHAOP"        "C"                  """FECHAOP"" IS NOT NULL    
"SYS_C009491"        "HORAOP"         "C"                  """HORAOP"" IS NOT NULL    
"SYS_C009492"        "CANTIDADOP"     "C"                  """CANTIDADOP"" IS NOT NULL   
---
- OPEFECTIVO
### Estructura
Nombre                    ?Nulo?   Tipo
NUMOP                     NOT NULL NUMBER(9)
TIPOOPEFECTIVO            NOT NULL CHAR(8)
SUCURSAL_CODOFICINA       NOT NULL NUMBER(4)
CCC                       NOT NULL CHAR(20)
### Constraints
"CONSTRAINT_NAME"	"COLUMN_NAME"	        "CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"OPEFECTIVOFK"	    "NUMOP"	                "R"	                                    "OPERACION"
"OPEFECTIVOFK"	    "CCC"	                "R"	                                    "OPERACION"
"OPEFECTIVOPK"	    "NUMOP"	                "P"	                
"OPEFECTIVOPK"	    "CCC"	                "P"	                
"SYS_C009495"	    "TIPOOPEFECTIVO"    	"C"	                """TIPOOPEFECTIVO"" IS NOT NULL	
"SYS_C009496"	    "SUCURSAL_CODOFICINA"	"C"	                """SUCURSAL_CODOFICINA"" IS NOT NULL
---
- OPTRANSFERENCIA
### Estructura
Nombre               ?Nulo?   Tipo
NUMOP                NOT NULL NUMBER(9)
CUENTADESTINO        NOT NULL CHAR(20)
CCC                  NOT NULL CHAR(20)
### Constraints
"CONSTRAINT_NAME"   	"COLUMN_NAME"	  "CONSTRAINT_TYPE"	"SEARCH_CONDITION"	"REFERENCES_TABLE"
"OPTRANSFERENCIAFK"	  "CCC"	          "R"		                                "OPERACION"
"OPTRANSFERENCIAFK"	  "NUMOP"	        "R"		                                "OPERACION"
"OPTRANSFERENCIAPK"	  "NUMOP"	        "P"		
"OPTRANSFERENCIAPK"	  "CCC"       	  "P"		
"SYS_C009499"	        "CUENTADESTINO"	"C" 	            """CUENTADESTINO"" IS NOT NULL
---

# VISTAS
