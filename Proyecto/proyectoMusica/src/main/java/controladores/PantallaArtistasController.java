/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteArtista;
import clientes.ClienteCancion;
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
import modelo.Artista;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaArtistasController implements Initializable {

    @FXML
    private JFXTextField txtNombreCancion;
    @FXML
    private JFXButton btnBuscarCancion;
    @FXML
    private GridPane gridCanciones;

    private List<Artista> artistas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerArtistas();
    }    
    
        public void obtenerArtistas() {
        ClienteArtista clienteArtista = new ClienteArtista();
        artistas = clienteArtista.findAll();
        gridCanciones.setVgap(20);
        int filas = artistas.size() / 3;
        int auxiliar = 0;
        if (artistas.size() % 3 != 0) {
            filas = ((artistas.size()) / 3) + 1;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {
                if (auxiliar < artistas.size()) {
                    FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/TarjetaArtista.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) loader.load();
                        TarjetaArtistaController controlador = loader.getController();
                        controlador.setArtista(artistas.get(auxiliar));
                    } catch (IOException ex) {
                        Logger.getLogger(PantallaCancionesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gridCanciones.add(root, j, i);
                    auxiliar++;
                }
            }
        }

    }

    @FXML
    private void buscarArtista(ActionEvent event) {
    }
    
}
