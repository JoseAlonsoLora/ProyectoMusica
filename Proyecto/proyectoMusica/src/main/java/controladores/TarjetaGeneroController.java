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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modelo.Genero;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class TarjetaGeneroController implements Initializable {

    private Genero genero;
    @FXML
    private StackPane pnlGenero;
    @FXML
    private Label lblNombreGenero;
    private StackPane pnlPincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlGenero.getStyleClass().add("pane");
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
        lblNombreGenero.setText(genero.getNombre());
    }

    @FXML
    private void mostrarAlbumesGenero(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAlbum.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaAlbumController controlador = loader.getController();
                controlador.setPnlPincipal(pnlPincipal);
                controlador.setAlbumes(new ClienteAlbum().obtenerAlbumesGenero(genero.getIdgenero()));
                pnlPincipal.getChildren().clear();
                pnlPincipal.getChildren().add(root);
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

    public void setPnlPincipal(StackPane pnlPincipal) {
        this.pnlPincipal = pnlPincipal;
    }
}
