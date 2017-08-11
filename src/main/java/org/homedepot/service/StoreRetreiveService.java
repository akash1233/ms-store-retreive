package org.homedepot.service;

import org.homedepot.model.StoredValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dharma on 8/11/17.
 */
@Component
public class StoreRetreiveService {
    private static List<StoredValue> tempvalue = new ArrayList<>();


    public StoredValue retreiveVarValue (String variableVal){
       for (StoredValue storedValue : tempvalue){
           if (storedValue.getVarname().equalsIgnoreCase(variableVal)){
               return storedValue;
           }
       }
       return null;
    }

}
