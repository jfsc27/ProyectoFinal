package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ProductoDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Usuario;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IUsuarioControllerServices;

import java.util.List;

/**
 * La clase UsuarioController se encarga de gestionar las operaciones relacionadas
 * con los usuarios, tales como validación, gestión de contactos y productos disponibles.
 * Implementa la interfaz {@link IUsuarioControllerServices}.
 */
public class UsuarioController implements IUsuarioControllerServices {
    ModelFactory modelFactory;

    /**
     * Constructor de la clase UsuarioController.
     * Inicializa la instancia de ModelFactory.
     */
    public UsuarioController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Obtiene un usuario específico.
     *
     * @param usuario el usuario que se desea obtener, representado por {@link UsuarioDto}.
     * @return un objeto {@link UsuarioDto} que representa al usuario.
     */
    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return modelFactory.getUsuario(usuario);
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param usuario el usuario a validar, representado por {@link UsuarioDto}.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    @Override
    public boolean validarUsuario(UsuarioDto usuario) {
        if (modelFactory.validarLogin(usuario)) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene el usuario completo a partir de un objeto {@link UsuarioDto}.
     *
     * @param usuario el usuario en forma de DTO.
     * @return un objeto {@link Usuario} que representa al usuario completo.
     */
    @Override
    public Usuario getUsuarioCompleto(UsuarioDto usuario) {
        return modelFactory.getUsuarioCompleto(usuario);
    }

    /**
     * Obtiene la lista de productos disponibles para un usuario.
     *
     * @param usuario el usuario para el cual se obtendrán los productos, representado por {@link UsuarioDto}.
     * @return una lista de objetos {@link ProductoDto} que representan los productos disponibles.
     */
    @Override
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario) {
        return modelFactory.getListaProductosDisponibles(usuario);
    }

    /**
     * Obtiene la lista de contactos de un usuario que es un vendedor.
     *
     * @param usuario el usuario vendedor, representado por {@link UsuarioDto}.
     * @return una lista de objetos {@link VendedorDto} que representan los contactos del vendedor.
     * @throws ClassCastException si el usuario no puede ser convertido a {@link VendedorDto}.
     */
    @Override
    public List<VendedorDto> getListaContactos(UsuarioDto usuario) {
        return modelFactory.getListaContactosDto(((VendedorDto)usuario).getIdVendedor());
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id el identificador único del usuario.
     * @return un objeto {@link UsuarioDto} que representa al usuario.
     */
    @Override
    public UsuarioDto getUsuarioPorId(String id) {
        return modelFactory.getUsuarioPorId(id);
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param vendedor el usuario vendedor a crear, representado por {@link VendedorDto}.
     * @return true si el usuario fue creado exitosamente, false en caso contrario.
     */
    @Override
    public boolean crearUsuario(VendedorDto vendedor) {
        return modelFactory.crearUsuario(vendedor);
    }

    /**
     * Obtiene la lista de nuevos contactos potenciales para un vendedor.
     *
     * @param usuario el usuario vendedor, representado por {@link VendedorDto}.
     * @return una lista de objetos {@link VendedorDto} que representan los nuevos contactos.
     */
    @Override
    public List<VendedorDto> getContactosNuevos(VendedorDto usuario) {
        return modelFactory.getListaContactosNuevos(usuario);
    }
}
