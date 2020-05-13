package bases2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bases2.models.Prueba;

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
        System.out.println("Hello World!");

        System.out.println("Creando EntityManager");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Prueba p = new Prueba(1L, "HOLA");
        Prueba p2 = new Prueba(2L, "MUNDO");
        em.persist(p);
        em.persist(p2);
        em.getTransaction().commit();
        em.close();
    }
}
