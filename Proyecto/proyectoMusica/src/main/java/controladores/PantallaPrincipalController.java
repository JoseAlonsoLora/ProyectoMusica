/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
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
    private static String nombreUsuario;
    private static List<Cancion> historial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colaCanciones = new ArrayList<>();
        historial = new ArrayList<>();
        pnlCancion.getStyleClass().add("panel");
        try {
            crearPantallaMiBiblioteca();
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaReproducirCancion.fxml"));
            Parent root = (Parent) loader.load();
            pnlCancion.getChildren().clear();
            pnlCancion.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNombreUsuario(String nombreUsuario) {
        PantallaPrincipalController.nombreUsuario = nombreUsuario;
        lblNombreUsuario.setText(nombreUsuario);
    }

    @FXML
    private void desplegarPantallaPlayList(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaListasReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        PantallaListasReproduccionController pantallaPlayList = loader.getController();
        pantallaPlayList.setPanelPrincipal(pnlPrincipal);
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarPantallaHistorial(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaHistorial.fxml"));
        Parent root = (Parent) loader.load();
        PantallaHistorialController pantallaHistorial = loader.getController();
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarPantallaAgregarBiblioteca(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAgregarBiblioteca.fxml"));
        Parent root = (Parent) loader.load();
        PantallaAgregarBibliotecaController pantallaAgregarAlbum = loader.getController();
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarMiBiblioteca(ActionEvent event) {
        crearPantallaMiBiblioteca();
    }

    private void crearPantallaMiBiblioteca() {
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
    
    public static void agregarHistorial(Cancion cancion){
        historial.add(cancion);
    }
    
    public static List<Cancion> historial(){
        return historial;
    }

}
