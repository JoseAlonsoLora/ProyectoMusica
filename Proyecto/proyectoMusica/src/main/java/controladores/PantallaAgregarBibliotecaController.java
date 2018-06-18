package controladores;

import clientes.ClienteArtista;
import clientes.ClienteBiblioteca;
import clientes.ClienteGenero;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.proyectomusica.MainApp;
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
import java.util.Properties;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    private Properties recurso;
    private StackPane panelPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bibliotecaUsuario = null;
        clienteArtista = new ClienteArtista();
        clienteGenero = new ClienteGenero();
        nombresCanciones = new ArrayList();
        nombresArtistas = new ArrayList();
        nombresGeneros = new ArrayList();
        archivoSeleccionado = null;
        recurso = MainApp.leerConfig();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
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
            clienteArtista.close();
        } catch (Exception e) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }
    }
    
    public void setPanelPrincipal(StackPane panelPrincipal){
        this.panelPrincipal = panelPrincipal;
    }

    @FXML
    private void seleccionarArchivo() {
        boolean archivoValido = true;
        FileChooser explorador = new FileChooser();
        archivoSeleccionado = explorador.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            String nombre = archivoSeleccionado.getName();
            String[] auxiliar = nombre.split("\\.");
            if (auxiliar.length < 2 || !auxiliar[1].equals("zip")) {
                archivoValido = false;
            }
            if (archivoValido) {
                lstCanciones.setItems(null);
                nombresCanciones = new ArrayList();
                try {
                    ZipInputStream archivo = new ZipInputStream(new FileInputStream(archivoSeleccionado));
                    ZipEntry entrada;

                    while ((entrada = archivo.getNextEntry()) != null) {
                        if (!entrada.isDirectory()) {
                            String[] auxiliarArchivo = entrada.getName().split("\\.");
                            boolean caracterInvalido = entrada.getName().contains("-") || entrada.getName().contains("*");
                            if (auxiliarArchivo.length < 2 || !auxiliarArchivo[1].equals("mp3") || caracterInvalido) {
                                lstCanciones.setItems(null);
                                nombresCanciones = new ArrayList();
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Información");
                                alert.setHeaderText(null);
                                alert.setContentText("El zip contiene algunos archivos que no cumplen con el formato .mp3 \n o algunas canciones contienen  *  o  -  en el nombre ");
                                alert.showAndWait();
                                break;
                            } else {
                                nombresCanciones.add(entrada.getName());
                            }
                        } else {
                            lstCanciones.setItems(null);
                            nombresCanciones = new ArrayList();
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Información");
                            alert.setHeaderText(null);
                            alert.setContentText("El zip no debe de contener directorios, únicamente archivos .mp3");
                            alert.showAndWait();
                            break;
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
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Archivo no compatible");
                alert.setHeaderText(null);
                alert.setContentText("El archivo debe tener una extención .zip");
                alert.showAndWait();
            }
        } else {
            lstCanciones.setItems(null);
            nombresCanciones = new ArrayList();
            ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll(nombresCanciones);
            lstCanciones.setItems(items);
        }
    }

    @FXML
    private void agregarArtista(ActionEvent event) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nuevo artista");
            dialog.setHeaderText("Nuevo artista");
            dialog.setContentText("Nombre del artista:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Artista artista = new Artista();
                artista.setNombre(result.get());
                clienteArtista = new ClienteArtista();
                clienteArtista.create(artista);
                clienteArtista.close();
                clienteArtista = new ClienteArtista();
                nombresArtistas = new ArrayList();
                artistas = clienteArtista.findAll();
                for (Artista artistaAux : artistas) {
                    nombresArtistas.add(artistaAux.getNombre());
                }
                ObservableList<String> itemsArtistas = FXCollections.observableArrayList();
                itemsArtistas.addAll(nombresArtistas);
                cmbArtistas.setItems(itemsArtistas);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Artista creado exitosamente");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void guardarAlbum(ActionEvent event) throws IOException {
        try {
            if (!verificarCamposVacios()) {
                if (!verificarArchivoSubido()) {
                    if (!verificarLongitudCampos()) {
                        ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
                        List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
                        for (Biblioteca biblioteca : bibliotecas) {
                            if (biblioteca.getUsuario_nombreusuario().equals(PantallaPrincipalController.nombreUsuario)) {
                                bibliotecaUsuario = biblioteca;
                            }
                        }

                        JSONObject albumJSON = new JSONObject();
                        albumJSON.put("nombre", txtAlbum.getText());
                        albumJSON.put("anoLanzamiento", txtAnio.getText());
                        albumJSON.put("compania", txtCompania.getText());
                        albumJSON.put("idArtista", artistas.get(cmbArtistas.getSelectionModel()
                                .getSelectedIndex()).getIdartista());
                        System.out.println("id artista: " + artistas.get(cmbArtistas.getSelectionModel()
                                .getSelectedIndex()).getIdartista());
                        albumJSON.put("idGenero", generos.get(cmbGeneros.getSelectionModel()
                                .getSelectedIndex()).getIdgenero());
                        albumJSON.put("idBiblioteca", bibliotecaUsuario.getIdbiblioteca());
                        JSONArray listaCanciones = new JSONArray();
                        for (String nombreCancion : nombresCanciones) {
                            JSONObject cancion = new JSONObject();
                            cancion.put("nombre", nombreCancion.replace(".mp3", ""));
                            cancion.put("calificacion", 10);
                            cancion.put("nombrearchivo", PantallaPrincipalController.nombreUsuario + "/" + txtAlbum.getText() + "/" + nombreCancion);
                            listaCanciones.put(cancion);
                        }
                        albumJSON.put("listaCanciones", listaCanciones);
                        String ip = recurso.getProperty("ipAddress");
                        String puerto = recurso.getProperty("portDjango");
                        File archivoCanciones = new File(archivoSeleccionado.getAbsolutePath());
                        if (archivoCanciones.exists()) {
                            Client cliente = ClientBuilder.newClient();

                            WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/crearAlbum/");
                            webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(albumJSON.toMap(), javax.ws.rs.core.MediaType.APPLICATION_JSON));
                        }

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
                        puerto = recurso.getProperty("portFiles");
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(PantallaAgregarBibliotecaController.class.getResource("/fxml/BarraProgreso.fxml"));
                        Parent root = loader.load();
                        BarraProgresoController barra = loader.getController();
                        barra.setTipo("Cargando canciones");
                        barra.setStageActual(stage);
                        barra.setPnlPrincipal(panelPrincipal);
                        barra.setZip(zip);
                        barra.setPuerto(Integer.parseInt(puerto));
                        barra.setIp(ip);
                        barra.setRuta(PantallaPrincipalController.nombreUsuario + "/" + txtAlbum.getText());
                        Scene scene = new Scene(root);
                        stage.setTitle("Trabajando");
                        stage.setScene(scene);
                        stage.show();
                        barra.setTipoCarga(true);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Algunos campos sobrepasan el límite de caracteres");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Debe seleccionar un archivo valido de canciones");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Algunos campos estan vacios");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }
    }

    public boolean verificarCamposVacios() {
        boolean camposVacios = false;
        if (txtAlbum.getText().trim().isEmpty() || txtAnio.getText().trim().isEmpty()
                || txtCompania.getText().trim().isEmpty() || cmbArtistas.getSelectionModel().isEmpty()
                || cmbGeneros.getSelectionModel().isEmpty()) {
            camposVacios = true;
        }
        return camposVacios;
    }

    public boolean verificarArchivoSubido() {
        boolean archivoVacio = true;
        if (lstCanciones.getItems().size() > 0) {
            archivoVacio = false;
        }
        return archivoVacio;
    }

    public boolean verificarLongitudCampos() {
        boolean longitudExcedida = false;
        if (txtAlbum.getText().trim().length() > 20 || txtAnio.getText().trim().length() > 4
                || txtCompania.getText().trim().length() > 20) {
            System.out.println(txtAlbum.getText().trim().length());
            longitudExcedida = true;
        }
        return longitudExcedida;
    }

    @FXML
    private void limitarAlbum(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, txtAlbum, 20);
        if (!Character.isLetterOrDigit(caracter) && !Character.isSpaceChar(caracter)) {
            event.consume();
        }
    }

    @FXML
    private void limitarAnio(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, txtAnio, 4);
        if (!Character.isDigit(caracter)) {
            event.consume();
        }
    }

    @FXML
    private void limitarCompania(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, txtCompania, 20);
        if (!Character.isLetterOrDigit(caracter) && !Character.isSpaceChar(caracter)) {
            event.consume();
        }
    }

    public void limitarCaracteres(KeyEvent event, JFXTextField campo, int caracteresMaximos) {
        if (campo.getText().trim().length() >= caracteresMaximos) {
            event.consume();
        }
    }

}
