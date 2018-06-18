/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clasesApoyo.HiloCargaCanciones;
import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raymundo
 */
public class BarraProgresoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private byte[] zip;
    private String tipo;
    private int puerto;
    private boolean tipoCarga;
    private String ip;
    private String ruta;
    private Stage stageActual;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label labelTipoCarga;
    private StackPane pnlPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public byte[] getZip() {
        return zip;
    }

    public void setZip(byte[] zip) {
        this.zip = zip;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        labelTipoCarga.setText(tipo);
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void setTipoCarga(boolean tipoCarga) {
        this.tipoCarga = tipoCarga;
        if (tipoCarga) {
            Thread thread = new Thread(new HiloCargaCanciones(zip, puerto, ip, ruta, this));
            thread.start();
        }
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setStageActual(Stage stageActual) {
        this.stageActual = stageActual;
    }

    public void cargaCompleta() {
        try {
            stageActual.close();
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaAgregarBiblioteca.fxml"));
            Parent root = (Parent) loader.load();
            PantallaAgregarBibliotecaController pantallaAgregarAlbum = loader.getController();
            pantallaAgregarAlbum.setPanelPrincipal(pnlPrincipal);
            pnlPrincipal.getChildren().clear();
            pnlPrincipal.getChildren().add(root);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Album creado exitosamente");
            alert.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(BarraProgresoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPnlPrincipal(StackPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }

}
