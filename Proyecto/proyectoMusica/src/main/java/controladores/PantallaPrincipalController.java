/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clasesApoyo.MediaControl;
import clientes.ClienteBiblioteca;
import clientes.ClienteCancion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import static com.sun.javafx.PlatformUtil.isWindows;
import static controladores.TarjetaCancionController.reproducirCancion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import modelo.Biblioteca;
import modelo.Cancion;

/**
 * FXML Controller class
 *
 * @author T-REX
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private MenuButton btnConfiguracion;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private JFXButton btnMiBiblioteca;
    @FXML
    private JFXButton btnMusica;
    @FXML
    private JFXButton btnPlayList;
    @FXML
    private StackPane pnlPrincipal;
    @FXML
    private StackPane pnlCancion;

    private static List<Cancion> colaCanciones;
    public static String nombreUsuario;
    private static List<Cancion> historial;
    public static MediaControl mc;
    private static int indiceCola;
    private static int indiceRadio;
    public static PantallaReproducirCancionController controlador;
    public static List<Cancion> radio;
    private Biblioteca bibliotecaUsuario;
    @FXML
    private JFXToggleButton btnPublica;
    @FXML
    private JFXButton btnAgregarAlbum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        indiceCola = 0;
        indiceRadio = 0;
        radio = new ArrayList<>();
        colaCanciones = new ArrayList<>();
        historial = new ArrayList<>();
        pnlCancion.getStyleClass().add("panel");
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaReproducirCancion.fxml"));
            Parent root = (Parent) loader.load();
            controlador = loader.getController();
            pnlCancion.getChildren().clear();
            pnlCancion.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setNombreUsuario(String nombreUsuario) {
        PantallaPrincipalController.nombreUsuario = nombreUsuario;
        lblNombreUsuario.setText(nombreUsuario);
        crearPantallaMiBiblioteca();
        buscarBiblioteca();
    }

    public void buscarBiblioteca() {
        try {
            ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
            List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
            for (Biblioteca biblioteca : bibliotecas) {
                if (biblioteca.getUsuario_nombreusuario().equals(PantallaPrincipalController.nombreUsuario)) {
                    bibliotecaUsuario = biblioteca;
                }
            }

            if (bibliotecaUsuario.getPublica() == 1) {
                btnPublica.setSelected(true);
            }
        } catch (Exception ex) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }
    }

    @FXML
    private void desplegarPantallaPlayList(javafx.event.ActionEvent event) throws IOException {
        btnPublica.setVisible(false);
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaListasReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        PantallaListasReproduccionController pantallaPlayList = loader.getController();
        pantallaPlayList.setPanelPrincipal(pnlPrincipal);
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarPantallaHistorial(javafx.event.ActionEvent event) throws IOException {
        btnPublica.setVisible(false);
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaHistorial.fxml"));
        Parent root = (Parent) loader.load();
        PantallaHistorialController pantallaHistorial = loader.getController();
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarPantallaAgregarBiblioteca(javafx.event.ActionEvent event) throws IOException {
        btnPublica.setVisible(false);
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAgregarBiblioteca.fxml"));
        Parent root = (Parent) loader.load();
        PantallaAgregarBibliotecaController pantallaAgregarAlbum = loader.getController();
        pantallaAgregarAlbum.setPanelPrincipal(pnlPrincipal);
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarMiBiblioteca(ActionEvent event) {
        crearPantallaMiBiblioteca();
    }

    private void crearPantallaMiBiblioteca() {
        btnPublica.setVisible(true);
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaMiBiblioteca.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            PantallaMiBibliotecaController pantallaMiBiblioteca = loader.getController();
            pantallaMiBiblioteca.setPnlPrincipal(pnlPrincipal);
            pnlPrincipal.getChildren().clear();
            pnlPrincipal.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deplegarExplorarMusica(ActionEvent event) {
        btnPublica.setVisible(false);
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaExplorarMusica.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            PantallaExplorarMusicaController pantallaMiBiblioteca = loader.getController();
            pantallaMiBiblioteca.setPnlPrincipal(pnlPrincipal);
            pnlPrincipal.getChildren().clear();
            pnlPrincipal.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void agregarCola(Cancion cancion) {
        colaCanciones.add(cancion);
    }

    public static void agregarHistorial(Cancion cancion) {
        historial.add(cancion);
    }

    public static List<Cancion> historial() {
        return historial;
    }

    public static void reproducirCancionCola() {
        if (!colaCanciones.isEmpty() && indiceCola != colaCanciones.size()) {
            PantallaPrincipalController.agregarHistorial(colaCanciones.get(indiceCola));
            String rutaCancion = colaCanciones.get(indiceCola).getNombrearchivo().replace("/", "-");
            String rutaFinal;
            if (isWindows()) {
                rutaFinal = rutaCancion.replace(" ", "*");
            } else {
                rutaFinal = rutaCancion.replace("\\s", "*");
            }
            reproducirCancion(rutaFinal);
            PantallaPrincipalController.controlador.mostrarInformacion(colaCanciones.get(indiceCola).getNombreArtista(),
                    colaCanciones.get(indiceCola).getNombre());
            indiceCola++;
        } else {
            if (!radio.isEmpty() && indiceRadio != radio.size()) {
                PantallaPrincipalController.agregarHistorial(radio.get(indiceRadio));
                String rutaCancion = radio.get(indiceRadio).getNombrearchivo().replace("/", "-");
                String rutaFinal;
                if (isWindows()) {
                    rutaFinal = rutaCancion.replace(" ", "*");
                } else {
                    rutaFinal = rutaCancion.replace("\\s", "*");
                }
                reproducirCancion(rutaFinal);
                PantallaPrincipalController.controlador.mostrarInformacion(radio.get(indiceRadio).getNombreArtista(),
                        radio.get(indiceRadio).getNombre());
                indiceRadio++;
            }
        }
    }

    public static void buscarCancionesGenero(int idAlbum) {
        radio.clear();
        radio.addAll(new ClienteCancion().obtenerCancionesGenero(idAlbum));
        indiceRadio = 0;
    }

    @FXML
    private void cambiarEstado(ActionEvent event) {
        try {
            if (btnPublica.isSelected()) {
                new ClienteBiblioteca().cambiarEstadoPublica(bibliotecaUsuario.getIdbiblioteca());
            } else {
                new ClienteBiblioteca().cambiarEstadoPrivada(bibliotecaUsuario.getIdbiblioteca());
            }

            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.INFORMATION);
            alertUsuarioInvalido.setTitle("Información");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("Estado cambiado");
            alertUsuarioInvalido.showAndWait();
            
        } catch (Exception ex) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }

    }
}
