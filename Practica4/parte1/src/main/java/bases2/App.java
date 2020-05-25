package bases2;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.Query;
import javax.persistence.TypedQuery;

import org.graalvm.compiler.nodes.virtual.EscapeObjectState;

import bases2.models.*;
import bases2.queries.*;

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
        //LastDataTransaction();
        IngresosMasJoven();
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
    private static void LastDataTransaction() {
        // Query3: Ultima transaccion de todas cuentas de todos los clientes

        // EN JPQL:

        EntityManager em = emf.createEntityManager();
        // He cambiado la orientación de los JOIN porque queda más simple
        String query_text = 
        "SELECT cl.Nombre, real.numCuenta, tr.fecha " +
        "FROM Transaccion tr JOIN tr.realizante real JOIN real.propietarios cl " + 
        "WHERE tr.fecha IN ( SELECT MAX(tr2.fecha) FROM Transaccion tr2 WHERE tr2.realizante.numCuenta = tr.realizante.numCuenta) "+
        "GROUP BY cl.Nombre, real.numCuenta, tr.fecha ORDER BY cl.Nombre";

        javax.persistence.Query query3 = em.createQuery(query_text);

        // Source: https://vladmihalcea.com/hibernate-resulttransformer/
        @SuppressWarnings("unchecked")
        List<Query3> results = query3.unwrap(org.hibernate.query.Query.class)
                        .setResultTransformer(new Query3Transformer()).getResultList();

        System.out.println("------ Mostrando Query3 en JPQL ------");

        for (Query3 q : results) {
                System.out.println(q);
        }
    }

    private static void IngresosMasJoven() {
        // Query5: Retirada total de dinero de las cuentas del usuario mas viejo

        // EN JPQL:

        EntityManager em = emf.createEntityManager();
        // He cambiado la orientación de los JOIN porque queda más simple
        String query_text = 
        "SELECT cl.Nombre, realizantes.numCuenta, SUM(op.importe), cl.Edad "
                        + "FROM Operacion op JOIN op.realizante realizantes "
                        + "JOIN realizantes.propietarios cl " + "WHERE op.tipo = 'Retirada' AND "
                        + "cl.Edad = (SELECT MAX(cl2.Edad) FROM Cliente cl2) "
                        + "GROUP BY realizantes.numCuenta, cl.Nombre, cl.Edad ORDER BY SUM(op.importe) DESC";

        javax.persistence.Query query5 = em.createQuery(query_text);

        // Source: https://vladmihalcea.com/hibernate-resulttransformer/
        @SuppressWarnings("unchecked")
        List<Query5> results = query5.unwrap(org.hibernate.query.Query.class)
                        .setResultTransformer(new Query5Transformer()).getResultList();

        System.out.println("------ Mostrando Query5 en JPQL ------");

        for (Query5 q : results) {
                System.out.println(q);
        }
}

        /**
         * Función que he encontrado en:
         * http://www.java2s.com/Tutorials/Java/JPA/4100__JPA_Query_GroupBy_Having.htm
         * 
         * @param result
         */
        private static void printResult(Object result) {
                if (result == null) {
                        System.out.print("NULL");
                } else if (result instanceof Object[]) {
                        Object[] row = (Object[]) result;
                        System.out.print("[");
                        for (int i = 0; i < row.length; i++) {
                                printResult(row[i]);
                        }
                        System.out.print("]");
                } else if (result instanceof Long || result instanceof Double || result instanceof String) {
                        System.out.print(result.getClass().getName() + ": " + result);
                } else {
                        System.out.print(result);
                }
                System.out.println();
        }
}
