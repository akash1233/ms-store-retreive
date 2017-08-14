package org.homedepot.controller;

import org.homedepot.model.StoredValue;
import org.homedepot.service.StoreRetreiveService;
import org.homedepot.util.CustomeErrorType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by dharma on 8/11/17.
 */
@RestController
@RequestMapping("/api")
public class SRController {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SRController.class);

    @Autowired
    StoreRetreiveService storeRetreiveService;


    // ---- Shows all the stored value

    @RequestMapping(value = "/retrivestoredval/" , method = RequestMethod.GET)
    public ResponseEntity<List<StoredValue>> listAllVarValue() {
        List<StoredValue> storedValues = storeRetreiveService.listAllVarValue();
        if (storedValues.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<StoredValue>>(storedValues, HttpStatus.OK);

    }


    @RequestMapping(value = "/retrivestoredval/{variablename}" , method = RequestMethod.GET)
    public ResponseEntity<?> getVarValue(@PathVariable("variablename") String varName) {
        logger.info("retreiving the var name value :" + varName);
        StoredValue storedValue = storeRetreiveService.retreiveByVarName(varName);
        if (storedValue == null){
            logger.error("Variable value not found : " + varName);
            return new ResponseEntity<CustomeErrorType>(new CustomeErrorType("Variable Not found "+ varName), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<StoredValue>(storedValue, HttpStatus.OK);

    }
    @RequestMapping(value = "/storevalue/" ,  method = RequestMethod.POST)
    public ResponseEntity<?> createVarValue(@RequestBody StoredValue storedValue) {
        logger.info("Creating Variable and it's value : " + storedValue);
        if (storeRetreiveService.varExits(storedValue)) {
            logger.error("Variable already exists " + storedValue.getVarname());
            return new ResponseEntity<CustomeErrorType>(new CustomeErrorType("Unable to create a variable " + storedValue.getVarname()), HttpStatus.CONFLICT);
        }
        storeRetreiveService.saveVarValue(storedValue);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(uriComponentsBuilder.path("/api/storevalue/{variablename}").buildAndExpand(storedValue.getVarname()).toUri());
        return new ResponseEntity<StoredValue>(storedValue , HttpStatus.CREATED);
    }


}
