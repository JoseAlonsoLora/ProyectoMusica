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
import modelo.Listareproduccion;

/**
 * FXML Controller class
 *
 * @author raymundo
 */
public class PantallaListasReproduccionController implements Initializable {

    @FXML
    private JFXListView<String> lstListas;
    @FXML
    private JFXButton btnNuvaLista;
    private ArrayList<String> nombresListas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarListas();
    }

    public void mostrarListas() {
        nombresListas = new ArrayList();
        ClienteListaReproduccion clienteLista = new ClienteListaReproduccion();
        List<Listareproduccion> listas = clienteLista.findAll();
        for(Listareproduccion lista: listas){
            nombresListas.add(lista.getNombre());
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(nombresListas);
        lstListas.setItems(items);
    }

    @FXML
    private void crearNuevaLista(ActionEvent event) {
    }

}
