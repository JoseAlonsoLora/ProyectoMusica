/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteListaReproduccion;
import clientes.ClienteUsuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import modelo.Listareproduccion;
import modelo.Usuario;

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
    private List<Listareproduccion> listasReproduccionUsuario;
    private ArrayList<String> nombresListas;
    private ClienteListaReproduccion clienteLista;
    private ClienteUsuario clienteUsuario;
    private Usuario usuarioActual;
    private StackPane panelPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteLista = new ClienteListaReproduccion();
        clienteUsuario = new ClienteUsuario();
        usuarioActual = clienteUsuario.find("RayPerez");
        mostrarListas();
    }

    public void setPanelPrincipal(StackPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void mostrarListas() {
        listasReproduccionUsuario = new ArrayList();
        nombresListas = new ArrayList();
        List<Listareproduccion> listas = clienteLista.findAll();
        for (Listareproduccion lista : listas) {
            if (lista.getUsuarionombreUsuario().getNombreUsuario().equals("RayPerez")) {
                listasReproduccionUsuario.add(lista);
                nombresListas.add(lista.getNombre());
            }
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(nombresListas);
        lstListas.setItems(items);
        lstListas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCancionesPlayList.fxml"));
                    Parent root = (Parent) loader.load();
                    PantallaCancionesPlayListController pantallaCancionesPlayList = loader.getController();
                    pantallaCancionesPlayList.setPanelPrincipal(panelPrincipal);
                    pantallaCancionesPlayList.setLista(listasReproduccionUsuario.get(lstListas.getSelectionModel().getSelectedIndex()));
                    panelPrincipal.getChildren().clear();
                    panelPrincipal.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(PantallaListasReproduccionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void crearNuevaLista(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nueva lista");
        dialog.setHeaderText("Nueva lista");
        dialog.setContentText("Nombre de la lista:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Listareproduccion nuevaLista = new Listareproduccion();
            nuevaLista.setNombre(result.get());
            nuevaLista.setUsuarionombreUsuario(usuarioActual);
            clienteLista.create(nuevaLista);
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaListasReproduccion.fxml"));
            Parent root = (Parent) loader.load();
            PantallaListasReproduccionController pantallaPlayList = loader.getController();
            pantallaPlayList.setPanelPrincipal(panelPrincipal);
            panelPrincipal.getChildren().clear();
            panelPrincipal.getChildren().add(root);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Lista creada exitosamente");
            alert.showAndWait();
        }
    }

}
