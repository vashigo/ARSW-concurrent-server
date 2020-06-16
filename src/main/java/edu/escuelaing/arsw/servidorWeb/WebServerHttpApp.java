/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.servidorWeb;

import java.io.IOException;

/**
 *
 * @author Vashi
 */
public class WebServerHttpApp {

    public static void main(String[] args) throws IOException {
        //socket con 1000 threads (users)
        WebServerHttp webServer = new WebServerHttp(1000);
        webServer.start();
    }
}
