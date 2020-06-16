package edu.escuelaing.arsw.servidorWeb.reader;

import java.util.HashMap;
import java.util.Map;

/**
 * select what type of parameter it is if it is png, html, jpg, css, 
 * javascript or ico
 * 
 * @author vashi
 */
public class ResourceChooser {

    public static Map<String, ResourceWriter> type = new HashMap<String, ResourceWriter>() {
        {
            put("html", new TextWriter("html"));
            put("png", new ImageWriter("png"));
            put("jpg", new ImageWriter("jpg"));
            put("js", new TextWriter("javascript"));
            put("css", new TextWriter("css"));
            put("ico", new ImageWriter("ico"));
            put("err",new ErrorWriter("501 No Implementado"));
        }
    };

    /**
     * method responsible for interpreting and writing the resource
     * 
     * @param path
     * @return
     * @throws Exception 
     */
    public static ResourceWriter choose(String path) throws Exception {
        String resource = "";
        try {
            String[] s = path.split("\\.");
            resource = s[s.length - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(" No es una peticion de Recurso Especifico/ Peticion mal formada");
        }
        if (!type.containsKey(resource)){
            return type.get("err");           
        }else{
            return type.get(resource);      
        }
    }

}
