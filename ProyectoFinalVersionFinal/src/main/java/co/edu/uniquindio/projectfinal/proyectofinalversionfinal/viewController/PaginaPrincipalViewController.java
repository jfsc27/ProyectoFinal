package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.MensajeController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.MuroController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.PublicacionController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.UsuarioController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;

import java.io.IOException;

public class PaginaPrincipalViewController {
    ModelFactory modelFactory;
    VendedorDto vendedor;
    PublicacionController publicacionController;
    UsuarioController usuarioController;
    MuroController muroController;
    MensajeController mensajeController;

    public void inicializarPaginaPrincipal(VendedorDto vendedor) throws IOException {
        modelFactory = ModelFactory.getInstance();
        publicacionController = new PublicacionController();
        usuarioController = new UsuarioController();
        muroController = new MuroController();
        mensajeController = new MensajeController();
    }
}
