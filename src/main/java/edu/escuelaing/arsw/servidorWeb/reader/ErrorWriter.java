package edu.escuelaing.arsw.servidorWeb.reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class in charge of interpreting an error
 * @author vashi
 */
public class ErrorWriter implements ResourceWriter {

    private final String message;

    public ErrorWriter(String message) {
        this.message = message;
    }

    @Override
    public void write(String file, Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String outputLine = "HTTP/1.1 " + message + "\r\n"
                    + "Content-Type: text/html\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>" + message + "</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error " + message + "</h1>"
                    + "</body>"
                    + "</html>";
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String exactType() {
        return "error";
    }
}
