package edu.escuelaing.arsw.servidorWeb.reader;

import java.io.*;
import java.net.Socket;

/**
 * select what type of parameter it is if it is png, html, jpg, css, javascript
 * or ico
 *
 * @author vashi
 */
public class InterpreterResource {

    public Socket clientSocket;

    InterpreterResource(String resource, Socket clientSocket) {
        this.clientSocket = clientSocket;
        if (resource.toLowerCase().contains(".html")) {
            textResource(resource, "html");
        } else if (resource.toLowerCase().contains(".css")) {
            textResource(resource, "css");
        } else if (resource.toLowerCase().contains(".js")) {
            textResource(resource, "js");
        } else if (resource.toLowerCase().contains(".png")) {
            imageResource(resource, "png");
        } else if (resource.toLowerCase().contains(".jpg")) {
            imageResource(resource, "jpg");
        } else if (resource.toLowerCase().contains(".ico")) {
            imageResource(resource, "ico");
        } else {
            errorType();
        }
    }

    /**
     * error para mostrar en pantalla de que no se puede procesar otro formato
     * que nosea .html o .png
     */
    private void errorType() {
        try {
            PrintWriter out;
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 Erro Type Not Implemented\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Error Type</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>erroType: solo se procesa archivos con .html o .png</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de mostrar en pantalla al no encontrar la direccion o archivo
     * especificado
     */
    private void raise404() {
        try {
            PrintWriter out;
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 404 Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>404 Not Found</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error 404: Contenido del archivo no correto o no encontrado</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de manejar las imagenes de cualquier formato almacenado en
     * resources
     *
     * @param resource
     */
    private void imageResource(String resource, String type) {
        try {
            File graphicResource = new File("resources" + resource);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/" + type + "\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex) {
            raise404();
        }

    }

    /**
     * Se encarga de manejar el html almacenado en resources
     *
     * @param resource
     */
    private void textResource(String resource, String type) {
        PrintWriter out = null;
        try {
            String outputLine = "";
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader bf = new BufferedReader(new FileReader("resources" + resource));
            outputLine = "HTTP/1.1 200 OK \r\n";
            outputLine += "Content-Type: text/" + type + "\r\n";
            outputLine += "\r\n\r\n";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine += bfRead;
            }
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            raise404();
        }
    }

}
