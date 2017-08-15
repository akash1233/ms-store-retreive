package org.homedepot.service;

import org.homedepot.model.StoredValue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dharma on 8/11/17.
 */
@Service
public class StoreRetreiveServiceImpl implements StoreRetreiveService {

    private List<StoredValue> storedValues;

    @PostConstruct
    public void init() {

        System.out.println("Initializing User Service");
        storedValues = new ArrayList<StoredValue>();
        StoredValue first = new StoredValue("xyz", "2345");
        this.saveVarValue(first);

    }


    public List<StoredValue> listAllVarValue() {
        return storedValues;
    }


    public StoredValue retreiveByVarName(String varname) {
        for (StoredValue storedValue : storedValues) {
            if (varname.equalsIgnoreCase(storedValue.getVarname())) {
                return storedValue;
            }
        }
        return null;
    }


    public boolean varExits(StoredValue newvar) {

        for (StoredValue storedValue : storedValues) {
            if (newvar.getVarname().equalsIgnoreCase(storedValue.getVarname())) {
                return true;
            }

        }
        return false;
    }


    public void saveVarValue(StoredValue newvarval) {
        storedValues.add(newvarval);
    }
}
