package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.*;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.*;

import java.util.List;

public interface IMarketPlaceMapping {
    /*
     * Interface para los convertidores (mapeadores) de clases y listas entre los modelos y los DTOs.
     *
     * En el contexto de esta aplicación, se utilizan DTOs (Data Transfer Objects) para transferir
     * información entre la capa lógica y la capa de presentación. Esto permite una separación más clara
     * entre las diferentes capas de la aplicación y mejora la mantenibilidad del código.
     */

    // Métodos para convertir clases individuales (entidades a DTOs y viceversa)

    /**
     * Convierte un objeto Usuario en un UsuarioDto.
     * @param usuario Objeto Usuario a convertir.
     * @return Objeto UsuarioDto correspondiente.
     */
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    /**
     * Convierte un objeto UsuarioDto en un Usuario.
     * @param usuarioDto Objeto UsuarioDto a convertir.
     * @return Objeto Usuario correspondiente.
     */
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    /**
     * Convierte un objeto Chat en un ChatDto.
     * @param chat Objeto Chat a convertir.
     * @return Objeto ChatDto correspondiente.
     */
    ChatDto chatToChatDto(Chat chat);
    Chat chatDtoToChat(ChatDto chatDto);

    /**
     * Convierte un objeto PublicacionDto en una Publicacion.
     * @param publicacion Objeto PublicacionDto a convertir.
     * @return Objeto Publicacion correspondiente.
     */
    Publicacion publicacionDtoToPublicacion(PublicacionDto publicacion);

    /**
     * Convierte un objeto Publicacion en un PublicacionDto.
     * @param publicacion Objeto Publicacion a convertir.
     * @return Objeto PublicacionDto correspondiente.
     */
    PublicacionDto publicacionToPublicacionDto(Publicacion publicacion);

    // Convertidores para las entidades Producto, Mensaje, Comentario y Muro

    ProductoDto productoToProductoDto(Producto producto);
    Producto productoDtoToProducto(ProductoDto productoDto);

    Mensaje mesajeDtoToMensaje(MensajeDto mensaje);
    MensajeDto mensajeToMensajeDto(Mensaje mensaje);

    Comentario comentarioDtoToComentario(ComentarioDto comentario);
    ComentarioDto comentarioToComentarioDto(Comentario comentario);

    Muro muroDtoToMuro(MuroDto muro);
    MuroDto muroToMuroDto(Muro muro);

    // Métodos para convertir listas completas (entidades a DTOs y viceversa)

    /**
     * Convierte una lista de vendedores en una lista de VendedorDto.
     * @param vendedores Lista de objetos Vendedor.
     * @return Lista de objetos VendedorDto.
     */
    List<VendedorDto> VendedoresToVendedoresDto(List<Vendedor> vendedores);

    /**
     * Convierte una lista de VendedorDto en una lista de vendedores.
     * @param vendedores Lista de objetos VendedorDto.
     * @return Lista de objetos Vendedor.
     */
    List<Vendedor> VendedoresDtoToVendedores(List<VendedorDto> vendedores);

    /**
     * Convierte una lista de publicaciones en una lista de PublicacionDto.
     * @param publicaciones Lista de objetos Publicacion.
     * @return Lista de objetos PublicacionDto.
     */
    List<PublicacionDto> publicacionesToPublicacionesDto(List<Publicacion> publicaciones);

    /**
     * Convierte una lista de PublicacionDto en una lista de publicaciones.
     * @param publicacionesDto Lista de objetos PublicacionDto.
     * @return Lista de objetos Publicacion.
     */
    List<Publicacion> publicacionesDtoToPublicaciones(List<PublicacionDto> publicacionesDto);

    /**
     * Convierte una lista de productos en una lista de ProductoDto.
     * @param productos Lista de objetos Producto.
     * @return Lista de objetos ProductoDto.
     */
    List<ProductoDto> productosToProductosDto(List<Producto> productos);

    /**
     * Convierte una lista de ProductoDto en una lista de productos.
     * @param productosDto Lista de objetos ProductoDto.
     * @return Lista de objetos Producto.
     */
    List<Producto> productosDtoToProductos(List<ProductoDto> productosDto);

    // Convertidores para listas de comentarios y mensajes

    List<Comentario> comentariosDtoToComentarios(List<ComentarioDto> comentariosDto);
    List<ComentarioDto> comentariosToComentariosDto(List<Comentario> comentarios);

    List<Mensaje> mensajesDtoToMensajes(List<MensajeDto> mensajesDto);
    List<MensajeDto> mensajeToMensajesDto(List<Mensaje> mensajes);
}
