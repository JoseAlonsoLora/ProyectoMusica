/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import modelo.Cancion;
import modelo.Listareproduccion;

/**
 * FXML Controller class
 *
 * @author iro19
 */
public class PantallaCancionesPlayListController implements Initializable {

    @FXML
    private Label lblPlaylist;
    @FXML
    private JFXButton btnDescargar;
    private StackPane panelPrincipal;
    private Listareproduccion lista;
    @FXML
    private ListView<String> lstCanciones;
    private ArrayList<String> nombreCanciones;
    private ArrayList<Cancion> canciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setPanelPrincipal(StackPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setLista(Listareproduccion lista) {
        this.lista = lista;
        lblPlaylist.setText(lista.getNombre());
        System.out.println(lista.getCancionList().size());
        mostrarCanciones();
    }

    @FXML
    private void descargarPlayList(ActionEvent event) {
    }

    public void mostrarCanciones(){
        canciones = new ArrayList();
        nombreCanciones = new ArrayList();
        canciones = (ArrayList<Cancion>) lista.getCancionList();
        for (Cancion cancion : canciones) {
                nombreCanciones.add(cancion.getNombre());
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(nombreCanciones);
        lstCanciones.setItems(items);
    }
}
