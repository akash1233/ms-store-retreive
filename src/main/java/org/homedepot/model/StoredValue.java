package org.homedepot.model;

/**
 * Created by dharma on 8/11/17.
 */
public class StoredValue {
    private String varname;
    private String value;
    private String param;


    public StoredValue(String param) {
        super();
        this.varname = param.split("=")[0].toString();
        this.value = param.split("=")[1].toString();

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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return String.format(
                "StoredValue [varname=%s, value=%s, param=%s]", varname,
                value, param);
    }


}



