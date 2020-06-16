package edu.escuelaing.arsw.servidorWeb.reader;

import java.net.Socket;

/**
 * interface that houses the methods for writing and interpreting the type
 *
 * @author vashi
 */
public interface ResourceWriter {

    /**
     * method that is responsible for interpreting the file
     *
     * @param file string path file
     * @param clientSocket socket client or server
     */
    public void write(String file, Socket clientSocket);

    /**
     * method that is responsible for interpreting the type
     *
     * @return
     */
    public String exactType();
}
