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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import modelo.Album;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class TarjetaAlbumController implements Initializable {

    @FXML
    private ImageView imgAlbum;
    @FXML
    private Label lblNombreAlbum;
    private Album album;
    @FXML
    private StackPane pnlAlbum;
    private StackPane pnlPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlAlbum.getStyleClass().add("pane");
    }

    public void setAlbum(Album album) {
        this.album = album;
        lblNombreAlbum.setText(album.getNombre());
    }

    public void setPnlPrincipal(StackPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }    

    @FXML
    private void abrirCanciones(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            PantallaCancionesController controlador = loader.getController();
            controlador.setCanciones(new ClienteCancion().obtenerCancionesAlbum(album.getIdalbum()));
            pnlPrincipal.getChildren().clear();
            pnlPrincipal.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaMiBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
