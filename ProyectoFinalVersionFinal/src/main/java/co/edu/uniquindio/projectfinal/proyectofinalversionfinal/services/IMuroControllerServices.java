package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;

import java.util.List;

public interface IMuroControllerServices {
    List<PublicacionDto> getListaPublicaciones(UsuarioDto usuario);
}
