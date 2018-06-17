/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteCancion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author iro19
 */
public class PantallaHistorialController implements Initializable {

    @FXML
    private StackPane pnlCancion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            PantallaCancionesController controlador = loader.getController();
            controlador.setCanciones(PantallaPrincipalController.historial());
            controlador.ocultarBuscar();
            pnlCancion.getChildren().clear();
            pnlCancion.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaMiBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
