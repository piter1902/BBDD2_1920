package bases2.queries;

import java.util.LinkedHashMap;
import java.util.List;

import bases2.queries.*;


// Source: https://stackoverflow.com/questions/52568707/jpa-repository-query-java-lang-object-cannot-be-cast-to-model
public class Query2Transformer implements org.hibernate.transform.ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Query2 q = new Query2();
        
        q.setCodigo((int)tuple[0]);
        q.setNombre((String)tuple[1]);
        q.setEdad((int)tuple[2]);
        q.setNumCuenta((String)tuple[3]);
        return q;
    }

    @Override
    public List transformList(List collection) {
       return collection;
    }
}