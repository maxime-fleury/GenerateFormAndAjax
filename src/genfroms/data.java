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
public class data {
    private String method;
    private String action;
    private String SubmitText;
    private String div_to_remplace;
    private form[] f;
    private int cntForm;
    public data(){
        this.SubmitText = "";
        this.cntForm = 0;
    }
    public void addForm(String type, String value, String placeholder){
        form tmp = new form(type, value, placeholder);
        form[] tmp2 = this.f;
        
        this.f = new form[this.cntForm+1];
        
        for(int i = 0; i < this.cntForm;i++){
            this.f[i] = tmp2[i];
        }
        
        this.f[(this.cntForm)] = tmp;
        this.cntForm++;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public void setAction(String action){
        this.action = action;
    }
    public void setSubmitText(String s){
        this.SubmitText = s;
    }
    public void setDivtoreplace(String s){
        this.div_to_remplace = s;
    }
    public void editForm(int cnt, String tmp, String text){
        cnt = cnt-1;
        switch (tmp){
            case "type" : f[cnt].setType(text); break;
            case "value" : f[cnt].setValue(text); break;
            case "placeholder" :f[cnt].setPlaceholder(text); break;
        }
    }
    public int getCntForm(){
        return this.cntForm;
    }
    public String getFormCode(){
        String res = "";
        res += "<form action='" + this.action + "' method='" + this.method + "'>\n";
        for(int i =0; i < cntForm; i++)
            res+= "\t" + this.f[i].getLine() + "\n";
        //res += "\t<input type='submit' value='" + this.SubmitText + "'>\n";
        res += "<a id='send' onclick='return loadXMLDoc(\"" + this.action + "\");'> " + this.SubmitText +  " </a>";
        res += "</form>";
        res += getAjaxCode();
        return res;
    }
    public String getAjaxCode(){
        String res = "";
        
            res += "\n<script>\n";
            res += getGetParams();
            res +="function loadXMLDoc(page) {\n" +
"    var xmlhttp = new XMLHttpRequest();\n" +
"\n" +
"    xmlhttp.onreadystatechange = function() {\n" +
"        if (xmlhttp.readyState == XMLHttpRequest.DONE) {   // XMLHttpRequest.DONE == 4\n" +
"           if (xmlhttp.status == 200) {\n" +
"               document.getElementById(\"" + div_to_remplace + "\").innerHTML = xmlhttp.responseText;\n" +
"               console.log(xmlhttp.responseText);\n" +
"           }\n" +
"           else if (xmlhttp.status == 400) {\n" +
"              alert('There was an error 400');\n" +
"           }\n" +
"           else {\n" +
"               alert('something else other than 200 was returned');\n" +
"           }\n" +
"        }\n" +
"    };\n" +
"\n" +
                    
"    xmlhttp.open(\"" + method + "\", page, true);\n" +
"    xmlhttp.setRequestHeader(\"Content-Type\", \"application/x-www-form-urlencoded\");\n" +             
"    xmlhttp.send(" + getParams() + ");\n}\n</script>" +
"";
        return res;
    }
    //generate All Params For post or get Request
    public String getParams(){
        String res = "";
        for(int i =0; i < cntForm; i++){
           if(i!=0) res += " + ";
            res += "\"";
           if(i!=0) res+= "&";
           res += this.f[i].getValue() + " = \" + params." + this.f[i].getValue();
        }
        return res;
    }
    //generate the function that gets params
    public String getGetParams(){
        String res = "var params = new Object();\n";
        for(int i =0; i < cntForm; i++)
           res+= "params." + this.f[i].getValue() + " = document.getElementById(\"" + this.f[i].getValue() + "\").value;\n";
        return res;
    }
}
