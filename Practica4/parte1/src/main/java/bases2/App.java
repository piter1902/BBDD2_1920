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
        Cliente c1 = new Cliente("18064600D", "Calle de primero, 3, Atico, 50018", 665145589, "josemiguel@gmail.com", "José Miguel", "Hernandez");
        Cliente c2 = new Cliente("15482697V", "Calle de ultimo, 5, 1ºD, 50002", 665444111, "juanjose@gmail.com", "Juan José", "Tambo");
        Cuenta cu1 = new CuentaAhorro("11111", "1111122222", new GregorianCalendar(2020, 01, 25).getTime(), 100, 25.0);
        Cuenta cu2 = new CuentaAhorro("22222", "2222233333", new GregorianCalendar(2019, 06, 25).getTime(), 150, 0.0);
        
        c1.addCuenta(cu1);
        c2.addCuenta(cu1);
        c2.addCuenta(cu2);

        Transaccion t1 = new Transferencia(1, new GregorianCalendar(2020, 05, 13).getTime(), 10, "11:15", "Primera transaccion", cu1, cu2);
        Transaccion t2 = new Transferencia(1, new GregorianCalendar(2020, 05, 12).getTime(), 73, "11:55", "Primera transaccion de otra cuenta", cu2, cu1);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.persist(cu1);
        em.persist(cu2);
        // ------------------
        em.persist(t1);
        em.persist(t2);
        em.getTransaction().commit();
    }
}
