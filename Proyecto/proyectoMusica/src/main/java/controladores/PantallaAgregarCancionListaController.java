/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clientes.ClienteAlbum;
import clientes.ClienteCancion;
import clientes.ClienteListaReproduccion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.mycompany.proyectomusica.MainApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import modelo.Cancion;
import modelo.Listareproduccion;
import org.json.JSONObject;

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
    private Properties recurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = MainApp.leerConfig();
        listPlayList.setDepth(1);
        listPlayList.setExpanded(true);
        mostrarListas();
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    @FXML
    private void agregarCancionPlayList(ActionEvent event) {
        if (listPlayList.getSelectionModel().getSelectedItem() != null) {
            if (!hayNombreRepetido()) {
                Client cliente = ClientBuilder.newClient();
                JSONObject listaCancion = new JSONObject();
                listaCancion.put("idLista", listPlayList.getSelectionModel().getSelectedItem().getIdlistareproduccion());
                listaCancion.put("idCancion", cancion.getIdcancion());
                String ip = recurso.getProperty("ipAddress");
                String puerto = recurso.getProperty("portDjango");
                WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/agregarALista/");
                webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(listaCancion.toMap(), javax.ws.rs.core.MediaType.APPLICATION_JSON));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Canción agregada");
                alert.showAndWait();
                Stage mainStage = (Stage) btnAceptar.getScene().getWindow();
                mainStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("No puede tener un nombre de canción repetido en la misma PlayList");
                alert.showAndWait();
            }
        } else {
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

    public boolean hayNombreRepetido() {
        boolean hayNombreRepetido = false;
        ClienteCancion cliente = new ClienteCancion();
        List<Cancion> cancionesPlayList = cliente.obtenerCancionesLista(
                listPlayList.getSelectionModel().getSelectedItem().getIdlistareproduccion());
        for (Cancion cancion : cancionesPlayList) {
            if (this.cancion.getNombre().equals(cancion.getNombre())) {
                hayNombreRepetido = true;
                break;
            }
        }
        return hayNombreRepetido;
    }

}
