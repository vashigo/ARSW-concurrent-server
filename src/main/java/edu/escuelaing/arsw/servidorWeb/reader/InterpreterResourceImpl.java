/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.servidorWeb.reader;

import java.net.Socket;


public class InterpreterResourceImpl extends InterpreterResource {

    public InterpreterResourceImpl(String resource, Socket clientSocket) {
        super(resource, clientSocket);
    }
    
}
