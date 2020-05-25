package bases2.queries;

import java.util.LinkedHashMap;
import java.util.List;

import bases2.queries.*;

public class Query4Transformer implements org.hibernate.transform.ResultTransformer {
    
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Query4 q = new Query4();
        
        q.setNumCuenta((int)tuple[0]);
        q.setSumaImporte((int)tuple[1]);

        return q;
    }

    @Override
    public List transformList(List collection) {
       return collection;
    }
}