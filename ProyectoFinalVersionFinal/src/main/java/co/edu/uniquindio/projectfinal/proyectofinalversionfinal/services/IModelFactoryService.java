package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;


import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.*;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.*;

import java.util.List;

public interface IModelFactoryService {
    public UsuarioDto getUsuario(UsuarioDto usuario);
    public boolean validarLogin(UsuarioDto usuario);
    public Usuario getUsuarioCompleto(UsuarioDto usuario);
    public void darMeGustaPublicacion(UsuarioDto usuario, String idVendedor, PublicacionDto dto);
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario);
    boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor);
    List<PublicacionDto> getListaPublicaciones(Muro muro);
    boolean crearUsuario(VendedorDto vendedor);

    ChatDto getChat(VendedorDto vendedor, VendedorDto contacto);

    //Retorno de listas asociadas a clases
    List<ProductoDto> getListaProductosDto(String id);

    List<VendedorDto> getListaContactosDto(String id);
    List<Vendedor> getListaContactos(String id);

    List<Comentario> getListaComentarios(String idVendedor, PublicacionDto publicacion);
    List<ComentarioDto> getListaComentariosDto(String idVendedor, PublicacionDto publicacion);

    List<Vendedor> getListaMeGusta(String idVendedor, PublicacionDto dto);
    List<VendedorDto> getListaMeGustaDto(String idVendedor, PublicacionDto dto);

    List<Publicacion> getListaPublicaciones(String idVendedor);
    List<PublicacionDto> getListaPublicacionesDto(String idVendedor);

    List<MensajeDto> getListaMensajeChat(String id);

    // CRUD PUBLICACION
    boolean agregarPublicacion(PublicacionDto publicacion, String idVendedor);
    // CRUD COMENTARIO
    boolean agregarComentario(ComentarioDto comentario, PublicacionDto publicacion);
    void darLikeComentario(ComentarioDto comentario, PublicacionDto publicacion);
    int getLikesComentario(ComentarioDto dto, PublicacionDto publicacion);
}
