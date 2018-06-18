/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteAlbum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import modelo.Artista;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class TarjetaArtistaController implements Initializable {

    private Artista artista;
    @FXML
    private StackPane pnlTarjeta;
    @FXML
    private Label lblNombreArtista;
    private StackPane pnlPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlTarjeta.getStyleClass().add("pane");
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
        lblNombreArtista.setText(artista.getNombre());
    }

    public void setPnlPrincipal(StackPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }

    @FXML
    private void abrirAlbumes(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAlbum.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaAlbumController controlador = loader.getController();
                controlador.setPnlPincipal(pnlPrincipal);
                controlador.setAlbumes(new ClienteAlbum().obtenerAlbumesArtista(artista.getIdartista()));
                pnlPrincipal.getChildren().clear();
                pnlPrincipal.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(PantallaMiBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            Alert alertUsuarioInvalido = new Alert(Alert.AlertType.ERROR);
            alertUsuarioInvalido.setTitle("Error");
            alertUsuarioInvalido.setHeaderText(null);
            alertUsuarioInvalido.setContentText("No hay conexión con el servidor");
            alertUsuarioInvalido.showAndWait();
        }
    }

}
