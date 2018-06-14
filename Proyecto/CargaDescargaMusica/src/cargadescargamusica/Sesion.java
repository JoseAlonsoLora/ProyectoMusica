package cargadescargamusica;

import java.io.ByteArrayInputStream;
import java.io.File;
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

    public Sesion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            archivo = (byte[]) entrada.readObject();

            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(archivo));
            ZipEntry entry = null;

            File zipNuevo = new File("C:\\Users\\iro19\\Documents\\6to\\");
            Path outDir = Paths.get("C:\\Users\\iro19\\Documents\\6to\\");

            //guardarArchivo(buffer, "/home/alonso/Documents/cancer.zip");
            while ((entry = zipStream.getNextEntry()) != null) {
                Path filePath = outDir.resolve(entry.getName());
                String entryName = entry.getName();

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
            
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
