package edu.escuelaing.arsw.servidorWeb.socketsWeb;

import edu.escuelaing.arsw.servidorWeb.reader.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socket of Client
 *
 * @author Vashi
 */
public class ClientSocket implements Runnable {

    private final Socket clientSocket;

    public ClientSocket(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public synchronized  void run() {
        ResourceWriter rw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String path = getPath(in);
            rw = ResourceChooser.choose(path);
            rw.write(path, clientSocket);
            in.close();
            wait();
            clientSocket.close();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage() + ": Error del proceso en el servidor");
        }
    }

    /**
     * Captura el path de una peticion GET
     *
     * @param in Buffer del Socket del Cliente
     * @return el path del archivo.
     * @throws IOException si no es posible leer el buffer
     */
    public static String getPath(BufferedReader in) throws IOException {
        String inputLine, path = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (inputLine.contains("GET")) {
                path = inputLine.split(" ")[1];
            }
            if (!in.ready()) {
                break;
            }
        }
        return path;
    }

}
