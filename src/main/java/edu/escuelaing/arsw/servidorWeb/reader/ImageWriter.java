package edu.escuelaing.arsw.servidorWeb.reader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * class in charge of interpreting an image
 * 
 * @author vashi
 */
public class ImageWriter implements ResourceWriter {

    private String type;

    public ImageWriter(String type) {
        this.type = type;
    }

    @Override
    public void write(String file, Socket clientSocket) {
        try {
            File graphicResource = new File("resources" + file);
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
            new ErrorWriter("404 Not Found").write("", clientSocket);
        }
    }

    @Override
    public String exactType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
