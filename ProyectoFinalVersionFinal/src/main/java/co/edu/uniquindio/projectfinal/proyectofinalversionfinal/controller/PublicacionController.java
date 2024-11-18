package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ComentarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IPublicacionControllerService;

import java.util.List;

public class PublicacionController implements IPublicacionControllerService {
    ModelFactory modelFactory;

    public PublicacionController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public void darMeGusta(UsuarioDto usuario, String idVendedor, PublicacionDto publicacion) {
        modelFactory.darMeGustaPublicacion(usuario, idVendedor, publicacion);
    }

    @Override
    public boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.agregarPublicacion(publicacion, vendedor.getIdVendedor());
    }

    @Override
    public boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.eliminarPublicacion(publicacion, vendedor);
    }

    @Override
    public boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.actualizarPublicacion(publicacion, vendedor);
    }

    @Override
    public List<VendedorDto> getListaMeGustas(String idVendedor, PublicacionDto dto) {
        return modelFactory.getListaMeGustaDto(idVendedor, dto);
    }

    @Override
    public List<ComentarioDto> getListaComentarios(String idPublicacion, PublicacionDto dto) {
        return modelFactory.getListaComentariosDto(idPublicacion, dto);
    }

    @Override
    public boolean agregarComentario(ComentarioDto dto, PublicacionDto publicacion) {
        return modelFactory.agregarComentario(dto, publicacion);
    }

    @Override
    public void darLikeComentario(ComentarioDto dto, PublicacionDto publicacion) {
        modelFactory.darLikeComentario(dto, publicacion);
    }

    @Override
    public int getLikesComentario(ComentarioDto dto, PublicacionDto publicacion) {
        return modelFactory.getLikesComentario(dto, publicacion);
    }
}
