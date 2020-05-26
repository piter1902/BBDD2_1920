package bases2;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import bases2.models.Cliente;
import bases2.models.Cuenta;
import bases2.models.CuentaAhorro;
import bases2.models.CuentaCorriente;
import bases2.models.Operacion;
import bases2.models.Sucursal;
import bases2.models.Transaccion;
import bases2.models.Transferencia;
import bases2.queries.Query1;
import bases2.queries.Query1Transformer;
import bases2.queries.Query2;
import bases2.queries.Query2Transformer;
import bases2.queries.Query3;
import bases2.queries.Query3Transformer;
import bases2.queries.Query4;
import bases2.queries.Query4Transformer;
import bases2.queries.Query5;
import bases2.queries.Query5Transformer;

/**
 * Hello world!
 */
public final class Test {
        private Test() {
        }

        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistenciaAlumnos");

        public static void main(String[] args) {

                insertData();
                queryMasDinero();

                querySucursalesMenores30();

                queryMaxIngresoTransaccion();
                LastDataTransaction();
                IngresosMasJoven();
        }

        private static void insertData() {

                Sucursal s1 = new Sucursal(1, "Calle de los amantes de las bases de datos 3, bajo, Zaragoza, 50018",
                                976446628);

                Sucursal s2 = new Sucursal(2, "Calle de los ingenieros informaticos 1, atico, Huesca, 22003",
                                974551197);

                Sucursal s3 = new Sucursal(3, "Calle Maria de Luna1, subsuelo, zaragoza, 50670", 965345618);

                // ------------------------------------------------------------------

                Cliente c1 = new Cliente("18064600D", "Calle de primero, 3, Atico, 50018", 665145589, 21,
                                "josemiguel@gmail.com", "José Miguel", "Hernandez");

                Cliente c2 = new Cliente("15482697V", "Calle de ultimo, 5, 1ºD, 50002", 665444111, 25,
                                "juanjose@gmail.com", "Juan José", "Tambo");

                Cliente c3 = new Cliente("64364354H", "Calle Xinto, 10, 1J, 50018", 98833848, 32,
                                "milibro@lunadepluton.si", "DROSSSS", "Nabo");

                Cliente c4 = new Cliente("76476476J", "Calle Mayor, 9, Bloque 2, 50009", 83736478, 18, "jv7@live.com",
                                "Jesus", "Amargo");

                Cliente c5 = new Cliente("83746478P", "Calle Bonita, 18, 3ºD, 50000", 747475752, 26, "julio@gmail.es",
                                "Julio", "Martinez");

                Cliente c6 = new Cliente("74674672D", "Calle Primera, 2, 1ºD, 50089", 848475729, 45, "elSefe@live.com",
                                "Sefedin", "Perez");

                // ------------------------------------------------------------------

                Cuenta cu1 = new CuentaAhorro("11111", "1111122222", new GregorianCalendar(2020, 01, 25).getTime(), 100,
                                25);

                Cuenta cu2 = new CuentaAhorro("22222", "2222233333", new GregorianCalendar(2019, 06, 25).getTime(), 150,
                                1);

                Cuenta cu3 = new CuentaCorriente("33333", "3333344444", new GregorianCalendar(2020, 02, 19).getTime(),
                                200, s1);

                Cuenta cu4 = new CuentaCorriente("44444", "4444455555", new GregorianCalendar(2020, 01, 24).getTime(),
                                20, s2);

                Cuenta cu5 = new CuentaAhorro("55555", "5555566666", new GregorianCalendar(2020, 03, 20).getTime(), 56,
                                10);

                Cuenta cu6 = new CuentaAhorro("6666", "66667777", new GregorianCalendar(2010, 04, 10).getTime(), 500,
                                1);

                Cuenta cu7 = new CuentaCorriente("77777", "7777788888", new GregorianCalendar(2020, 05, 11).getTime(),
                                30, s1);

                Cuenta cu8 = new CuentaCorriente("88888", "8888899999", new GregorianCalendar(2015, 01, 22).getTime(),
                                150, s3);

                Cuenta cu9 = new CuentaCorriente("99999", "999900000", new GregorianCalendar(2013, 04, 9).getTime(),
                                500, s2);

                c1.addCuenta(cu1);
                c2.addCuenta(cu1);
                c2.addCuenta(cu9);
                c2.addCuenta(cu3);
                c3.addCuenta(cu2);
                c3.addCuenta(cu5);
                c3.addCuenta(cu6);
                c4.addCuenta(cu3);
                c4.addCuenta(cu7);
                c5.addCuenta(cu4);
                c5.addCuenta(cu8);
                c6.addCuenta(cu4);
                c6.addCuenta(cu9);

                // ------------------------------------------------------------------
                Transaccion t1 = new Transferencia(1, new GregorianCalendar(2020, 05, 13).getTime(), 10, "10:36",
                                "Primera transaccion", cu1, cu9);
                t1.setSucursal(s1);

                Transaccion t2 = new Transferencia(2, new GregorianCalendar(2020, 05, 12).getTime(), 73, "9:10",
                                "Primera transaccion de otra cuenta", cu1, cu3);
                t2.setSucursal(s2);

                Transaccion t3 = new Operacion(3, new GregorianCalendar(2020, 05, 14).getTime(), 10, "3:06",
                                "Retirada en cajero 1", cu7, "Retirada");
                t3.setSucursal(s1);

                Transaccion t4 = new Operacion(4, new GregorianCalendar(2020, 05, 14).getTime(), 10, "21:57",
                                "Retirada en cajero 2", cu6, "Retirada");
                t4.setSucursal(s2);

                Transaccion t5 = new Operacion(5, new GregorianCalendar(2020, 07, 11).getTime(), 20, "5:45",
                                "Retirada en cajero 3", cu5, "Retirada");
                t5.setSucursal(s3);

                Transaccion t6 = new Operacion(6, new GregorianCalendar(2020, 01, 22).getTime(), 50, "8:59",
                                "Retirada en cajero 2", cu4, "Retirada");
                t6.setSucursal(s2);

                Transaccion t7 = new Operacion(7, new GregorianCalendar(2019, 03, 29).getTime(), 100, "22:08",
                                "Retirada en cajero 1", cu3, "Retirada");
                t7.setSucursal(s1);

                Transaccion t8 = new Operacion(8, new GregorianCalendar(2020, 10, 9).getTime(), 50, "9:23",
                                "Retirada en cajero 1", cu2, "Retirada");
                t8.setSucursal(s1);

                Transaccion t9 = new Transferencia(3, new GregorianCalendar(2020, 05, 12).getTime(), 90, "2:34",
                                "Pago alquiler", cu4, cu1);
                t9.setSucursal(s2);

                Transaccion t10 = new Transferencia(4, new GregorianCalendar(2020, 01, 23).getTime(), 1212, "5:30",
                                "Dinero que me debe el gita", cu3, cu2);
                t10.setSucursal(s2);

                Transaccion t11 = new Transferencia(5, new GregorianCalendar(2019, 10, 11).getTime(), 124, "22:30",
                                "Pago a Ecotambo", cu1, cu3);
                t11.setSucursal(s2);

                Transaccion t12 = new Transferencia(6, new GregorianCalendar(2020, 06, 17).getTime(), 206, "7:54",
                                "Comida del otro dia", cu3, cu4);
                t12.setSucursal(s1);

                Transaccion t13 = new Transferencia(7, new GregorianCalendar(2020, 9, 11).getTime(), 400, "4:21",
                                "Ya tu sae", cu1, cu5);
                t13.setSucursal(s3);

                Transaccion t14 = new Transferencia(8, new GregorianCalendar(2017, 05, 12).getTime(), 224, "22:49",
                                "Pues mas cositas que quieres", cu7, cu6);
                t14.setSucursal(s3);

                Transaccion t15 = new Transferencia(9, new GregorianCalendar(2020, 10, 25).getTime(), 679, "23:01",
                                "Mas y mas pruebas", cu9, cu7);
                t15.setSucursal(s2);

                Transaccion t16 = new Transferencia(10, new GregorianCalendar(2020, 05, 10).getTime(), 666, "3:55",
                                "Pedro AMARGO", cu6, cu8);
                t16.setSucursal(s1);

                Transaccion t17 = new Transferencia(11, new GregorianCalendar(2020, 9, 23).getTime(), 1000, "7:45",
                                "Los dineros", cu6, cu9);
                t17.setSucursal(s1);

                Transaccion t18 = new Operacion(12, new GregorianCalendar(2010, 10, 9).getTime(), 120, "8:11",
                                "Hola dinero", cu4, "Ingreso");
                t18.setSucursal(s2);

                Transaccion t19 = new Operacion(13, new GregorianCalendar(2015, 11, 10).getTime(), 300, "3:55",
                                "Loteria", cu9, "Ingreso");
                t19.setSucursal(s2);

                Transaccion t20 = new Operacion(14, new GregorianCalendar(2016, 9, 30).getTime(), 568, "21:09",
                                "Para netflix", cu8, "Ingreso");
                t20.setSucursal(s1);

                Transaccion t21 = new Operacion(15, new GregorianCalendar(2019, 12, 13).getTime(), 780, "18:10",
                                "Pruebas", cu6, "Ingreso");
                t21.setSucursal(s3);

                Transaccion t22 = new Operacion(16, new GregorianCalendar(2020, 11, 07).getTime(), 15, "7:57",
                                "Paga de ECOTAMBO", cu2, "Ingreso");
                t22.setSucursal(s1);

                Transaccion t23 = new Operacion(13, new GregorianCalendar(2020, 1, 2).getTime(), 306, "11:03",
                                "Hola guapo", cu1, "Ingreso");
                t23.setSucursal(s2);

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                // ------------------
                em.persist(s1);
                em.persist(s2);
                em.persist(s3);
                // ------------------
                em.persist(c1);
                em.persist(c2);
                em.persist(c3);
                em.persist(c4);
                em.persist(c5);
                em.persist(c6);
                // ------------------
                em.persist(cu1);
                em.persist(cu2);
                em.persist(cu3);
                em.persist(cu4);
                em.persist(cu5);
                em.persist(cu6);
                em.persist(cu7);
                em.persist(cu8);
                em.persist(cu9);
                // ------------------
                em.persist(t1);
                em.persist(t2);
                em.persist(t3);
                em.persist(t4);
                em.persist(t5);
                em.persist(t6);
                em.persist(t7);
                em.persist(t8);
                em.persist(t9);
                em.persist(t10);
                em.persist(t11);
                em.persist(t12);
                em.persist(t13);
                em.persist(t14);
                em.persist(t15);
                em.persist(t16);
                em.persist(t17);
                em.persist(t18);
                em.persist(t19);
                em.persist(t20);
                em.persist(t21);
                em.persist(t22);
                em.persist(t23);

                em.getTransaction().commit();

                em.close();
        }

        private static void queryMasDinero() {
                // Query 1 en JPQL: "Users que más dinero han extraído de sus cuentas"

                EntityManager em = emf.createEntityManager();
                String query_text = "SELECT cl.Nombre, op.realizante.numCuenta, SUM(op.importe) "
                                + "FROM Operacion op JOIN op.realizante realizantes "
                                + "JOIN realizantes.propietarios cl " + "WHERE op.tipo = 'Retirada' "
                                + "GROUP BY op.realizante.numCuenta, cl.Nombre " + "ORDER BY SUM(op.importe) DESC";

                javax.persistence.Query query1 = em.createQuery(query_text);

                // Source: https://vladmihalcea.com/hibernate-resulttransformer/
                @SuppressWarnings("unchecked")
                List<Query1> results = query1.unwrap(org.hibernate.query.Query.class)
                                .setResultTransformer(new Query1Transformer()).getResultList();

                System.out.println("------ Mostrando Query1 en JPQL ------");

                for (Query1 q : results) {
                        System.out.println(q);
                }

                // TODO:He probado varias cosas pero los resultados son siempre del tipo
                // "java.lang.object"
                // printResult(results);

                // Query 1 en Criteria API
                // Source:
                // https://stackoverflow.com/questions/41982998/hibernate-criteriabuilder-to-join-multiple-tables/42019970

                javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();

                javax.persistence.criteria.CriteriaQuery query = cb.createQuery(Query1.class);
                javax.persistence.criteria.Root<Operacion> operacionTable = query.from(Operacion.class);
                javax.persistence.criteria.Join<Operacion, Cuenta> cuentaJoin = operacionTable.join("realizante");
                javax.persistence.criteria.Join<Cuenta, Cliente> clienteJoin = cuentaJoin.join("propietarios");

                List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(operacionTable.get("tipo"), "Retirada"));

                query.multiselect(clienteJoin.get("Nombre"), cuentaJoin.get("numCuenta"),
                                cb.sum(operacionTable.get("importe")));

                query.where(predicates.stream().toArray(javax.persistence.criteria.Predicate[]::new));
                query.groupBy(cuentaJoin.get("numCuenta"), clienteJoin.get("Nombre"));
                // query.orderBy(operacionTable.get("importe"));

                // Para poder hacer el Order By
                List<javax.persistence.criteria.Order> order = new ArrayList<>();
                order.add(cb.desc(cb.sum(operacionTable.get("importe"))));

                query.orderBy(order);

                TypedQuery<Query1> typedQuery = em.createQuery(query);

                List<Query1> resultList = typedQuery.getResultList();

                System.out.println("------ Mostrando Query1 en Criteria API ------");

                for (Query1 q : resultList) {
                        System.out.println(q);
                }

                em.close(); // Cerramos el Manager
        }

        private static void querySucursalesMenores30() {
                // Query2: Sucursales en las que los clientes menores de 30 años tienen cuentas
                // corrientes.

                // EN JPQL:

                EntityManager em = emf.createEntityManager();
                // He cambiado la orientación de los JOIN porque queda más simple
                String query_text = "SELECT suc.codigo, cl.Nombre, cl.Edad, cc.numCuenta " + "FROM Cliente cl "
                                + "JOIN cl.cuentas cc " + "JOIN cc.sucursal suc " + "WHERE cl.Edad < 30 "
                                + "ORDER BY suc.codigo";

                javax.persistence.Query query2 = em.createQuery(query_text);

                // Source: https://vladmihalcea.com/hibernate-resulttransformer/
                @SuppressWarnings("unchecked")
                List<Query2> results = query2.unwrap(org.hibernate.query.Query.class)
                                .setResultTransformer(new Query2Transformer()).getResultList();

                System.out.println("------ Mostrando Query2 en JPQL ------");

                for (Query2 q : results) {
                        System.out.println(q);
                }

                // Query 2 en Criteria API

                javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();

                javax.persistence.criteria.CriteriaQuery query = cb.createQuery(Query2.class);
                javax.persistence.criteria.Root<Cliente> clienteTable = query.from(Cliente.class);
                javax.persistence.criteria.Join<Cliente, Cuenta> cuentaJoin = clienteTable.join("cuentas");

                // Seleccionamos tabla hija
                javax.persistence.criteria.Join cuentaCorrienteJoin = cb.treat(cuentaJoin, CuentaCorriente.class);

                javax.persistence.criteria.Join<CuentaCorriente, Sucursal> sucursalJoin = cuentaCorrienteJoin
                                .join("sucursal");

                List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
                predicates.add(cb.lt(clienteTable.get("Edad"), 30));

                query.multiselect(sucursalJoin.get("codigo"), clienteTable.get("Nombre"), clienteTable.get("Edad"),
                                cuentaCorrienteJoin.get("numCuenta")).distinct(true);

                query.where(predicates.stream().toArray(javax.persistence.criteria.Predicate[]::new));

                // Para poder hacer el Order By
                List<javax.persistence.criteria.Order> order = new ArrayList<>();
                order.add(cb.asc(sucursalJoin.get("codigo")));

                query.orderBy(order);

                TypedQuery<Query2> typedQuery = em.createQuery(query);

                List<Query2> resultList = typedQuery.getResultList();

                System.out.println("------ Mostrando Query2 en Criteria API ------");

                for (Query2 row : resultList) {
                        System.out.println(row);
                }

        }

        private static void LastDataTransaction() {
                // Query3: Ultima transaccion de todas cuentas de todos los clientes

                // EN JPQL:

                EntityManager em = emf.createEntityManager();
                // He cambiado la orientación de los JOIN porque queda más simple
                String query_text = "SELECT cl.Nombre, real.numCuenta, tr.fecha "
                                + "FROM Transaccion tr JOIN tr.realizante real JOIN real.propietarios cl "
                                + "WHERE tr.fecha IN ( SELECT MAX(tr2.fecha) FROM Transaccion tr2 WHERE tr2.realizante.numCuenta = tr.realizante.numCuenta) "
                                + "GROUP BY cl.Nombre, real.numCuenta, tr.fecha ORDER BY cl.Nombre";

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

        private static void queryMaxIngresoTransaccion() {
                // Query4: Máximo movimiento en una transacción en cada una de las
                // cuentas de cada cliente
                EntityManager em = emf.createEntityManager();

                String query_text = "SELECT cl.Nombre, cuen.numCuenta, tr.importe " + "FROM Transaccion tr "
                                + "JOIN tr.realizante cuen JOIN cuen.propietarios cl "
                                + "WHERE tr.importe = " + "(SELECT MAX(tr1.importe) as Importe "
                                + "FROM Transaccion tr1 WHERE tr1.realizante.numCuenta = tr.realizante.numCuenta) "
                                + "ORDER BY cl.Nombre";

                javax.persistence.Query query4 = em.createQuery(query_text);

                // Source: https://vladmihalcea.com/hibernate-resulttransformer/
                @SuppressWarnings("unchecked")
                List<Query4> results = query4.unwrap(org.hibernate.query.Query.class)
                                .setResultTransformer(new Query4Transformer()).getResultList();

                System.out.println("------ Mostrando Query4 en JPQL ------");

                for (Query4 q : results) {
                        System.out.println(q);
                }

        }

        private static void IngresosMasJoven() {
                // Query5: Retirada total de dinero de las cuentas del usuario mas viejo

                // EN JPQL:

                EntityManager em = emf.createEntityManager();
                // He cambiado la orientación de los JOIN porque queda más simple
                String query_text = "SELECT cl.Nombre, realizantes.numCuenta, SUM(op.importe), cl.Edad "
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
