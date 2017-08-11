package org.homedepot.controller;

import org.homedepot.model.StoredValue;
import org.homedepot.service.StoreRetreiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dharma on 8/11/17.
 */
@RestController
public class SRController {
    @Autowired
    private StoreRetreiveService storeRetreiveService;

    @GetMapping("/storedval/{variableVal}")
    public StoredValue retrieveStoredVarVlaue(@PathVariable String variableVal) {
        return storeRetreiveService.retreiveVarValue(variableVal);

    }


}
