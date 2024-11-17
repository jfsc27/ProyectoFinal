package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ProductoDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Usuario;

import java.util.List;

public interface IUsuarioControllerServices {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarUsuario(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario);
    public List<VendedorDto> getListaContactos(UsuarioDto usuario);
    public UsuarioDto getUsuarioPorId(String id);
    boolean crearUsuario(VendedorDto vendedor);
}
