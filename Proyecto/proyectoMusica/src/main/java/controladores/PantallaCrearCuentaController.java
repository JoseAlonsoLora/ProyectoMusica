package controladores;

import clientes.ClienteUsuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.proyectomusica.MainApp;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class PantallaCrearCuentaController implements Initializable {

    @FXML
    private JFXTextField campoNombre;
    @FXML
    private JFXTextField campoApellido;
    @FXML
    private JFXTextField campoCorreo;
    @FXML
    private JFXTextField campoUsuario;
    @FXML
    private JFXPasswordField campoContraseña;
    @FXML
    private JFXButton botonRegistrarse;
    @FXML
    private JFXButton botonCancelar;
    @FXML
    private Label etiquetaAdvertenciaNombre;
    @FXML
    private Label etiquetaAdvertenciaApellido;
    @FXML
    private Label etiquetaAdvertenciaCorreo;
    @FXML
    private Label etiquetaAdvertenciaUsuario;
    @FXML
    private Label etiquetaAdvertenciaContraseña;
    private Properties recurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = MainApp.leerConfig();

    }

    @FXML
    private void registrarUsuario(ActionEvent event) throws NoSuchAlgorithmException {

        if (!verificarCamposVacios(campoNombre, campoApellido, campoCorreo, campoUsuario, campoContraseña)) {
            if (!verificarLongitudExcedida(campoNombre, campoApellido, campoCorreo, campoUsuario)) {
                if (validarCorreo(campoCorreo.getText().trim())) {
                    JSONObject usuarioNuevo = new JSONObject();
                    usuarioNuevo.put("nombres", campoNombre.getText().trim());
                    usuarioNuevo.put("contrasena", cifrarContrasena(campoContraseña.getText()));
                    usuarioNuevo.put("nombreUsuario", campoUsuario.getText().trim());
                    usuarioNuevo.put("apellidos", campoApellido.getText().trim());
                    usuarioNuevo.put("correo", campoCorreo.getText().trim());

                    Client cliente = ClientBuilder.newClient();
                    String ip = recurso.getProperty("ipAddress");
                    String puerto = recurso.getProperty("portDjango");
                    WebTarget webTarget = cliente.target("http://"+ip+":"+puerto+"/crearUsuario/");
                    webTarget.request(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(usuarioNuevo.toMap(), javax.ws.rs.core.MediaType.APPLICATION_JSON));
                    mostrarMensaje("", "Registro exitoso", "La cuenta se ha registrado correctamente");

                } else {
                    mostrarMensaje("", "Correo no válido", "El correo ingresado no tiene un formato válido");
                }
            }

        }

    }

    public boolean verificarCamposVacios(JFXTextField campoNombre, JFXTextField campoApellido, JFXTextField campoCorreo, JFXTextField campoUsuario, JFXPasswordField campoContraseña) {
        boolean camposVacios = false;

        if (campoNombre.getText().trim().isEmpty()) {
            mandarAdvertencia(etiquetaAdvertenciaNombre);
            camposVacios = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaNombre);
        }

        if (campoApellido.getText().trim().isEmpty()) {
            mandarAdvertencia(etiquetaAdvertenciaApellido);
            camposVacios = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaApellido);
        }

        if (campoCorreo.getText().trim().isEmpty()) {
            mandarAdvertencia(etiquetaAdvertenciaCorreo);
            camposVacios = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaCorreo);
        }

        if (campoUsuario.getText().trim().isEmpty()) {
            mandarAdvertencia(etiquetaAdvertenciaUsuario);
            camposVacios = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaUsuario);
        }

        if (campoContraseña.getText().trim().isEmpty()) {
            mandarAdvertencia(etiquetaAdvertenciaContraseña);
            camposVacios = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaContraseña);
        }

        if (camposVacios) {
            mostrarMensaje("Campo vacio", "Algún campo esta vacío", "Debe llenar todos los campos requeridos");
        }

        return camposVacios;
    }

    public boolean verificarLongitudExcedida(JFXTextField campoNombre, JFXTextField campoApellido, JFXTextField campoCorreo, JFXTextField campoUsuario) {
        boolean longitudExcedida = false;

        if (campoNombre.getText().trim().length() > 45) {
            mandarAdvertencia(etiquetaAdvertenciaNombre);
            longitudExcedida = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaNombre);
        }

        if (campoApellido.getText().trim().length() > 45) {
            mandarAdvertencia(etiquetaAdvertenciaApellido);
            longitudExcedida = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaApellido);
        }

        if (campoCorreo.getText().trim().length() > 45) {
            mandarAdvertencia(etiquetaAdvertenciaCorreo);
            longitudExcedida = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaCorreo);
        }

        if (campoUsuario.getText().trim().length() > 20) {
            mandarAdvertencia(etiquetaAdvertenciaUsuario);
            longitudExcedida = true;
        } else {
            desactivarAdvertencia(etiquetaAdvertenciaUsuario);
        }

        if (longitudExcedida) {
            mostrarMensaje("Campos excedidos", "Algún campo excede el límite de caracteres", "Revise el límite de caracteres permitidos");

        }

        return longitudExcedida;
    }

    public void mandarAdvertencia(Label etiqueta) {
        etiqueta.setText("*");
    }

    public void desactivarAdvertencia(Label etiqueta) {
        etiqueta.setText("");
    }

    public boolean validarCorreo(String correo) {
        String formatoCorreo = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patron = Pattern.compile(formatoCorreo);
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }

    @FXML
    private void limitarCaracteresNombre(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, campoNombre, 30);
        if (!(Character.isLetter(caracter) || Character.isSpaceChar(caracter))) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresApellido(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, campoApellido, 30);
        if (!(Character.isLetter(caracter) || Character.isSpaceChar(caracter))) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresCorreo(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, campoCorreo, 45);
        if (Character.isSpaceChar(caracter)) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresUsuario(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        limitarCaracteres(event, campoCorreo, 45);
        if (Character.isSpaceChar(caracter)) {
            event.consume();
        }

    }

    public void limitarCaracteres(KeyEvent event, JFXTextField campo, int caracteresMaximos) {
        if (campo.getText().trim().length() >= caracteresMaximos) {
            event.consume();
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

    public void mostrarMensaje(String titulo, String encabezado, String contenido) {
        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle(titulo);
        advertencia.setHeaderText(encabezado);
        advertencia.setContentText(contenido);
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        advertencia.getButtonTypes().setAll(botonOK);
        advertencia.showAndWait();
    }

}
