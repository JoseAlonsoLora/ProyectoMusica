/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import modelo.Cancion;

/**
 * FXML Controller class
 *
 * @author raymundo
 */
public class TarjetaMenuCancionController implements Initializable {

    @FXML
    private JFXButton btnAgregarCola;
    @FXML
    private JFXButton btnAgregarLista;
    private Cancion cancion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void agregarCola(ActionEvent event) {
        PantallaPrincipalController.agregarCola(cancion);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Canción agregada a la cola");
        alert.showAndWait();
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    @FXML
    private void agregarLista(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAgregarCancionLista.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            PantallaAgregarCancionListaController controlador = loader.getController();
            controlador.setCancion(cancion);
        } catch (IOException ex) {
            Logger.getLogger(PantallaCancionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
