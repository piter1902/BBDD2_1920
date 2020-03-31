
/**
 * Example extracted and adapted from http://community.versant.com/documentation/reference/db4o-8.0/java/tutorial/docs/FirstGlance.html
 * Date: March 15, 2014
 */

import java.io.*;
import java.util.Date;
import java.util.List;

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
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
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
            // Cuenta cuen1 = new Cuenta_corriente(1287376372, "ES6621000418401235567891",
            // new Date(2013, 11, 26), 3050,
            // List.of(cl1, cl2));
            // Cuenta cuen2 = new Cuenta_corriente(1737882920, "ES6621765764756476574657",
            // new Date(2011, 8, 1), 10060,
            // List.of(cl3));
            // Cuenta cuen3 = new Cuenta_ahorro(1390000390, "ES5756765756756476545798", new
            // Date(2016, 7, 13), 5700,
            // List.of(cl4, cl1), 3);
            // Cuenta cuen4 = new Cuenta_ahorro(1287378733, "ES8758785758785758755858", new
            // Date(2019, 20, 14), 60790,
            // List.of(cl2, cl3), 6);
            Cuenta cuen1 = new Cuenta_corriente(1287376372, "ES6621000418401235567891", new Date(2013, 11, 26), 3050);
            Cuenta cuen2 = new Cuenta_corriente(1737882920, "ES6621765764756476574657", new Date(2011, 8, 1), 10060);
            Cuenta cuen3 = new Cuenta_ahorro(1390000390, "ES5756765756756476545798", new Date(2016, 7, 13), 5700, 3);
            Cuenta cuen4 = new Cuenta_ahorro(1287378733, "ES8758785758785758755858", new Date(2019, 20, 14), 60790, 6);
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

            //Actualizamos las cuentas para añadir sus propietarios
            updateCuentaCorriente_client(db, cuen1, cl1);
            updateCuentaCorriente_client(db, cuen1, cl2);
            updateCuentaCorriente_client(db, cuen2, cl3);
            updateCuentaAhorro_client(db,cuen3, cl1);
            updateCuentaAhorro_client(db,cuen3, cl4);
            updateCuentaAhorro_client(db,cuen4, cl2);
            updateCuentaAhorro_client(db,cuen4, cl3);
            // storeFirstPilot(db);
            // storeSecondPilot(db);
            // retrieveAllPilots(db);
            // retrievePilotByName(db);
            // retrievePilotByExactPoints(db);
            // updatePilot(db);
            // deleteFirstPilotByName(db);
            // deleteSecondPilotByName(db);
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
        System.out.println("Almacenado " + cliente);
    }

    /**
     * Inserta en la base de datos la cuenta indicada
     * 
     * @param db     Objeto que representa la Base de Datos
     * @param cuenta Cuenta a insertar en la base de datos
     */
    public static void insertCuenta(ObjectContainer db, Cuenta cuenta) {
        db.store(cuenta);
        System.out.println("Almacenado " + cuenta);
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
        System.out.println("Cliente a actualizar: " + encontrado);
        encontrado.addCuenta(cuenta);
        System.out.println("Cliente modificado: " + encontrado);
        db.store(encontrado);
    }

    public static void updateCuentaAhorro_client(ObjectContainer db, Cuenta cuenta, Cliente cliente) {
        Cuenta base = new Cuenta_ahorro(cuenta,((Cuenta_ahorro)cuenta).getInteres());
        ObjectSet result = db.queryByExample(base);
        Cuenta_ahorro encontrado = (Cuenta_ahorro) result.next();
        System.out.println("Cuenta a actualizar: " + encontrado);
        encontrado.addCliente(cliente);
        System.out.println("Cuenta modificado: " + encontrado);
        db.store(encontrado);
    }

    public static void updateCuentaCorriente_client(ObjectContainer db, Cuenta cuenta, Cliente cliente) {
        Cuenta base = new Cuenta_corriente(cuenta);
        ObjectSet result = db.queryByExample(base);
        Cuenta_corriente encontrado = (Cuenta_corriente) result.next();
        System.out.println("Cuenta a actualizar: " + encontrado);
        encontrado.addCliente(cliente);
        System.out.println("Cuenta modificado: " + encontrado);
        db.store(encontrado);
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
