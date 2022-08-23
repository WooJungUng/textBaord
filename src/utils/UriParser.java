package utils;

import java.util.HashMap;
import java.util.Map;

public class UriParser {

    /*
    uri규칙
    - /articles/write
    - /articles/delete?id=1

     */

    private String URI;
    private String controllerCode;
    private String target;
    private boolean isValid = false;

    private Map<String, Object> parameter = new HashMap<>();

    public UriParser(String uri){
        this.URI = uri;
        parse();
    }

    private void parse(){

        if(!this.URI.startsWith("/")){
            this.isValid = false;
            return;
        }

        String [] uriSplit = this.URI.split("\\?", 2);

        if(uriSplit.length == 2){

            String paramBody = uriSplit[1];
            String [] splitParam = paramBody.split("=", 2);

            parameter.put(splitParam[0], splitParam[1]);


        }

        String[] uriBodysplit = uriSplit[0].split("/");

        if(uriBodysplit.length != 3){
            this.isValid = false;
            return;
        }

        this.controllerCode = uriBodysplit[1];
        this.target = uriBodysplit[2];

        isValid = true;

    }

    public String getControllerCode() {
        return controllerCode;
    }

    public String getTarget() {
        return target;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

}
