/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaMiBibliotecaController implements Initializable {

    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab tbCanciones;
    @FXML
    private Tab tbArtistas;
    @FXML
    private Tab tbAlbum;
    @FXML
    private Tab tbGenero;
    @FXML
    private StackPane pnlCanciones;
    @FXML
    private StackPane pnlArtistas;
    @FXML
    private StackPane pnlAlbum;
    @FXML
    private StackPane pnlGenero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearPantallaCanciones();
        tbAlbum.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                System.out.println("Tab Album");
            }
        });
        tbCanciones.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaCanciones();
            }
        });
    }

    private void crearPantallaCanciones() {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            pnlCanciones.getChildren().clear();
            pnlCanciones.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PantallaMiBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
