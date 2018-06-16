/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesApoyo;

import controladores.BarraProgresoController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author raymundo
 */
public class HiloCargaCanciones implements Runnable {

    private byte[] zip;
    private int puerto;
    private String ip;
    private String ruta;
    private BarraProgresoController barra;

    public HiloCargaCanciones(byte[] zip, int puerto, String ip, String ruta, BarraProgresoController barra) {
        this.zip = zip;
        this.puerto = puerto;
        this.ip = ip;
        this.ruta = ruta;
        this.barra = barra;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, puerto);
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            salida.writeObject(true);
            salida.writeObject(ruta);
            salida.writeObject(zip);
            socket.close();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    barra.cargaCompleta();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HiloCargaCanciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
