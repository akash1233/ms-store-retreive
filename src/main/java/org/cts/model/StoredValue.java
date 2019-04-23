package org.cts.model;

/**
 * Created by dharma on 8/11/17.
 */
public class StoredValue {
    private String varname;
    private String value;


    public StoredValue(String varname, String value) {
        this.varname = varname;
        this.value = value;

    }

    public StoredValue() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVarname() {
        return varname;
    }

    public void setVarname(String varname) {
        this.varname = varname;
    }

    public boolean testBoooleanValue() {
        return true;

    }
}





