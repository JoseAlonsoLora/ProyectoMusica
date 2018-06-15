/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.Cancion;

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
    
    
}
