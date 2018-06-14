package controladores;

import clientes.ClienteArtista;
import clientes.ClienteBiblioteca;
import clientes.ClienteGenero;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.zip.ZipOutputStream;
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
import modelo.Artista;
import modelo.Biblioteca;
import modelo.Genero;
import org.json.JSONArray;
import org.json.JSONObject;

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
        clienteGenero.close();
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
    private void guardarAlbum(ActionEvent event) throws IOException {
        ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
        List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
        for (Biblioteca biblioteca : bibliotecas) {
            if (biblioteca.getUsuario_nombreusuario().getNombreUsuario().equals("RayPerez")) {
                bibliotecaUsuario = biblioteca;
            }
        }

        JSONObject albumJSON = new JSONObject();
        albumJSON.put("nombre", txtAlbum.getText());
        albumJSON.put("anoLanzamiento", txtAnio.getText());
        albumJSON.put("compania", txtCompania.getText());
        albumJSON.put("idArtista", artistas.get(cmbArtistas.getSelectionModel()
                .getSelectedIndex()).getIdartista());
        albumJSON.put("idGenero", generos.get(cmbGeneros.getSelectionModel()
                .getSelectedIndex()).getIdgenero());
        albumJSON.put("idBiblioteca", bibliotecaUsuario.getIdBiblioteca());
        JSONArray listaCanciones = new JSONArray();
        for (String nombreCancion : nombresCanciones) {
            JSONObject cancion = new JSONObject();
            cancion.put("nombre", nombreCancion);
            cancion.put("calificacion", 10);
            cancion.put("nombrearchivo", nombreCancion);
            listaCanciones.put(cancion);
        }
        albumJSON.put("listaCanciones", listaCanciones);

        File archivoCanciones = new File(archivoSeleccionado.getAbsolutePath());
        if (archivoCanciones.exists()) {
            System.out.println("Existe");
            Client cliente = ClientBuilder.newClient();
            System.out.println(albumJSON.toString());
            WebTarget webTarget = cliente.target("http://localhost:9000/crearAlbum/");
            webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(albumJSON.toMap(), javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

//        zipOutputStream.putNextEntry(new ZipEntry(archivoCanciones.getName()));
//        FileInputStream fileInputStream = new FileInputStream(archivoCanciones);
//        byte[] buffer = new byte[1024];
//        int byteRead;
//        while ((byteRead = fileInputStream.read(buffer)) > 0) {
//            zipOutputStream.write(buffer, 0, byteRead);
//        }
//        fileInputStream.close();
//        if (zipOutputStream != null) {
//            zipOutputStream.finish();
//            zipOutputStream.flush();
//        }
//        byte[] zip = byteArrayOutputStream.toByteArray();+
        FileInputStream ficheroStream = new FileInputStream(archivoCanciones);
        byte contenido[] = new byte[(int) archivoCanciones.length()];
        System.out.println(archivoCanciones.length());
        ficheroStream.read(contenido);

        JSONObject archivoZip = new JSONObject();
        archivoZip.put("bytes", contenido);
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target("http://localhost:9000/subirArchivo/");
        webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(archivoZip.toMap(), javax.ws.rs.core.MediaType.APPLICATION_JSON));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Album creado exitosamente");
        alert.showAndWait();
    }
}
