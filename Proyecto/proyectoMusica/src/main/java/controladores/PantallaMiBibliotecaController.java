/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteAlbum;
import clientes.ClienteBiblioteca;
import clientes.ClienteCancion;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import modelo.Biblioteca;

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
    private Tab tbAlbum;
    @FXML
    private StackPane pnlCanciones;
    @FXML
    private StackPane pnlAlbum;
    private StackPane pnlPrincipal;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearPantallaCanciones();
        tbAlbum.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaAlbumes();
            }
        });
        tbCanciones.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                crearPantallaCanciones();
            }
        });
    }

    public void setPnlPrincipal(StackPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }

    private void crearPantallaCanciones() {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCanciones.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaCancionesController controlador = loader.getController();
                controlador.setCanciones(new ClienteCancion().obtenerCancionesBiblioteca());
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

    public void crearPantallaAlbumes() {
        try {
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAlbum.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                PantallaAlbumController controlador = loader.getController();
                controlador.setPnlPincipal(pnlAlbum);
                ClienteBiblioteca clienteBiblioteca = new ClienteBiblioteca();
                Biblioteca bibliotecaUsuario = null;
                List<Biblioteca> bibliotecas = clienteBiblioteca.findAll();
                for (Biblioteca biblioteca : bibliotecas) {
                    if (biblioteca.getUsuario_nombreusuario().equals(PantallaPrincipalController.nombreUsuario)) {
                        bibliotecaUsuario = biblioteca;
                    }
                }
                controlador.setAlbumes(new ClienteAlbum().obtenerAlbumesBiblioteca(bibliotecaUsuario.getIdbiblioteca()));
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

    @FXML
    private void crearNuevoAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAgregarBiblioteca.fxml"));
        Parent root = (Parent) loader.load();
        PantallaAgregarBibliotecaController pantallaAgregarAlbum = loader.getController();
        pantallaAgregarAlbum.setPanelPrincipal(pnlPrincipal);
        pnlPrincipal.getChildren().clear();
        pnlPrincipal.getChildren().add(root);
    }

}
