/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteAlbum;
import clientes.ClienteArtista;
import clientes.ClienteCancion;
import clientes.ClienteGenero;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaExplorarMusicaController implements Initializable {

    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab tbCanciones;
    @FXML
    private StackPane pnlCanciones;
    @FXML
    private Tab tbArtistas;
    @FXML
    private StackPane pnlArtistas;
    @FXML
    private Tab tbAlbum;
    @FXML
    private StackPane pnlAlbum;
    @FXML
    private Tab tbGenero;
    @FXML
    private StackPane pnlGenero;

    private StackPane pnlPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearPantallaCanciones();
        tbArtistas.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaArtistas();
            }
        });
        tbAlbum.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaAlbum();
            }
        });
        tbCanciones.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaCanciones();
            }
        });

        tbGenero.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
               crearPantallaGenero();
            }
        });
    }

    void setPnlPrincipal(StackPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }

    private void crearPantallaCanciones() {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaCancionesController controlador = loader.getController();
                controlador.setCanciones(new ClienteCancion().findAll());
                pnlCanciones.getChildren().clear();
                pnlCanciones.getChildren().add(root);
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

    private void crearPantallaArtistas() {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaArtistas.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaArtistasController controlador = loader.getController();
                controlador.setPnlPrincipal(pnlArtistas);
                controlador.setArtistas(new ClienteArtista().findAll());
                pnlArtistas.getChildren().clear();
                pnlArtistas.getChildren().add(root);
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

    private void crearPantallaGenero(){
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaGenero.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaGeneroController controlador = loader.getController();                
                controlador.setPnlPincipal(pnlGenero);
                controlador.setGeneros(new ClienteGenero().findAll());
                pnlGenero.getChildren().clear();
                pnlGenero.getChildren().add(root);
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
    
    private void crearPantallaAlbum() {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAlbum.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaAlbumController controlador = loader.getController();
                controlador.setPnlPincipal(pnlAlbum);
                controlador.setAlbumes(new ClienteAlbum().findAll());
                pnlAlbum.getChildren().clear();
                pnlAlbum.getChildren().add(root);
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
