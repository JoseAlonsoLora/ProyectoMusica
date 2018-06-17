/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.Album;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaAlbumController implements Initializable {

    @FXML
    private JFXTextField txtNombreCancion;
    @FXML
    private JFXButton btnBuscarCancion;
    @FXML
    private GridPane gridAlbumes;
    private List<Album> albumes;
    private StackPane pnlPincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
        mostrarAlbumes();
    }

    public void setPnlPincipal(StackPane pnlPincipal) {
        this.pnlPincipal = pnlPincipal;
    }

    @FXML
    private void buscarAlbum(ActionEvent event) {
    }

    public void mostrarAlbumes() {
        gridAlbumes.setVgap(20);
        int filas = albumes.size() / 3;
        int auxiliar = 0;
        if (albumes.size() % 3 != 0) {
            filas = ((albumes.size()) / 3) + 1;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {
                if (auxiliar < albumes.size()) {
                    FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/TarjetaAlbum.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) loader.load();
                        TarjetaAlbumController controlador = loader.getController();
                        controlador.setPnlPrincipal(pnlPincipal);
                        controlador.setAlbum(albumes.get(auxiliar));
                    } catch (IOException ex) {
                        Logger.getLogger(PantallaCancionesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gridAlbumes.add(root, j, i);
                    auxiliar++;
                }
            }
        }

    }
}
