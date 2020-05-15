package bases2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import main.java.queries.*;

//import javax.persistence.Criteria;

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
     * 
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

        // insertData();

        // printClients();

        do_queries();
    }

    private static void printClients() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cliente> query = em.createQuery("select c from Cliente c", Cliente.class);
        for (Cliente c : query.getResultList()) {
            System.out.println(c.toString());
        }
    }

    private static void insertData() {

        Sucursal s1 = new Sucursal(1, "Calle de los amantes de las bases de datos 3, bajo, Zaragoza, 50018", 976446628);
        Sucursal s2 = new Sucursal(2, "Calle de los ingenieros informaticos 1, atico, Huesca, 22003", 974551197);
        Sucursal s3 = new Sucursal(3, "Calle Maria de Luna1, subsuelo, zaragoza, 50670", 965345618);

        Cliente c1 = new Cliente("18064600D", "Calle de primero, 3, Atico, 50018", 665145589, 21,
                "josemiguel@gmail.com", "José Miguel", "Hernandez");
        Cliente c2 = new Cliente("15482697V", "Calle de ultimo, 5, 1ºD, 50002", 665444111, 25, "juanjose@gmail.com",
                "Juan José", "Tambo");
        Cuenta cu1 = new CuentaAhorro(11111, "1111122222", new GregorianCalendar(2020, 01, 25).getTime(), 100, "Ahorro",
                25);
        Cuenta cu2 = new CuentaAhorro(22222, "2222233333", new GregorianCalendar(2019, 06, 25).getTime(), 150, "Ahorro",
                1);
        Cuenta cu3 = new CuentaCorriente(33333, "3333344444", new GregorianCalendar(2020, 02, 19).getTime(), 200,
                "Corriente", s1);
        Cuenta cu4 = new CuentaCorriente(44444, "4444455555", new GregorianCalendar(2020, 01, 24).getTime(), 20,
                "Corriente", s2);

        c1.addCuenta(cu1);
        c2.addCuenta(cu1);
        c2.addCuenta(cu2);
        c1.addCuenta(cu3);
        c1.addCuenta(cu4);
        c2.addCuenta(cu4);

        Transaccion t1 = new Transferencia(1, new GregorianCalendar(2020, 05, 13).getTime(), 10, "Primera transaccion",
                cu1, cu2);
        t1.setSucursal(s1);

        Transaccion t2 = new Transferencia(2, new GregorianCalendar(2020, 05, 12).getTime(), 73,
                "Primera transaccion de otra cuenta", cu2, cu1);
        t2.setSucursal(s2);

        Transaccion t3 = new Operacion(3, new GregorianCalendar(2020, 05, 14).getTime(), 10, "Retirada en cajero 1",
                cu1, "Retirada");
        t3.setSucursal(s1);

        Transaccion t4 = new Operacion(4, new GregorianCalendar(2020, 05, 14).getTime(), 10, "Retirada en cajero 2",
                cu2, "Retirada");
        t4.setSucursal(s2);

        Transaccion t5 = new Operacion(5, new GregorianCalendar(2020, 07, 11).getTime(), 20, "Retirada en cajero 3",
                cu3, "Retirada");
        t5.setSucursal(s3);

        Transaccion t6 = new Operacion(6, new GregorianCalendar(2020, 01, 22).getTime(), 50, "Retirada en cajero 2",
                cu4, "Retirada");
        t6.setSucursal(s2);

        Transaccion t7 = new Operacion(7, new GregorianCalendar(2019, 03, 29).getTime(), 100, "Retirada en cajero 1",
                cu2, "Retirada");
        t7.setSucursal(s1);

        Transaccion t8 = new Operacion(8, new GregorianCalendar(2020, 10, 9).getTime(), 50, "Retirada en cajero 1", cu2,
                "Retirada");
        t8.setSucursal(s1);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // ------------------
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
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
        em.persist(t5);
        em.persist(t6);
        em.persist(t7);
        em.persist(t8);
        em.getTransaction().commit();

        em.close();
    }

    private static void do_queries() {
        // Query 1 en JPQL: "Users que más dinero han extraído de sus cuentas"

        // EN SQL: 
        // select cl.nombre, SUM(op.importe), op.num_cuenta_realizante 
        // from operacion op 
        // JOIN poseer pos ON op.num_cuenta_realizante = pos.num_cuenta
        // JOIN  cliente cl ON pos.DNI = cl.DNI
        // where op.tipo = 'Retirada' GROUP BY op.num_cuenta_realizante,cl.nombre
        // ORDER BY SUM(importe) DESC; 

        EntityManager em = emf.createEntityManager();
        String query_text1 = "SELECT cl.Nombre, op.realizante.numCuenta, SUM(op.importe) " + 
            "FROM Operacion op JOIN op.realizante realizantes " + 
            "JOIN realizantes.propietarios cl " +
            "WHERE op.tipo = 'Retirada' " + 
            "GROUP BY op.realizante.numCuenta, cl.Nombre " +
            "ORDER BY SUM(op.importe) DESC";

        javax.persistence.Query query1 = em.createQuery(query_text1);

        // Source: https://vladmihalcea.com/hibernate-resulttransformer/
        @SuppressWarnings("unchecked")
        List<Query1> results = query1
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new Query1Transformer())
                .getResultList();

        for (Query1 q : results){
                System.out.println(q);
        }

        //TODO:He probado varias cosas pero los resultados son siempre del tipo "java.lang.object"
        //printResult(results);
        
        // Query 1 en Criteria API
        // Source: https://stackoverflow.com/questions/41982998/hibernate-criteriabuilder-to-join-multiple-tables/42019970
        
        // javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();

        // javax.persistence.criteria.CriteriaQuery query = cb.createQuery(Query1.class);
        // javax.persistence.criteria.Root<Operacion> operacionTable = query.from(Operacion.class); 
        // javax.persistence.criteria.Join<Operacion,Cuenta> cuentaJoin = operacionTable.join(Operacion_.realizante);
        // javax.persistence.criteria.Join<Operacion,Cliente> clienteJoin = operacionTable.join(Cuenta_.propietarios);

        // List<Predicate> predicates = new ArrayList<>();
        // predicates.add(cb.equal(operacionTable.get(Operacion_.tipo), "Retirada"));
        
        // query.multiselect(
        //         clienteJoin.get(Cliente_.Nombre),
        //         cuentaJoin.get(Cuenta_.numCuenta),
        //         cb.sum(operacionTable.get(Operacion_.importe))
        // );
        // query.where(predicates.stream().toArray(Predicate[]::new));
        // query.groupBy(cuentaJoin.get(Cuenta_.numCuenta), clienteJoin.get(Cliente_.Nombre));
        // TypedQuery<Query1> typedQuery = em.createQuery(query);

        // List<Query1> resultList = typedQuery.getResultList();
        // for (Query1 q : resultList){
        //         System.out.println(q);
        // }

        // em.close(); //Cerramos el Manager
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
