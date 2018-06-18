package cargadescargamusica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
    private Boolean guardarArchivo;
    private ArrayList<String> rutasCanciones;

    public Sesion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            guardarArchivo = (Boolean) entrada.readObject();
            if (guardarArchivo) {
                rutaGuardado = (String) entrada.readObject();
                System.out.println(rutaGuardado);
                archivo = (byte[]) entrada.readObject();

                ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(archivo));
                ZipEntry entry = null;
                String rutaFinal = "/home/raymundo170/ServidorCanciones/" + rutaGuardado;
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
            } else {
                rutasCanciones = (ArrayList<String>) entrada.readObject();
                enviarCanciones();
            }
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

    public void enviarCanciones() {
        try {
            String rutaGuardado = "/home/raymundo170/ServidorCanciones/";
            File archivoZip = new File("/home/raymundo170/ServidorCanciones/fichero.zip");
            archivoZip.createNewFile();
            ZipOutputStream os = new ZipOutputStream(new FileOutputStream(archivoZip.getAbsolutePath()));
            for (String ruta : rutasCanciones) {
                String[] auxiliar = ruta.split("/");
                String nombre = auxiliar[2];
                ZipEntry entrada = new ZipEntry(nombre);
                os.putNextEntry(entrada);
                FileInputStream fis = new FileInputStream(rutaGuardado+ruta);
                byte[] buffer = new byte[1024];
                int leido = 0;
                while (0 < (leido = fis.read(buffer))) {
                    os.write(buffer, 0, leido);
                }
                fis.close();
                os.closeEntry();
            }
            os.close();   
            enviar(archivoZip);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviar(File archivoCanciones){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
            
            zipOutputStream.putNextEntry(new ZipEntry(archivoCanciones.getName()));
            FileInputStream fileInputStream = new FileInputStream(archivoCanciones);
            byte[] buffer = new byte[1024];
            int byteRead;
            while ((byteRead = fileInputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.flush();
            }
            byte[] zip = byteArrayOutputStream.toByteArray();
            salida.writeObject(zip);
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
