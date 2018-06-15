package cargadescargamusica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author iro19
 */
public class Sesion implements Runnable {

    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private byte[] archivo;
    private String rutaGuardado;

    public Sesion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            rutaGuardado = (String) entrada.readObject();
            System.out.println(rutaGuardado);
            archivo = (byte[]) entrada.readObject();

            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(archivo));
            ZipEntry entry = null;
            String rutaFinal = "/home/alonso/Desktop/Musica/" + rutaGuardado;
            File zipNuevo = new File(rutaFinal);
            if (!zipNuevo.exists()) {
                zipNuevo.mkdirs();
            }
            Path outDir = Paths.get(rutaFinal);
            String entryName = "";
            //guardarArchivo(buffer, "/home/alonso/Documents/cancer.zip");
            while ((entry = zipStream.getNextEntry()) != null) {
                Path filePath = outDir.resolve(entry.getName());
                entryName = entry.getName();
                System.out.println(entryName);
                FileOutputStream out = new FileOutputStream(filePath.toFile());

                byte[] byteBuff = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = zipStream.read(byteBuff)) != -1) {
                    out.write(byteBuff, 0, bytesRead);
                }

                out.close();
                zipStream.closeEntry();
            }

            zipStream.close();
            descomprimirZip(rutaFinal, rutaFinal + "/" + entryName);

        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descomprimirZip(String ruta, String rutaZip) {
        final int TAM_BUFFER = 4096;
        byte[] buffer = new byte[TAM_BUFFER];

        ZipInputStream flujo = null;
        try {
            flujo = new ZipInputStream(new BufferedInputStream(
                    new FileInputStream(rutaZip)));
            ZipEntry entrada;
            while ((entrada = flujo.getNextEntry()) != null) {
                String nombreSalida = ruta + File.separator
                        + entrada.getName();
                BufferedOutputStream salida = null;
                try {
                    int leido;
                    salida = new BufferedOutputStream(
                            new FileOutputStream(nombreSalida), TAM_BUFFER);
                    while ((leido = flujo.read(buffer, 0, TAM_BUFFER)) != -1) {
                        salida.write(buffer, 0, leido);
                    }
                } finally {
                    if (salida != null) {
                        salida.close();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (flujo != null) {
                try {
                    flujo.close();
                    File file = new File(rutaZip);
                    file.delete();
                } catch (IOException ex) {
                    Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

}
