/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteCancion;
import com.jfoenix.controls.JFXButton;
import com.mycompany.proyectomusica.MainApp;
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
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import modelo.Cancion;
import modelo.Listareproduccion;

/**
 * FXML Controller class
 *
 * @author iro19
 */
public class PantallaCancionesPlayListController implements Initializable {

    @FXML
    private Label lblPlaylist;
    @FXML
    private JFXButton btnDescargar;
    private StackPane panelPrincipal;
    private Listareproduccion lista;
    private ArrayList<Cancion> canciones;
    private ArrayList<String> rutasCanciones;
    private Properties recurso;
    private File archivoSeleccionado;
    @FXML
    private StackPane pnlCanciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = MainApp.leerConfig();
    }

    public void setPanelPrincipal(StackPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setLista(Listareproduccion lista) {
        this.lista = lista;
        lblPlaylist.setText(lista.getNombre());
        mostrarCanciones();
    }

    @FXML
    private void descargarPlayList(ActionEvent event) {
        if (canciones.size() > 0) {
            FileChooser explorador = new FileChooser();
            archivoSeleccionado = explorador.showSaveDialog(null);
            if (archivoSeleccionado != null) {
                String[] auxiliar = archivoSeleccionado.getName().split("\\.");
                if (auxiliar.length == 1) {
                    try {
                        archivoSeleccionado.mkdir();
                        rutasCanciones = new ArrayList();
                        for (Cancion cancion : canciones) {
                            rutasCanciones.add(cancion.getNombrearchivo());
                        }
                        Socket socket = new Socket(recurso.getProperty("ipAddress"), Integer.parseInt(recurso.getProperty("portFiles")));
                        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                        salida.writeObject(false);
                        salida.writeObject(rutasCanciones);
                        byte[] archivo = (byte[]) entrada.readObject();
                        ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(archivo));
                        ZipEntry entry = null;
                        String rutaFinal = archivoSeleccionado.getAbsolutePath();
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
                        socket.close();
                        descomprimirArchivo(rutaFinal, rutaFinal + "/" + entryName);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Las canciones se han descargado correctamente");
                        alert.showAndWait();
//                        salida.writeObject("RayPerez/" + txtAlbum.getText());
//                        salida.writeObject(zip);
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PantallaCancionesPlayListController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PantallaCancionesPlayListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Debes introducir el nombre de la carpeta donde se guardaran los archivos");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("No existe ninguna cancion en la playlist");
            alert.showAndWait();
        }
    }

    public void mostrarCanciones() {
        Client cliente = ClientBuilder.newClient();
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portDjango");
        WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/canciones/?id=" + lista.getIdlistareproduccion());
        canciones = (ArrayList<Cancion>) webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cancion>>() {
        });
        crearPantallaCanciones();        
    }

    public void crearPantallaCanciones() {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            PantallaCancionesController controlador = loader.getController();
            controlador.setCanciones(new ClienteCancion().findAll());
            controlador.ocultarBuscar();
            pnlCanciones.getChildren().clear();
            pnlCanciones.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaMiBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descomprimirArchivo(String ruta, String rutaZip) {
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
            Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (flujo != null) {
                try {
                    flujo.close();
                    File file = new File(rutaZip);
                    file.delete();
                } catch (IOException ex) {
                    Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
