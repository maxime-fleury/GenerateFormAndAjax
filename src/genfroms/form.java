/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genfroms;

/**
 *
 * @author acs
 */
public class form {
    private String type;
    private String value;
    private String placeholder;
    public form(){
        this.type = null;
        this.value = null;
        this.placeholder = null;
    }
    public form(String type, String value, String placeholder){
       this.type = type;
       this.value = value;
       this.placeholder = placeholder;
    }
    public void setType(String t){
        this.type = t;
    }
    public void setValue(String v){
        this.value = v;
    }
    public void setPlaceholder(String p){
        this.placeholder = p;
    }
    public String[] getAll(){
        String[] res = new String[3];
        res[0] = this.type;
        res[1] = this.value;
        res[2] = this.placeholder;
        return res;
    }
    public String getLine(){
        String res = "";
        res += "<input type='" + this.type + "' value='" + this.value + "' name='" + this.value + "' placeholder='" + this.placeholder + "'/>";
        return res;
    }
    public String getValue(){
        return this.value;
    }
}
