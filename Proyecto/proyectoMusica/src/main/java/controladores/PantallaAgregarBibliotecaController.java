package controladores;

import clientes.ClienteAlbum;
import clientes.ClienteArtista;
import clientes.ClienteBiblioteca;
import clientes.ClienteCancion;
import clientes.ClienteGenero;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import modelo.Album;
import modelo.AlbumPOST;
import modelo.Artista;
import modelo.Biblioteca;
import modelo.Cancion;
import modelo.Genero;

/**
 * FXML Controller class
 *
 * @author T-REX
 */
public class PantallaAgregarBibliotecaController implements Initializable {

    @FXML
    private ListView<String> lstCanciones;
    private ArrayList<String> nombresCanciones;
    private ArrayList<String> nombresArtistas;
    private ArrayList<String> nombresGeneros;
    private List<Artista> artistas;
    private List<Genero> generos;
    @FXML
    private JFXTextField txtAnio;
    @FXML
    private JFXTextField txtCompania;
    private ClienteArtista clienteArtista;
    private ClienteGenero clienteGenero;
    @FXML
    private JFXComboBox<String> cmbArtistas;
    @FXML
    private JFXComboBox<String> cmbGeneros;
    @FXML
    private JFXTextField txtAlbum;
    private Biblioteca bibliotecaUsuario;
    private File archivoSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bibliotecaUsuario = null;
        clienteArtista = new ClienteArtista();
        clienteGenero = new ClienteGenero();
        nombresCanciones = new ArrayList();
        nombresArtistas = new ArrayList();
        nombresGeneros = new ArrayList();
        archivoSeleccionado = null;
        cargarDatos();
    }

    public void cargarDatos() {
        artistas = clienteArtista.findAll();
        generos = clienteGenero.findAll();
        for (Artista artista : artistas) {
            nombresArtistas.add(artista.getNombre());
        }
        for (Genero genero : generos) {
            nombresGeneros.add(genero.getNombre());
        }
        ObservableList<String> itemsArtistas = FXCollections.observableArrayList();
        itemsArtistas.addAll(nombresArtistas);
        cmbArtistas.setItems(itemsArtistas);
        ObservableList<String> itemsGeneros = FXCollections.observableArrayList();
        itemsGeneros.addAll(nombresGeneros);
        cmbGeneros.setItems(itemsGeneros);
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser explorador = new FileChooser();
        archivoSeleccionado = explorador.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            try {
                ZipInputStream archivo = new ZipInputStream(new FileInputStream(archivoSeleccionado));
                ZipEntry entrada;

                while ((entrada = archivo.getNextEntry()) != null) {
                    if (!entrada.isDirectory()) {
                        nombresCanciones.add(entrada.getName());
                    }
                }
                ObservableList<String> items = FXCollections.observableArrayList();
                items.addAll(nombresCanciones);
                lstCanciones.setItems(items);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void agregarArtista(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo artista");
        dialog.setHeaderText("Nuevo artista");
        dialog.setContentText("Nombre del artista:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Artista artista = new Artista();
            artista.setNombre(result.get());
            clienteArtista.create(artista);
            artistas.add(artista);
            nombresArtistas.add(artista.getNombre());
            ObservableList<String> itemsArtistas = FXCollections.observableArrayList();
            itemsArtistas.addAll(nombresArtistas);
            cmbArtistas.setItems(itemsArtistas);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Artista creado exitosamente");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void guardarAlbum(ActionEvent event) {
        ClienteAlbum clienteAlbum = new ClienteAlbum();
        List<Album> albumes = clienteAlbum.findAll();
        int idAlbum = albumes.size() + 1;
        ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
        List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
        for (Biblioteca biblioteca : bibliotecas) {
            if (biblioteca.getUsuarionombreUsuario().getNombreUsuario().equals("RayPerez")) {
                bibliotecaUsuario = biblioteca;
            }
        }
        Album album = new Album();
        album.setNombre(txtAlbum.getText());
        album.setArtistaidArtista(artistas.get(cmbArtistas.getSelectionModel().getSelectedIndex()));
        album.setGeneroidGenero(generos.get(cmbGeneros.getSelectionModel().getSelectedIndex()));
        album.setBibliotecaidBiblioteca(bibliotecaUsuario);
        AlbumPOST albumPOST = new AlbumPOST();
        albumPOST.setAlbum(album);
        File archivoCanciones = new File(archivoSeleccionado.getAbsolutePath());
        if (archivoCanciones.exists()) {
            System.out.println("Existe");
            albumPOST.setFile(archivoCanciones);
            Client cliente = ClientBuilder.newClient();
            WebTarget webTarget = cliente.target("http://localhost:8080/proyectoMusicaServidor/webresources/modelo.album");
            webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(albumPOST, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        guardarCanciones(idAlbum);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Album creado exitosamente");
        alert.showAndWait();
    }

    public void guardarCanciones(int idAlbum) {
        ClienteCancion clienteCancion = new ClienteCancion();
        ClienteAlbum clienteAlbum = new ClienteAlbum();
        Album album = clienteAlbum.find(String.valueOf(idAlbum));
        System.out.println(album.getIdAlbum()+"..................................................");
        for (String nombreCancion : nombresCanciones) {
            Cancion cancion = new Cancion();
            cancion.setNombre(nombreCancion);
            cancion.setCalificacion(10);
            cancion.setNombreArchivo(nombreCancion);
            cancion.setAlbumidAlbum(album);
            clienteCancion.create(cancion);
        }

    }
}
