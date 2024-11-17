package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ProductoDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Usuario;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IUsuarioControllerServices;

import java.util.List;

public class UsuarioController implements IUsuarioControllerServices {
    ModelFactory modelFactory;

    public UsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return modelFactory.getUsuario(usuario);
    }

    @Override
    public boolean validarUsuario(UsuarioDto usuario) {
        if (modelFactory.validarLogin(usuario)) {
            return true;
        }
        return false;
    }

    @Override
    public Usuario getUsuarioCompleto(UsuarioDto usuario) {
        return modelFactory.getUsuarioCompleto(usuario);
    }

    @Override
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario) {
        return modelFactory.getListaProductosDisponibles(usuario);
    }

    @Override
    public List<VendedorDto> getListaContactos(UsuarioDto usuario) {
        return modelFactory.getListaContactosDto(((VendedorDto)usuario).getIdVendedor());
    }

    @Override
    public UsuarioDto getUsuarioPorId(String id) {
        return modelFactory.getUsuarioPorId(id);
    }

    @Override
    public boolean crearUsuario(VendedorDto vendedor) {
        return modelFactory.crearUsuario(vendedor);
    }
}
