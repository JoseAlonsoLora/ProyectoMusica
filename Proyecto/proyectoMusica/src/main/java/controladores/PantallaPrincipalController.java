/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void desplegarPantallaPlayList(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCancionesPlayList.fxml"));
        Parent root = (Parent) loader.load();
        PantallaCancionesPlayListController pantallaPlayList = loader.getController();
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
}
