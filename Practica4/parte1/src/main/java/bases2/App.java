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
        Cliente c = new Cliente("18064600D", "Calle de primero, 3, Atico, 50018", 665145589, "josemiguel@gmail.com", "José Miguel", "Hernandez");
        Cuenta cu = new CuentaAhorro("11111", "1111122222", new GregorianCalendar(2020, 01, 25).getTime(), 100, 25.0);
        
        c.addCuenta(cu);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.persist(cu);
        em.getTransaction().commit();
    }
}
