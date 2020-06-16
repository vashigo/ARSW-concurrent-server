package edu.escuelaing.arsw.servidorWeb.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * class in charge of interpreting an file html, javascript or css
 * @author vashi
 */
public class TextWriter implements ResourceWriter {

    private String type;

    public TextWriter(String type) {
        this.type = type;
    }

    @Override
    public void write(String file, Socket clientSocket) {
        PrintWriter out=null;
        try {
            String outputLine="";
            out = new PrintWriter(clientSocket.getOutputStream(), true);           
            BufferedReader bf = new BufferedReader(new FileReader("resources" + file));
            outputLine = "HTTP/1.1 200 OK\r\n";
            outputLine+="Content-Type: text/"+type+"\n";
            outputLine+="\r\n";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine+=bfRead;
            }
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            new ErrorWriter("404 Not Found").write("", clientSocket);
        }
        
    }     

    @Override
    public String exactType() {
        return "text/" + type;
    }

}
