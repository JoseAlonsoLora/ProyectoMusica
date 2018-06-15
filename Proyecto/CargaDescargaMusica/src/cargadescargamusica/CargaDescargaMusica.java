package cargadescargamusica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author iro19
 */
public class CargaDescargaMusica {

    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor = new ServerSocket(8000);
        while (true) {
            Socket socketCliente = socketServidor.accept();
            System.out.println("Cliente conectado");
            Sesion sesion = new Sesion(socketCliente);
            Thread hiloSesion = new Thread(sesion);
            hiloSesion.start();
        }

    }

}
