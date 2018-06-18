/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.Genero;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class PantallaGeneroController implements Initializable {

    @FXML
    private GridPane gridGeneros;
    private StackPane pnlPincipal;
    private List<Genero> generos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setPnlPincipal(StackPane pnlPincipal) {
        this.pnlPincipal = pnlPincipal;
    }
    
    public void mostrarGeneros(){
        gridGeneros.setVgap(20);
        gridGeneros.setHgap(20);
        int filas = generos.size() / 3;
        int auxiliar = 0;
        if (generos.size() % 3 != 0) {
            filas = ((generos.size()) / 3) + 1;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {
                if (auxiliar < generos.size()) {
                    FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/TarjetaGenero.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) loader.load();
                        TarjetaGeneroController controlador = loader.getController();
                        controlador.setPnlPincipal(pnlPincipal);
                        controlador.setGenero(generos.get(auxiliar));
                    } catch (IOException ex) {
                        Logger.getLogger(PantallaCancionesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gridGeneros.add(root, j, i);
                    auxiliar++;
                }
            }
        }
        
        
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
        mostrarGeneros();
    }        
}
