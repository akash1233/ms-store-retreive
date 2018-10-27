package org.cts.controller;

import org.cts.model.StoredValue;
import org.cts.service.StoreRetreiveService;
import org.cts.util.CustomeErrorType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //------ Retreives a single param variable

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


    // --------- Stores a param value
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



    // ---- update and variable value
    @RequestMapping(value= "storevalue/{variablename}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateVarValue(@PathVariable("variablename") String varName , @RequestBody StoredValue storedValue){
        logger.info("Updating Variable with name : " + varName + " with the value " + storedValue.getValue());
       if (storeRetreiveService.updateVarValue(storedValue)){
           return new ResponseEntity<StoredValue>(storedValue , HttpStatus.OK);
       } else {
           logger.error("Variable trying to update not found : " + varName);
           return new ResponseEntity<CustomeErrorType>(new CustomeErrorType("Unable to updated a variable " + storedValue.getVarname()), HttpStatus.NOT_FOUND);
       }


    }

    // ---- Delete all variable
    @RequestMapping(value = "/retrivestoredval/" , method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllVarsVals(){
        logger.info("Deleting all variables ");
        storeRetreiveService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



}
