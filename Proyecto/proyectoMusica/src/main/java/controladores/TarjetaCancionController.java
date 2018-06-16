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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import modelo.Cancion;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class TarjetaCancionController implements Initializable {
    
    private Cancion cancion;
    @FXML
    private Label lblNombreCancion;
    @FXML
    private Label lblArtista;
    @FXML
    private Label lblAlbum;
    @FXML
    private StackPane pnlTarjeta;
    @FXML
    private JFXButton bntMenuCancion;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlTarjeta.getStyleClass().add("pane");
        // TODO
    }    

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
        lblNombreCancion.setText(this.cancion.getNombre());
        lblArtista.setText(this.cancion.getNombreArtista());
        lblAlbum.setText(this.cancion.getNombreAlbum());
    }

    @FXML
    private void mostrarMenu(ActionEvent event) {
        PopOver pop = new PopOver();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(
                TarjetaCancionController.class.getResource("/fxml/TarjetaMenuCancion.fxml"));
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TarjetaCancionController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        TarjetaMenuCancionController controlador = loader.getController();
        controlador.setCancion(cancion);
        pop.setContentNode(root);
        pop.show(bntMenuCancion);
    }


    
    
}
