package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IMuroControllerServices;

import java.util.List;

/**
 * La clase MuroController se encarga de gestionar las operaciones relacionadas con el muro
 * de publicaciones de un usuario. Implementa la interfaz {@link IMuroControllerServices}.
 * Utiliza {@link ModelFactory} para acceder al modelo y obtener la información necesaria.
 */
public class MuroController implements IMuroControllerServices {
    ModelFactory modelFactory;

    /**
     * Constructor de la clase MuroController.
     * Inicializa la instancia de ModelFactory.
     */
    public MuroController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Obtiene la lista de publicaciones asociadas a un usuario específico.
     *
     * @param usuario el usuario del cual se desea obtener las publicaciones,
     *                representado por un objeto {@link UsuarioDto}.
     * @return una lista de objetos {@link PublicacionDto} que representan las publicaciones
     *         del usuario.
     * @throws ClassCastException si el usuario no puede ser convertido a {@link VendedorDto}.
     */
    @Override
    public List<PublicacionDto> getListaPublicaciones(UsuarioDto usuario) {
        return modelFactory.getListaPublicacionesDto(((VendedorDto)usuario).getIdVendedor());
    }
}
