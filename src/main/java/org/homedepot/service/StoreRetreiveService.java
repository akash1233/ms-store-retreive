package org.homedepot.service;

import org.homedepot.model.StoredValue;

import java.util.List;

/**
 * Created by dharma on 8/14/17.
 */
public interface StoreRetreiveService  {
    List<StoredValue> listAllVarValue();
    StoredValue 		retreiveByVarName(String varname);
    boolean		varExits(StoredValue user);
    void		saveVarValue(StoredValue user);

}
