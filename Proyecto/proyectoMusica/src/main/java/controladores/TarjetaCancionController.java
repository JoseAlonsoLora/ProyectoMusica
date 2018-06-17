/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clasesApoyo.MediaControl;
import com.jfoenix.controls.JFXButton;
import com.mycompany.proyectomusica.MainApp;
import static com.sun.javafx.PlatformUtil.isWindows;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import modelo.Cancion;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author alonso
 */
public class TarjetaCancionController implements Initializable {

    private Cancion cancion;
    @FXML
    private Label lblNombreCancion;
    @FXML
    private Label lblArtista;
    @FXML
    private Label lblAlbum;
    @FXML
    private StackPane pnlTarjeta;
    @FXML
    private JFXButton bntMenuCancion;
    private Properties recurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnlTarjeta.getStyleClass().add("pane");
        recurso = MainApp.leerConfig();
        // TODO
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
        lblNombreCancion.setText(this.cancion.getNombre());
        lblArtista.setText(this.cancion.getNombreArtista());
        lblAlbum.setText(this.cancion.getNombreAlbum());
    }

    @FXML
    private void mostrarMenu(ActionEvent event) {
        PopOver pop = new PopOver();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(
                TarjetaCancionController.class.getResource("/fxml/TarjetaMenuCancion.fxml"));
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TarjetaCancionController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        TarjetaMenuCancionController controlador = loader.getController();
        controlador.setCancion(cancion);
        pop.setContentNode(root);
        pop.show(bntMenuCancion);
    }

    @FXML
    private void reproducirCancion(MouseEvent event) {
        PantallaPrincipalController.agregarHistorial(cancion);
        String ip = recurso.getProperty("ipAddress");
        String puerto = recurso.getProperty("portStreaming");
        String rutaCancion = cancion.getNombrearchivo().replace("/", "-");
        String rutaFinal;
        if(isWindows()){
            rutaFinal = rutaCancion.replace(" ", "*");
        }else{
            rutaFinal = rutaCancion.replace("\\s", "*");
        }
        String ruta = "http://"+ip+":"+puerto+"/listen/"+rutaFinal;
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 540, 241);

        // create media player
        Media media = new Media(ruta);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
