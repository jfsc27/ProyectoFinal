package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IMuroControllerServices;

import java.util.List;

public class MuroController implements IMuroControllerServices {
    ModelFactory modelFactory;

    public MuroController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public List<PublicacionDto> getListaPublicaciones(UsuarioDto usuario) {
        return modelFactory.getListaPublicacionesDto(((VendedorDto)usuario).getIdVendedor());
    }
}
