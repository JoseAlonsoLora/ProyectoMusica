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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaReproducirCancionController implements Initializable {

    @FXML
    private ImageView imgAlbum;
    @FXML
    private Label lblNombreCancion;
    @FXML
    private Label lblNombreArtista;
    @FXML
    private Pane pnlReproducir;
    public static Pane panelCompartido;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelCompartido = new Pane();
        pnlReproducir.getChildren().add(panelCompartido);
        lblNombreArtista.setText("");
        lblNombreCancion.setText("");
    }
    public void mostrarInformacion(String nombreArtista, String nombreCancion){
        lblNombreArtista.setText(nombreArtista);
        lblNombreCancion.setText(nombreCancion);
    }

    
}
