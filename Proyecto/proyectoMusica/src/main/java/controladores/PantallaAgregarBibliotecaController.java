package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author T-REX
 */
public class PantallaAgregarBibliotecaController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser explorador = new FileChooser();
        File archivoSeleccionado = explorador.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            try {
                ZipInputStream archivo = new ZipInputStream(new FileInputStream(archivoSeleccionado));
                ZipEntry entrada;
                int i = 0;
                Label lblCancion;
                while (null != (entrada = archivo.getNextEntry())) {                    
                    lblCancion = new Label(entrada.getName());
                    grid.add(lblCancion, 0, i);
                    i++;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PantallaAgregarBibliotecaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
