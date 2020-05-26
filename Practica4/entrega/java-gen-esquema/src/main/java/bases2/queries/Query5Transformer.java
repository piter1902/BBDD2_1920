package bases2.queries;

import java.util.LinkedHashMap;
import java.util.List;

import bases2.queries.*;

public class Query5Transformer implements org.hibernate.transform.ResultTransformer {
    
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Query5 q = new Query5();
        q.setNombre((String)tuple[0]);
        q.setNumCuenta((String)tuple[1]);
        q.setSumaImporte((Long)tuple[2]);
        q.setEdad((int)tuple[3]);
        return q;
    }

    @Override
    public List transformList(List collection) {
       return collection;
    }
}