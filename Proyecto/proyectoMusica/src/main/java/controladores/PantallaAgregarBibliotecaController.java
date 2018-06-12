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
import static java.lang.Thread.sleep;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import modelo.Album;
import modelo.Artista;
import modelo.Biblioteca;
import modelo.Cancion;
import modelo.Genero;
import modelo.Listareproduccion;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bibliotecaUsuario = null;
        clienteArtista = new ClienteArtista();
        clienteGenero = new ClienteGenero();
        nombresCanciones = new ArrayList();
        nombresArtistas = new ArrayList();
        nombresGeneros = new ArrayList();
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
        File archivoSeleccionado = explorador.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            try {
                ZipInputStream archivo = new ZipInputStream(new FileInputStream(archivoSeleccionado));
                ZipEntry entrada;
                while ((entrada = archivo.getNextEntry()) != null) {
                    nombresCanciones.add(entrada.getName());
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
        int idAlbum = albumes.size()+1;
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
        clienteAlbum.create(album);
        guardarCanciones(album,idAlbum);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Album creado exitosamente");
        alert.showAndWait();
    }

    public void guardarCanciones(Album album, int idAlbum) {
        album.setIdAlbum(idAlbum);
        Cancion cancion = new Cancion();
        cancion.setNombre("kkkk");
        cancion.setCalificacion(10);
        cancion.setNombreArchivo("kkkk.mp3");
        cancion.setAlbumidAlbum(album);
        ClienteCancion clienteCancion = new ClienteCancion();
        clienteCancion.create(cancion);
    }
}
