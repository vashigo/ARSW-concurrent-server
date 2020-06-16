package edu.escuelaing.arsw.servidorWeb.socketsWeb;

import java.io.IOException;

/**
 * Socket of Server
 * 
 * @author Vashi
 */
public class ServerSocket {

    public ServerSocket() {
    }

    /**
     * Create a new server Socket
     * 
     * @return 
     */
    public static java.net.ServerSocket getNewServerSocket() {
        java.net.ServerSocket serverSocket = null;
        try {
            serverSocket = new java.net.ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port:" + getPort() + ".");
            System.exit(1);
        }
        return serverSocket;
    }

    /**
     * get port the socket server
     * 
     * @return 
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
