package bases2;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bases2.models.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }


    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistenciaAlumnos");

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // System.out.println("Hello World!");

        // System.out.println("Creando EntityManager");
        // EntityManager em = emf.createEntityManager();

        // em.getTransaction().begin();
        // Prueba p = new Prueba(1L, "HOLA");
        // Prueba p2 = new Prueba(2L, "MUNDO");
        // em.persist(p);
        // em.persist(p2);
        // em.getTransaction().commit();
        // em.close();
        insertData();
    }

    private static void insertData() {

        Sucursal s1 = new Sucursal(1, "Calle de los amantes de las bases de datos 3, bajo, Zaragoza, 50018", 976446628);
        Sucursal s2 = new Sucursal(2, "Calle de los ingenieros informaticos 1, atico, Huesca, 22003", 974551197);

        Cliente c1 = new Cliente("18064600D", "Calle de primero, 3, Atico, 50018", 665145589, 19, "josemiguel@gmail.com", "José Miguel", "Hernandez");
        Cliente c2 = new Cliente("15482697V", "Calle de ultimo, 5, 1ºD, 50002", 665444111, 25, "juanjose@gmail.com", "Juan José", "Tambo");
        Cuenta cu1 = new CuentaAhorro("11111", "1111122222", new GregorianCalendar(2020, 01, 25).getTime(), 100, 25.0);
        Cuenta cu2 = new CuentaAhorro("22222", "2222233333", new GregorianCalendar(2019, 06, 25).getTime(), 150, 0.0);
        Cuenta cu3 = new CuentaCorriente("33333", "3333344444", new GregorianCalendar(2020, 02, 19).getTime(), 200, s1);
        Cuenta cu4 = new CuentaCorriente("44444", "4444455555", new  GregorianCalendar(2020, 01, 24).getTime(), 20, s2);
        
        c1.addCuenta(cu1);
        c2.addCuenta(cu1);
        c2.addCuenta(cu2);
        c1.addCuenta(cu3);
        c1.addCuenta(cu4);
        c2.addCuenta(cu4);

        Transaccion t1 = new Transferencia(1, new GregorianCalendar(2020, 05, 13).getTime(), 10, "11:15", "Primera transaccion", cu1, cu2);
        t1.setSucursal(s1);

        Transaccion t2 = new Transferencia(1, new GregorianCalendar(2020, 05, 12).getTime(), 73, "11:55", "Primera transaccion de otra cuenta", cu2, cu1);
        t2.setSucursal(s2);

        Transaccion t3 = new Operacion(2, new GregorianCalendar(2020, 05, 14).getTime(), 10, "10:33", "Retirada en cajero 1", cu1, "Retirada");
        t3.setSucursal(s1);

        Transaccion t4 = new Operacion(2, new GregorianCalendar(2020, 05, 14).getTime(), 10, "8:45", "Retirada en cajero 2", cu2, "Retirada");
        t4.setSucursal(s2);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // ------------------
        em.persist(s1);
        em.persist(s2);
        // ------------------
        em.persist(c1);
        em.persist(c2);
        em.persist(cu1);
        em.persist(cu2);
        em.persist(cu3);
        em.persist(cu4);
        // ------------------
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.getTransaction().commit();
    }
}
