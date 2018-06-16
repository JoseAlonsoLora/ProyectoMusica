/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteListaReproduccion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import modelo.Cancion;
import modelo.Listareproduccion;

/**
 * FXML Controller class
 *
 * @author raymundo
 */
public class PantallaAgregarCancionListaController implements Initializable {

    @FXML
    private JFXListView<Listareproduccion> listPlayList;
    @FXML
    private JFXButton btnAceptar;
    private List<Listareproduccion> listasReproduccionUsuario;
    private Cancion cancion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listPlayList.setDepth(1);
        listPlayList.setExpanded(true);
        mostrarListas();
    }    

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    @FXML
    private void agregarCancionPlayList(ActionEvent event) {
        if(listPlayList.getSelectionModel().getSelectedItem() != null){
            
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una lista de reproducción");
            alert.showAndWait();
        }
    }
    
    public void mostrarListas() {
        ClienteListaReproduccion clienteListaReproduccion = new ClienteListaReproduccion();
        listasReproduccionUsuario = new ArrayList();
        List<Listareproduccion> listas = clienteListaReproduccion.findAll();
        for (Listareproduccion lista : listas) {
            if (lista.getUsuario_nombreusuario().equals("RayPerez")) {
                listasReproduccionUsuario.add(lista);
            }
        }
        ObservableList<Listareproduccion> items = FXCollections.observableArrayList();
        items.addAll(listasReproduccionUsuario);
        listPlayList.setItems(items);
    }
    
}
