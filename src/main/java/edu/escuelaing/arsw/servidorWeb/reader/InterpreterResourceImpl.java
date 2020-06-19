package edu.escuelaing.arsw.servidorWeb.reader;

import java.net.Socket;

/**
 * class extends InterpreterResource
 * 
 * @author Vashi
 */
public class InterpreterResourceImpl extends InterpreterResource {
    
    /**
     * constructor implementation
     * 
     * @param resource - String resource format
     * @param clientSocket - socket of client
     */
    public InterpreterResourceImpl(String resource, Socket clientSocket) {
        super(resource, clientSocket);
    }
    
}
