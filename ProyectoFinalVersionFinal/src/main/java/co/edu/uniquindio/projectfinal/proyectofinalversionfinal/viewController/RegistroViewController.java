package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.UsuarioController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroViewController implements Initializable {
    UsuarioController usuarioController;

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private CheckBox checkTerminos;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private PasswordField txtConfirmPass;

    @FXML
    private TextField txtConfirmarVisible;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtPassVisible;

    @FXML
    private TextField txtUsuario;

    @FXML
    void onCrearCuenta(ActionEvent event) throws IOException {
        if (checkTerminos.isSelected() && !txtApellido.getText().isEmpty() && !txtCedula.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtId.getText().isEmpty() && !txtNombre.getText().isEmpty() && txtUsuario.getText().isEmpty()) {
            if (!txtPass.getText().isEmpty() || !txtPassVisible.getText().isEmpty() && !txtConfirmPass.getText().isEmpty() || !txtConfirmarVisible.getText().isEmpty()) {
                if (txtPass.getText().equals(txtConfirmPass.getText()) || txtPassVisible.getText().equals(txtConfirmarVisible.getText()) ) {
                    VendedorDto dto = new VendedorDto();
                    dto.setApellido(txtApellido.getText());
                    dto.setCedula(txtCedula.getText());
                    dto.setNombre(txtNombre.getText());
                    dto.setPassword(txtPass.getText());
                    dto.setUsuario(txtUsuario.getText());
                    dto.setDireccion(txtDireccion.getText());
                    dto.setIdVendedor(txtId.getText());
                    if (usuarioController.crearUsuario(dto)) {
                        JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente!");
                        pasarPaginaPrincipal((VendedorDto) usuarioController.getUsuario(dto));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al crear el usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tu contraseña no coincide");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los espacios correctamente");
        }
    }

    public void pasarPaginaPrincipal(VendedorDto dto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/paginaPrincipal.fxml"));
        Scene scene = new Scene(loader.load(),490,650);
        PaginaPrincipalViewController controller = loader.getController();
        controller.inicializarPaginaPrincipal(dto);
        Stage stage = new Stage();
        stage.setScene(scene);

        Stage stageCerrar = (Stage) btnCrearCuenta.getScene().getWindow();
        stageCerrar.close();

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioController = new UsuarioController();
    }
}
