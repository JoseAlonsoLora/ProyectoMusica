/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.proyectomusica.MainApp;
import static com.mycompany.proyectomusica.MainApp.leerConfig;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import modelo.Listareproduccion;
import modelo.Sesion;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class PantallaIniciarSesionController implements Initializable {

    @FXML
    private JFXTextField campoUsuario;
    @FXML
    private JFXButton botonIniciarSesion;
    @FXML
    private JFXPasswordField campoContraseña;
    private Properties recurso;
    private Stage stageActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = MainApp.leerConfig();
    }

    @FXML
    private void iniciarSesion(ActionEvent event) throws NoSuchAlgorithmException {
        if (campoUsuario.getText().trim().isEmpty() || campoContraseña.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Algunos de los campos están vacíos");
            alert.showAndWait();
        } else {
            Client cliente = ClientBuilder.newClient();
            String ip = recurso.getProperty("ipAddress");
            String puerto = recurso.getProperty("portDjango");
            WebTarget webTarget = cliente.target("http://" + ip + ":" + puerto + "/iniciarSesion/?id=" + campoUsuario.getText().trim() + "&password=" + cifrarContrasena(campoContraseña.getText().trim()));
            Sesion sesion = webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<Sesion>() {
            });
            if (sesion.getResult().equals("true")) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaPrincipal.fxml"));
                    Parent root = (Parent) loader.load();
                    PantallaPrincipalController pantallaPrincipal = loader.getController();
                    pantallaPrincipal.setNombreUsuario(campoUsuario.getText());
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/Styles.css");
                    stage.setTitle("Pantalla Principal");
                    stage.setScene(scene);
                    stage.show();
                    stageActual.close();
                } catch (IOException ex) {
                    Logger.getLogger(PantallaIniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alertUsuarioInvalido = new Alert(Alert.AlertType.INFORMATION);
                alertUsuarioInvalido.setTitle("Información");
                alertUsuarioInvalido.setHeaderText(null);
                alertUsuarioInvalido.setContentText("El nombre de usuario y contraseña no coinciden");
                alertUsuarioInvalido.showAndWait();
            }
        }

    }

    public String cifrarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(contrasena.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    @FXML
    private void crearCuenta(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(PantallaPrincipalController.class.getResource("/fxml/PantallaCrearCuenta.fxml"));
            Parent root = (Parent) loader.load();
            PantallaCrearCuentaController pantallaCrearCuenta = loader.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle("Crear cuenta");
            stage.setScene(scene);
            stage.show();
            stageActual.close();
        } catch (IOException ex) {
            Logger.getLogger(PantallaIniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStageActual(Stage stageActual) {
        this.stageActual = stageActual;
    }

}
