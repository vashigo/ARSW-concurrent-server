package edu.escuelaing.arsw.servidorWeb.socketsWeb;

import edu.escuelaing.arsw.servidorWeb.reader.InterpreterResourceImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socket of Client
 *
 * @author Vashi
 */
public class ClientSocketThread implements Runnable {

    private final Socket clientSocket;

    public ClientSocketThread(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        InterpreterResourceImpl rw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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

            rw = new InterpreterResourceImpl(path ,clientSocket);
            
            in.close();

            
        } catch (Exception ex) {
            System.err.println(ex.getMessage() + ": error processing server information");
        }
    }
}
