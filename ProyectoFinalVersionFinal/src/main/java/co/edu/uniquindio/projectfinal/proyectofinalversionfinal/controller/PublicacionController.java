package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ComentarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.UsuarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IPublicacionControllerService;

import java.util.List;
/**
 * La clase PublicacionController gestiona las operaciones relacionadas con publicaciones,
 * incluyendo comentarios, likes, actualizaciones y eliminación de publicaciones.
 * Implementa la interfaz {@link IPublicacionControllerService} y utiliza {@link ModelFactory}
 * para interactuar con el modelo.
 */
public class PublicacionController implements IPublicacionControllerService {
    ModelFactory modelFactory;

    /**
     * Constructor de la clase PublicacionController.
     * Inicializa la instancia de ModelFactory.
     */
    public PublicacionController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Aplica un descuento a una publicación específica de un vendedor.
     *
     * @param idVendedor    el identificador único del vendedor.
     * @param idPublicacion el identificador único de la publicación.
     */
    public void aplicarDescuento(String idVendedor, String idPublicacion){
        modelFactory.aplicarDescuento(idVendedor, idPublicacion);
    }

    /**
     * Permite que un usuario dé "me gusta" a una publicación de un vendedor.
     *
     * @param usuario       el usuario que da "me gusta", representado por {@link UsuarioDto}.
     * @param idVendedor    el identificador único del vendedor.
     * @param publicacion   la publicación a la cual se le da "me gusta", representada por {@link PublicacionDto}.
     */
    @Override
    public void darMeGusta(UsuarioDto usuario, String idVendedor, PublicacionDto publicacion) {
        modelFactory.darMeGustaPublicacion(usuario, idVendedor, publicacion);
    }

    /**
     * Agrega una nueva publicación al muro de un vendedor.
     *
     * @param publicacion la publicación a agregar, representada por {@link PublicacionDto}.
     * @param vendedor    el vendedor al cual se le agrega la publicación, representado por {@link VendedorDto}.
     * @return true si la publicación fue agregada exitosamente, false en caso contrario.
     */
    @Override
    public boolean agregarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.agregarPublicacion(publicacion, vendedor.getIdVendedor());
    }

    /**
     * Elimina una publicación específica de un vendedor.
     *
     * @param publicacion la publicación a eliminar, representada por {@link PublicacionDto}.
     * @param vendedor    el vendedor al cual pertenece la publicación, representado por {@link VendedorDto}.
     * @return true si la publicación fue eliminada exitosamente, false en caso contrario.
     */
    @Override
    public boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.eliminarPublicacion(publicacion, vendedor);
    }

    /**
     * Actualiza los detalles de una publicación de un vendedor.
     *
     * @param publicacion la publicación con los nuevos datos, representada por {@link PublicacionDto}.
     * @param vendedor    el vendedor propietario de la publicación, representado por {@link VendedorDto}.
     * @return true si la publicación fue actualizada exitosamente, false en caso contrario.
     */
    @Override
    public boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return modelFactory.actualizarPublicacion(publicacion, vendedor);
    }

    /**
     * Obtiene la lista de usuarios que han dado "me gusta" a una publicación.
     *
     * @param idVendedor el identificador único del vendedor propietario de la publicación.
     * @param dto        la publicación, representada por {@link PublicacionDto}.
     * @return una lista de objetos {@link VendedorDto} que representan los usuarios que dieron "me gusta".
     */
    @Override
    public List<VendedorDto> getListaMeGustas(String idVendedor, PublicacionDto dto) {
        return modelFactory.getListaMeGustaDto(idVendedor, dto);
    }

    /**
     * Obtiene la lista de comentarios de una publicación.
     *
     * @param idPublicacion el identificador único de la publicación.
     * @param dto           la publicación, representada por {@link PublicacionDto}.
     * @return una lista de objetos {@link ComentarioDto} que representan los comentarios de la publicación.
     */
    @Override
    public List<ComentarioDto> getListaComentarios(String idPublicacion, PublicacionDto dto) {
        return modelFactory.getListaComentariosDto(idPublicacion, dto);
    }

    /**
     * Agrega un comentario a una publicación.
     *
     * @param dto         el comentario a agregar, representado por {@link ComentarioDto}.
     * @param publicacion la publicación a la cual se agregará el comentario, representada por {@link PublicacionDto}.
     * @return true si el comentario fue agregado exitosamente, false en caso contrario.
     */
    @Override
    public boolean agregarComentario(ComentarioDto dto, PublicacionDto publicacion) {
        return modelFactory.agregarComentario(dto, publicacion);
    }

    /**
     * Permite que un usuario dé "me gusta" a un comentario de una publicación.
     *
     * @param dto         el comentario al cual se le dará "me gusta", representado por {@link ComentarioDto}.
     * @param publicacion la publicación que contiene el comentario, representada por {@link PublicacionDto}.
     */
    @Override
    public void darLikeComentario(ComentarioDto dto, PublicacionDto publicacion) {
        modelFactory.darLikeComentario(dto, publicacion);
    }

    /**
     * Obtiene el número de "me gusta" de un comentario en una publicación.
     *
     * @param dto         el comentario, representado por {@link ComentarioDto}.
     * @param publicacion la publicación que contiene el comentario, representada por {@link PublicacionDto}.
     * @return el número de "me gusta" del comentario.
     */
    @Override
    public int getLikesComentario(ComentarioDto dto, PublicacionDto publicacion) {
        return modelFactory.getLikesComentario(dto, publicacion);
    }
}
