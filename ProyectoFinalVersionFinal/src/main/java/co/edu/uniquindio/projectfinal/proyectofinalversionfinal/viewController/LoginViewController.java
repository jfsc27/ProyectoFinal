package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.UsuarioController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

public class LoginViewController {
    private ModelFactory modelFactory;
    private UsuarioController usuarioController;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField textContraseña;

    @FXML
    private TextField textUsuario;

    @FXML
    void initialize(){
        usuarioController = new UsuarioController();
    }

    @FXML
    void onIniciarSesion(ActionEvent event) throws IOException {
        login();
    }

    public void login() throws IOException {
        UsuarioDto dto = buildUsuarioDto();
        if (usuarioController.validarUsuario(dto)){
            abrirVentana(usuarioController.getUsuario(dto));
        }
    }

    public void abrirVentana(UsuarioDto usuario) throws IOException {
        if (usuario instanceof VendedorDto){
            JOptionPane.showMessageDialog(null, "Bienvenido Vendedor "+ usuario.getNombre());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/paginaPrincipal-view.fxml"));
        }
    }

    public UsuarioDto buildUsuarioDto(){
        return new UsuarioDto(
                null,
                null,
                null,
                null,
                textUsuario.getText(),
                textContraseña.getText()
        );
    }

    @FXML
    void onRegistrarse(ActionEvent event) {

    }

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public void setModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

}
