package bases2.queries;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import bases2.queries.*;


// Source: https://stackoverflow.com/questions/52568707/jpa-repository-query-java-lang-object-cannot-be-cast-to-model
public class Query3Transformer implements org.hibernate.transform.ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Query3 q = new Query3();
        q.setNombre((String)tuple[0]);
        q.setNumCuenta((String)tuple[1]);
        q.setFecha((Timestamp)tuple[2]);   
        return q;
    }

    @Override
    public List transformList(List collection) {
       return collection;
    }
}