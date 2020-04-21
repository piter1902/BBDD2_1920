
/**
 * Example extracted and adapted from http://community.versant.com/documentation/reference/db4o-8.0/java/tutorial/docs/FirstGlance.html
 * Date: March 15, 2014
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;

//import javax.management.Query;

import com.db4o.*;

public class Ejecucion extends Util {

    final static String DB_FOLDER = "./DB-FILES";
    // final static String DB_FOLDER = System.getProperty("user.home");

    final static String DB_FILE = "banco.db4o";

    final static String DB4OFILENAME = DB_FOLDER + "/" + DB_FILE;

    public static void main(String[] args) {
        new File(DB4OFILENAME).delete();
        accessDb4o();
        new File(DB4OFILENAME).delete();

        //Creamos una nueva configuración, en la que hacemos que los DELETE y UPDATE sean en cascada
        com.db4o.config.EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Cliente.class).cascadeOnUpdate(true);
        config.common().objectClass(Cliente.class).cascadeOnDelete(true);
        config.common().objectClass(Cuenta.class).cascadeOnUpdate(true);
        config.common().objectClass(Cuenta.class).cascadeOnDelete(true);
        config.common().objectClass(Transacciones.class).cascadeOnUpdate(true);
        config.common().objectClass(Transacciones.class).cascadeOnDelete(true);
        config.common().objectClass(Sucursal.class).cascadeOnUpdate(true);
        config.common().objectClass(Sucursal.class).cascadeOnDelete(true);

        ObjectContainer db = Db4oEmbedded.openFile(config, DB4OFILENAME);
        try {

            // Creación de clientes, sin asignar ninguna cuenta
            Cliente cl1 = new Cliente("17159233Z", "Pedro", "Tamargo", 20, "Servidor central ecobicizara", 656293327,
                    "pedroTamargo@gmail.com");
            Cliente cl2 = new Cliente("12345678Z", "Hayk", "Kocharyan", 20, "Piso patera", 678937498,
                    "haykkocharyan@gmail.com");
            Cliente cl3 = new Cliente("75675647Z", "Jesus", "Villacampa", 20, "Piso de su tía", 678937666,
                    "jesusvillacampa@gmail.com");
            Cliente cl4 = new Cliente("21747076R", "Juan Jose", "Tambo", 20, "Piso con humedades", 629332890,
                    "jtambo@live.com");

            // Inserción de los clientes
            insertCliente(db, cl1);
            insertCliente(db, cl2);
            insertCliente(db, cl3);
            insertCliente(db, cl4);

            // Creacón de objetos tipo Cuenta. Al haberse declarado Cuenta como abstracta,
            // no se puede instanciar un objeto de la misma.
            // En vez, se crean objetos de tipo cuenta ahorro y cuenta corriente.
            Cuenta_corriente cuen1 = new Cuenta_corriente(1287376372, "ES6621000418401235567891",
                    new Date(2013, 11, 26), 3050);
            Cuenta_corriente cuen2 = new Cuenta_corriente(1737882920, "ES6621765764756476574657", new Date(2011, 8, 1),
                    10060);
            Cuenta_ahorro cuen3 = new Cuenta_ahorro(1390000390, "ES5756765756756476545798", new Date(2016, 7, 13), 5700,
                    3);
            Cuenta_ahorro cuen4 = new Cuenta_ahorro(1287378733, "ES8758785758785758755858", new Date(2019, 10, 14),
                    60790, 6);
            // Almacenamos las cuentas en la BD
            insertCuenta(db, cuen1);
            insertCuenta(db, cuen2);
            insertCuenta(db, cuen3);
            insertCuenta(db, cuen4);

            // Actualizamos clientes almacenados, añadiendo las cuentas que poseen
            updateClient_cuenta(db, cl1, cuen1);
            updateClient_cuenta(db, cl1, cuen3);
            updateClient_cuenta(db, cl2, cuen1);
            updateClient_cuenta(db, cl2, cuen4);
            updateClient_cuenta(db, cl3, cuen2);
            updateClient_cuenta(db, cl3, cuen4);
            updateClient_cuenta(db, cl4, cuen3);

            // Actualizamos las cuentas para añadir sus propietarios
            updateCuentaCorriente_client(db, cuen1, cl1);
            updateCuentaCorriente_client(db, cuen1, cl2);
            updateCuentaCorriente_client(db, cuen2, cl3);
            updateCuentaAhorro_client(db, cuen3, cl1);
            updateCuentaAhorro_client(db, cuen3, cl4);
            updateCuentaAhorro_client(db, cuen4, cl2);
            updateCuentaAhorro_client(db, cuen4, cl3);

            // Se añaden las distintas Sucursales
            Sucursal suc1 = new Sucursal(0130, "C/Juan Pablo II, N.32, Madrid", 976667896);
            Sucursal suc2 = new Sucursal(9000, "C/Paseo Independencia, N.12, Barcelona", 976547845);
            Sucursal suc3 = new Sucursal(0220, "C/Mayor, N.1, Lugo", 977863412);
            Sucursal suc4 = new Sucursal(2085, "C/Maria de Luna, N.2, Zaragoza", 976675010);

            insertSucursal(db, suc1);
            insertSucursal(db, suc2);
            insertSucursal(db, suc3);
            insertSucursal(db, suc4);

            // Procedemos a crear las Transacciones, tanto transferencias como operaciones
            Transferencia tr1 = new Transferencia(new Date(2020, 3, 31), "17:50", 23, "Transferencia a hayk",
                    "6754837");
            Transferencia tr2 = new Transferencia(new Date(2020, 12, 2), "5:17", 50, "Pago para peña", "874764");
            Transferencia tr3 = new Transferencia(new Date(2020, 01, 4), "19:35", 120, "Deuda pendiente", "87548549");
            Operacion tr4 = new Operacion(new Date(2020, 11, 3), "17:50", 20, "", "5657373", "Extracto");
            Operacion tr5 = new Operacion(new Date(2019, 8, 16), "21:54", 100, "Ingreso para estudios", "984374",
                    "Ingreso");
            Operacion tr6 = new Operacion(new Date(2018, 6, 11), "17:32", 45, "", "3372888", "Extracto");

            // Se añaden las cuentas con las que están relacionadas esas transacciones
            tr1.setNum_cuenta_realizante(cuen3);
            tr1.setNum_cuenta_beneficiario(cuen1);
            tr2.setNum_cuenta_realizante(cuen4);
            tr2.setNum_cuenta_beneficiario(cuen2);
            tr3.setNum_cuenta_realizante(cuen3);
            tr3.setNum_cuenta_beneficiario(cuen4);
            tr4.setNum_cuenta_realizante(cuen3);
            tr5.setNum_cuenta_realizante(cuen1);
            tr6.setNum_cuenta_realizante(cuen4);

            // Se actualizan sus respectivas Sucursales y se insertan en la base de datos
            tr1.setSucursal(suc2);
            tr2.setSucursal(suc3);
            tr3.setSucursal(suc1);
            tr4.setSucursal(suc4);
            tr5.setSucursal(suc1);
            tr6.setSucursal(suc2);

            insertTransaccion(db, tr1);
            insertTransaccion(db, tr2);
            insertTransaccion(db, tr3);
            insertTransaccion(db, tr4);
            insertTransaccion(db, tr5);
            insertTransaccion(db, tr6);

            // Procedemos a realizar consultas

            // Obtenemos todos los clientes almacenados en la Base de datos
            retreiveAllClientes(db);

            // Obtenemos todas las cuentas
            retreiveAllCuentas(db);

            //Obtenemos las cuentas de determinados clientes
            retreiveCuentasFromClient(db, "Hayk");
            retreiveCuentasFromClient(db, "Pedro");

            // Obtenemos los clientes que poseen una cuenta cuyo número de cuenta es el
            // indicado
            retreiveTransaccionFromCuenta(db, 1287376372, true);
            retreiveTransaccionFromCuenta(db, 1737882920, true);
            retreiveTransaccionFromCuenta(db, 1000000000, true);

            //Obtenemos las sucursales en las que ha operado una cuenta
            retreiveSucursalFromCuenta(db, 1287378733);
            retreiveSucursalFromCuenta(db, 1390000390);

        } finally {
            db.close();
        }
    }

    public static void accessDb4o() {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
        try {
            // do something with db4o
        } finally {
            db.close();
        }
    }

    /**
     * Inserta en la base de datos el cliente indicado
     *
     * @param db     Objeto que representa la Base de Datos
     * @param client Cliente a insertar en la base
     */
    public static void insertCliente(ObjectContainer db, Cliente cliente) {
        db.store(cliente);
        System.out.println("Almacenado cliente " + cliente.getNombre());
    }

    /**
     * Inserta en la base de datos la cuenta indicada
     *
     * @param db     Objeto que representa la Base de Datos
     * @param cuenta Cuenta a insertar en la base de datos
     */
    public static void insertCuenta(ObjectContainer db, Cuenta cuenta) {
        db.store(cuenta);
        System.out.println("Almacenada cuenta " + cuenta.getNum_cuenta());
    }

    /**
     * Inserta en la base de datos la sucursal indicada
     *
     * @param db  Objeto que representa la Base de Datos
     * @param suc Sucursal a insertar en la base de datos
     */
    public static void insertSucursal(ObjectContainer db, Sucursal suc) {
        db.store(suc);
        System.out.println("Almacenado sucursal: " + suc.getCodigo());
    }

    /**
     * Inserta en la base de datos la Transacción indicada
     *
     * @param db    Objeto que representa la Base de Datos
     * @param trans Transacción a insertar en la base de datos
     */
    public static void insertTransaccion(ObjectContainer db, Transacciones trans) {
        db.store(trans);
        System.out.println("Almacenada transaccion: " + trans.getNum_transaccion());
    }

    /**
     * Modifica el cliente almacenado en la base de datos equivalente a "cliente"
     * añadiendo "cuenta" a la lista de cuentas del mismo. Inserta el objeto
     * modificado en la base
     *
     * @param db      Objeto que representa la Base de Datos
     * @param cliente Cliente a modificar
     * @param cuenta  Cuenta a añadir en la lista de cuentas del cliente
     *
     */
    public static void updateClient_cuenta(ObjectContainer db, Cliente cliente, Cuenta cuenta) {
        Cliente base = new Cliente(cliente);
        ObjectSet result = db.queryByExample(base);
        Cliente encontrado = (Cliente) result.next();
        System.out.println("Cliente a actualizar: " + encontrado.getNombre());
        encontrado.addCuenta(cuenta);
        db.store(encontrado);
    }

    public static void updateCuentaAhorro_client(ObjectContainer db, Cuenta_ahorro cuenta, Cliente cliente) {
        Cuenta base = new Cuenta_ahorro(cuenta, cuenta.getInteres());
        ObjectSet result = db.queryByExample(base);
        Cuenta_ahorro encontrado = (Cuenta_ahorro) result.next();
        System.out.println("Cuenta a actualizar: " + encontrado.getNum_cuenta());
        encontrado.addCliente(cliente);
        db.store(encontrado);
    }

    public static void updateCuentaCorriente_client(ObjectContainer db, Cuenta_corriente cuenta, Cliente cliente) {
        Cuenta base = new Cuenta_corriente(cuenta);
        ObjectSet result = db.queryByExample(base);
        Cuenta_corriente encontrado = (Cuenta_corriente) result.next();
        System.out.println("Cuenta a actualizar: " + encontrado.getNum_cuenta());
        encontrado.addCliente(cliente);
        db.store(encontrado);
    }

    public static void retreiveAllClientes(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Cliente.class);
        System.out.println("Mostrando todos los clientes...");
        listResult(result);
    }

    public static void retreiveAllCuentas(ObjectContainer db) {
        com.db4o.query.Query query = db.query();
        query.constrain(Cuenta.class);
        ObjectSet result = query.execute();
        System.out.println("Mostrando todas las cuentas...");
        listResult(result);
    }

    public static void retreiveAllTransactions(ObjectContainer db) {
        com.db4o.query.Query query = db.query();
        query.constrain(Transacciones.class);
        ObjectSet result = query.execute();
        System.out.println("Mostrando todas las transacciones...");
        listResult(result);
    }
    /**
     *
     * @param db Objeto que representa la base de datos
     * @param num_cuenta Número de cuenta
     * @param verbose Si True, muestra por pantalla el resultado de la consulta
     * @return lista de transacciones de la cuenta num_cuenta
     */
    public static List retreiveTransaccionFromCuenta(ObjectContainer db, int num_cuenta, Boolean verbose){
       List<Transacciones> resultados = db.query(new com.db4o.query.Predicate<Transacciones>(){
           public boolean match(Transacciones tran){
               return (tran.getNum_cuenta_realizante().getNum_cuenta() == num_cuenta ||
                       ((Transferencia)tran).getNum_cuenta_beneficiario().getNum_cuenta() == num_cuenta);

           }
       });
       if (verbose){
           System.out.println("Mostrando las transacciones de la cuenta " + num_cuenta + "...");
           listResult(resultados);
       }
       return resultados;
    }

    public static void retreiveCuentasFromClient(ObjectContainer db, String name){
        List<Cuenta> resultados = db.query(new com.db4o.query.Predicate<Cuenta>(){
            public boolean match(Cuenta cuen){
                List clientes = cuen.getClientes();
                for(Object c : clientes){
                    return ((Cliente)c).getNombre() == name;
                }
                return false;
            }
        });
        System.out.println("Mostrando las cuentas del cliente " + name + "...");
        listResult(resultados);
    }
    /**
     * Muestra todas las sucursales en las que ha operado la cuenta indicada
     *
     * @param db Objeto que representa la Base de Datos
     * @param num_cuenta Número de la cuenta
     */
    public static void retreiveSucursalFromCuenta(ObjectContainer db, int num_cuenta){
        List<Transacciones> transacciones_cuenta = retreiveTransaccionFromCuenta(db, num_cuenta, false);
        List<Sucursal> sucursal_cuenta = new ArrayList<Sucursal>(); //Creacion de lista vacía
        for(Transacciones tran : transacciones_cuenta){
            sucursal_cuenta.add(tran.getSucursal());
        }
        System.out.println("Mostrando las sucursales en las que ha operado la cuenta: " + num_cuenta);
        listResult(sucursal_cuenta);
    }

    /*
     * public static void storeSecondPilot(ObjectContainer db) { Pilot pilot2 = new
     * Pilot("Rubens Barrichello", 99); db.store(pilot2);
     * System.out.println("Stored " + pilot2); }
     *
     * public static void retrieveAllPilotQBE(ObjectContainer db) { Pilot proto =
     * new Pilot(null, 0); ObjectSet result = db.queryByExample(proto);
     * listResult(result); }
     *
     * public static void retrieveAllPilots(ObjectContainer db) { ObjectSet result =
     * db.queryByExample(Pilot.class); listResult(result); }
     *
     * public static void retrievePilotByName(ObjectContainer db) { Pilot proto =
     * new Pilot("Michael Schumacher", 0); ObjectSet result =
     * db.queryByExample(proto); listResult(result); }
     *
     * public static void retrievePilotByExactPoints(ObjectContainer db) { Pilot
     * proto = new Pilot(null, 100); ObjectSet result = db.queryByExample(proto);
     * listResult(result); }
     *
     * public static void updatePilot(ObjectContainer db) { ObjectSet result =
     * db.queryByExample(new Pilot("Michael Schumacher", 0)); Pilot found = (Pilot)
     * result.next(); found.addPoints(11); db.store(found);
     * System.out.println("Added 11 points for " + found); retrieveAllPilots(db); }
     *
     * public static void deleteFirstPilotByName(ObjectContainer db) { ObjectSet
     * result = db.queryByExample(new Pilot("Michael Schumacher", 0)); Pilot found =
     * (Pilot) result.next(); db.delete(found); System.out.println("Deleted " +
     * found); retrieveAllPilots(db); }
     *
     * public static void deleteSecondPilotByName(ObjectContainer db) { ObjectSet
     * result = db.queryByExample(new Pilot("Rubens Barrichello", 0)); Pilot found =
     * (Pilot) result.next(); db.delete(found); System.out.println("Deleted " +
     * found); retrieveAllPilots(db); }
     */
}
