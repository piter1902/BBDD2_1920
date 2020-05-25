package bases2.queries;

import java.util.LinkedHashMap;
import java.util.List;

import bases2.queries.*;


// Source: https://stackoverflow.com/questions/52568707/jpa-repository-query-java-lang-object-cannot-be-cast-to-model
public class Query1Transformer implements org.hibernate.transform.ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Query1 q = new Query1();
        
        q.setNombre((String)tuple[0]);
        q.setNumCuenta((int)tuple[1]);
        q.setSumaImporte((Long)tuple[2]);
        return q;
    }

    @Override
    public List transformList(List collection) {
       return collection;
    }
}