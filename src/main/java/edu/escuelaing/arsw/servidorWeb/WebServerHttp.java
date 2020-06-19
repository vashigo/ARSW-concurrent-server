package edu.escuelaing.arsw.servidorWeb;

import edu.escuelaing.arsw.servidorWeb.socketsWeb.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * main web server interpreter
 *
 * @author vashi
 */
public class WebServerHttp {

    private final boolean run;
    private ExecutorService poolThreads;
    public ServerSocket serverSocket;

    public WebServerHttp(int nThread) {
        run = true;
        poolThreads = Executors.newFixedThreadPool(nThread);
    }

    /**
     * start concurrent Web Server
     *
     * @throws IOException
     */
    public void start() throws IOException {
        //ServerSocket
        serverSocket = edu.escuelaing.arsw.servidorWeb.socketsWeb.ServerSocket.getNewServerSocket();
        while (run) {

            try {

                Socket socket;
                socket = serverSocket.accept();
                System.out.println("\nNueva conexión entrante: " + socket +"\n");
                Runnable process = new ClientSocketThread(socket);
                poolThreads.execute(process);

            } catch (IOException e) {
                System.out.println("\nError al intentar escuchar por el puerto " + serverSocket.getLocalPort() + " o la conexión con el cliente\n");
                System.out.println(e.getMessage());
            }

        }
    }
}
