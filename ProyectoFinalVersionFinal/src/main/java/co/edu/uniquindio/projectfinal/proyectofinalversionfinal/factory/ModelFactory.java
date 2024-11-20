package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.*;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.*;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IModelFactoryService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La clase ModelFactory es una implementación del patrón de diseño Singleton que actúa como intermediario
 * entre los controladores y el modelo de la aplicación. Proporciona métodos para realizar operaciones
 * en el modelo y convierte entidades en DTOs y viceversa, utilizando la clase {@link MarketPlaceMappingImpl}.
 * También expone los métodos necesarios para interactuar con el sistema de negocio representado por {@link MarketPlace}.
 */
public class ModelFactory implements IModelFactoryService {
    private static ModelFactory instance;
    private static MarketPlace marketPlace;
    private static MarketPlaceMappingImpl mapping;

    /**
     * Constructor privado que inicializa la lógica de negocio y el mapeador.
     * Este constructor garantiza la inicialización adecuada del sistema.
     */
    private ModelFactory() {
        inicializarDatos();
        mapping = new MarketPlaceMappingImpl();
        mapping.setModelFactory(this);
    }

    /**
     * Devuelve la instancia única de la fábrica de modelos, garantizando
     * el patrón Singleton.
     *
     * @return la instancia única de {@link ModelFactory}.
     */
    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    /**
     * Aplica un descuento a una publicación específica de un vendedor.
     *
     * @param idVendedor    el identificador único del vendedor.
     * @param idPublicacion el identificador único de la publicación.
     */
    public void aplicarDescuento(String idVendedor, String idPublicacion){
        marketPlace.aplicarDescuento(idVendedor, idPublicacion);
    }

    /**
     * Obtiene un usuario validado a partir de sus credenciales encapsuladas en un DTO.
     *
     * @param usuario el objeto {@link UsuarioDto} con las credenciales.
     * @return un objeto {@link UsuarioDto} si las credenciales son válidas; de lo contrario, null.
     */
    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return validarLogin(usuario) ?
                mapping.usuarioToUsuarioDto(marketPlace.getUsuarioLogin(usuario.getUsuario(), usuario.getPassword())) :
                null;
    }

    /**
     * Obtiene un usuario segun su identificacion
     * @param id
     * @return
     */
    public UsuarioDto getUsuarioPorId(String id) {
        return mapping.usuarioToUsuarioDto(marketPlace.getUsuarioPorId(id));
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param usuario el objeto {@link UsuarioDto} con las credenciales a validar.
     * @return true si las credenciales son válidas; de lo contrario, false.
     */
    @Override
    public boolean validarLogin(UsuarioDto usuario) {
        if (marketPlace.verificarUsuario(usuario.getUsuario(), usuario.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene un usuario completo del modelo a partir de un DTO de usuario.
     *
     * @param usuario el objeto {@link UsuarioDto} con las credenciales del usuario.
     * @return un objeto {@link Usuario} correspondiente al usuario completo.
     */
    @Override
    public Usuario getUsuarioCompleto(UsuarioDto usuario) {
        return marketPlace.getUsuarioLogin(usuario.getUsuario(), usuario.getPassword());
    }

    /**
     * Agrega un "me gusta" a una publicación específica.
     *
     * @param usuario     el usuario que da "me gusta", representado como {@link UsuarioDto}.
     * @param idVendedor  el identificador del vendedor propietario de la publicación.
     * @param dto         el objeto {@link PublicacionDto} que representa la publicación.
     */
    @Override
    public void darMeGustaPublicacion(UsuarioDto usuario, String idVendedor, PublicacionDto dto) {
        marketPlace.darMeGustaPublicacion((Vendedor) mapping.usuarioDtoToUsuario(usuario), idVendedor, dto.getFechaPublicacion(), dto.getHoraPublicacion());
    }

    /**
     * Obtiene una lista de productos disponibles para un usuario.
     *
     * @param usuario el objeto {@link UsuarioDto} que representa al usuario.
     * @return una lista de objetos {@link ProductoDto} correspondientes a los productos disponibles.
     */
    @Override
    public List<ProductoDto> getListaProductosDisponibles(UsuarioDto usuario) {
        Usuario user = marketPlace.getUsuarioLogin(usuario.getUsuario(), usuario.getPassword());
        List<ProductoDto> disponibles = new ArrayList<>();
        if (user != null) {
            for (Producto producto:((Vendedor)user).getListaProductosDisponibles()){
                disponibles.add(mapping.productoToProductoDto(producto));
            }
        }
        return disponibles;
    }

    /**
     * Elimina una publicación asociada a un vendedor.
     *
     * @param publicacion el objeto {@link PublicacionDto} que representa la publicación a eliminar.
     * @param vendedor el objeto {@link VendedorDto} que representa al vendedor dueño de la publicación.
     * @return true si la publicación fue eliminada exitosamente, false en caso contrario.
     */
    @Override
    public boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return false;
    }

    /**
     * Actualiza los datos de una publicación asociada a un vendedor.
     *
     * @param publicacion el objeto {@link PublicacionDto} que contiene los datos actualizados.
     * @param vendedor el objeto {@link VendedorDto} que representa al vendedor dueño de la publicación.
     * @return true si la publicación fue actualizada exitosamente, false en caso contrario.
     */
    @Override
    public boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return false;
    }

    /**
     * Obtiene una lista de publicaciones de un muro.
     *
     * @param muro el objeto {@link Muro} que contiene las publicaciones.
     * @return una lista vacía (método no implementado).
     */
    @Override
    public List<PublicacionDto> getListaPublicaciones(Muro muro) {
        return List.of();
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param vendedor el objeto {@link VendedorDto} que representa al nuevo usuario.
     * @return true si el usuario fue creado exitosamente, false en caso contrario.
     */
    @Override
    public boolean crearUsuario(VendedorDto vendedor) {
        return marketPlace.crearUsuario((Vendedor) mapping.usuarioDtoToUsuario(vendedor));
    }

    /**
     * Agrega un mensaje a un chat existente.
     *
     * @param mensaje el objeto {@link MensajeDto} que representa el mensaje a agregar.
     * @param chat el objeto {@link ChatDto} que representa el chat donde se agregará el mensaje.
     * @return true si el mensaje fue agregado exitosamente, false en caso contrario.
     */
    @Override
    public boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat) {
        return marketPlace.agregarMensajeChat(mapping.mesajeDtoToMensaje(mensaje), mapping.chatDtoToChat(chat));
    }

    /**
     * Obtiene una lista de nuevos contactos de un vendedor.
     *
     * @param vendedor el objeto {@link VendedorDto} que representa al vendedor.
     * @return una lista de objetos {@link VendedorDto} que son nuevos contactos del vendedor.
     */
    @Override
    public List<VendedorDto> getListaContactosNuevos(VendedorDto vendedor) {
        return mapping.VendedoresToVendedoresDto(marketPlace.getListaContactosNuevos((Vendedor) mapping.usuarioDtoToUsuario(vendedor)));
    }

    /**
     * Obtiene un chat entre dos vendedores.
     *
     * @param vendedor el objeto {@link VendedorDto} que representa al usuario solicitante.
     * @param contacto el objeto {@link VendedorDto} que representa al contacto con el que se tiene el chat.
     * @return un objeto {@link ChatDto} que representa el chat entre ambos vendedores.
     */
    @Override
    public ChatDto getChat(VendedorDto vendedor, VendedorDto contacto) {
        Chat chat = marketPlace.getChat(
                (Vendedor) mapping.usuarioDtoToUsuario(vendedor),
                (Vendedor) mapping.usuarioDtoToUsuario(contacto)
        );
        return mapping.chatToChatDto(chat);
    }

    /**
     * Obtiene la lista de productos de un vendedor.
     *
     * @param id el identificador del vendedor.
     * @return una lista de objetos {@link ProductoDto} que representan los productos del vendedor.
     */
    @Override
    public List<ProductoDto> getListaProductosDto(String id) {
        return mapping.productosToProductosDto(marketPlace.getListaProductosVendedor(id));

    }

    /**
     * Obtiene una lista de contactos de un vendedor en formato DTO.
     *
     * @param id el identificador del vendedor.
     * @return una lista de objetos {@link VendedorDto} que representan los contactos del vendedor.
     */
    @Override
    public List<VendedorDto> getListaContactosDto(String id) {
        return mapping.VendedoresToVendedoresDto(marketPlace.getListaContactos(id));
    }

    @Override
    public List<Vendedor> getListaContactos(String id) {
        return marketPlace.getListaContactos(id);
    }

    @Override
    public List<Comentario> getListaComentarios(String idVendedor, PublicacionDto publicacion) {
        return marketPlace.getListaComentarios(idVendedor, publicacion.getFechaPublicacion(), publicacion.getHoraPublicacion());
    }

    @Override
    public List<ComentarioDto> getListaComentariosDto(String idVendedor, PublicacionDto publicacion) {
        return mapping.comentariosToComentariosDto(marketPlace.getListaComentarios(idVendedor,publicacion.getFechaPublicacion(), publicacion.getHoraPublicacion()));

    }

    @Override
    public List<Vendedor> getListaMeGusta(String idVendedor, PublicacionDto dto) {
        return marketPlace.getListaMeGusta(idVendedor, mapping.productoDtoToProducto(dto.getProducto()));
    }

    @Override
    public List<VendedorDto> getListaMeGustaDto(String idVendedor, PublicacionDto dto) {
        return mapping.VendedoresToVendedoresDto(marketPlace.getListaMeGusta(idVendedor, mapping.productoDtoToProducto(dto.getProducto())));
    }

    @Override
    public List<Publicacion> getListaPublicaciones(String idVendedor) {
        return List.of();
    }

    @Override
    public List<PublicacionDto> getListaPublicacionesDto(String idVendedor) {
        return mapping.publicacionesToPublicacionesDto(marketPlace.getListaPublicaciones(idVendedor));
    }

    public List<MensajeDto> getListaMensajeChat(String id) {
        return mapping.mensajeToMensajesDto(marketPlace.getMensajesChat(id));
    }

    public List<Mensaje> getListaMensajesChat(String id) {
        return marketPlace.getMensajesChat(id);
    }


    @Override
    public boolean agregarPublicacion(PublicacionDto publicacion, String idVendedor) {
        Publicacion p = new Publicacion();
        p.setDescripcion(publicacion.getDescripcion());
        p.setFechaPublicacion(publicacion.getFechaPublicacion());
        p.setIdVendedor(publicacion.getIdVendedor());
        p.setHoraPublicacion(publicacion.getHoraPublicacion());
        p.setProducto(mapping.productoDtoToProducto(publicacion.getProducto()));

        return marketPlace.crearPublicacion(p, idVendedor);
    }

    @Override
    public boolean agregarComentario(ComentarioDto comentario, PublicacionDto publicacion) {
        return marketPlace.agregarComentario(mapping.comentarioDtoToComentario(comentario), mapping.publicacionDtoToPublicacion(publicacion));
    }

    @Override
    public void darLikeComentario(ComentarioDto comentario, PublicacionDto publicacion) {
        marketPlace.darLikeComentario(mapping.comentarioDtoToComentario(comentario), mapping.publicacionDtoToPublicacion(publicacion));
    }

    @Override
    public int getLikesComentario(ComentarioDto dto, PublicacionDto publicacion) {
        return marketPlace.getLikesComentario(mapping.comentarioDtoToComentario(dto), mapping.publicacionDtoToPublicacion(publicacion));
    }

    /**
     * Se inicializan los datos quemasdos
     */
    private static void inicializarDatos() {

        MarketPlace marketPlace1 = new MarketPlace("Market");

        //Creacion de productos
        Producto producto1 = new Producto("Servicio Guardaespaldas", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/Guardaespaldas.jpg", "Servicio privado", Estado.PUBLICADO, 1000000);
        Producto producto2 = new Producto("Camiseta Local JUNIOR FC 2024", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/Camiseta Junior.png","Equipación deportiva",Estado.PUBLICADO, 299950);
        Producto producto3 = new Producto("Bicibleta usada", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/biciPro.png","Artículos usados",Estado.PUBLICADO, 20000);
        Producto producto4 = new Producto("Mano de guineo verde", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/guineitos.jpeg","Alimentos",Estado.PUBLICADO, 2000);
        Producto producto5 = new Producto("Pelota de fútbol adidas Brazuca","/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/brazuca.png", "Artículas deportivos",Estado.VENDIDO,4500000);
        Producto producto1Kit = new Producto("Bolsitas pa tu guineo", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/bolsitas.png", "Alimentos", Estado.PUBLICADO, 100);
        Producto producto2Kit = new Producto("La monda esa e corta e racimo e guineo", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/lamondaesaquecortaelracimoeguineo.png", "Alimentos", Estado.PUBLICADO, 5000);
        Kit producto6 = new Kit("Kit de cosecha de mancha e platano", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/berraqueraekit.jpeg", "Herramientas de igiene personal", Estado.PUBLICADO, 300);

        producto6.agregarProducto(producto1Kit);
        producto6.agregarProducto(producto2Kit);

        //Creacion de publicaciones
        Publicacion publicacion1 = new Publicacion(LocalDate.now(), LocalTime.now(), producto1,"Servicio privado de seguidad 5 estrellas y 24 horas. Cuenta con 2 guardaespaldas expertos en MMA para protegerlo a usted y su familia (uno de ellos se cree capaz de ganarle una pelea a Khabid). Precio negociable", "00001");
        Publicacion publicacion2 = new Publicacion(LocalDate.now().plusDays(1),LocalTime.now(),producto2,"Camiseta Local tipo jugador del mejor club de Colomboia. Disponible en todas las tallas de hombre, mujer y feministas", "00002");
        Publicacion publicacion3 = new Publicacion(LocalDate.now().plusDays(2),LocalTime.now(),producto3,"Vendo mi bicicleta con 2 semanas de uso por necesidad, le hacen falta las ruedas pero ella sirve. Precio negociable =D", "00003");
        Publicacion publicacion4 = new Publicacion(LocalDate.now().plusDays(3),LocalTime.now(),producto4,"Deliciosa mano de guineo verde perfecta pa un cayeye. 1 mano por 2k, 3 manos en 5k pa ti", "00004");
        Publicacion publicacion5 = new Publicacion(LocalDate.now().plusDays(4),LocalTime.now(),producto5,"El mejor balón que se ha creado en la historia del fútbol. Firmada por Camilo Zúñiga", "00005");
        Publicacion publicacion6 = new Publicacion(LocalDate.now(), LocalTime.now(), producto6, "Con este quit no tendras mas preocupaciones ahora podras recoger tu mancha facil y sencillo mi buen agropecunario", "00006");

        //Creacion de vendedores
        Vendedor vendedor1 = new Vendedor("Miguel", "Durant", "00001", "Cambia a diario", "MigueMC", "12345", "01");
        Vendedor vendedor2 = new Vendedor("Juan", "Chica", "00002", "Bosconia", "JuanMC", "12345", "02");
        Vendedor vendedor3 = new Vendedor("Yulieth", "Cazanova", "00003", "Frente a la U", "Yulie", "12345", "03");
        Vendedor vendedor4 = new Vendedor("Yonaiker", "Ceballos", "00004", "Atrás de la u", "Yoni", "12345", "04");
        Vendedor vendedor5 = new Vendedor("Yanki","apellido5","00005","DirecciónX","user5","12345","05");
        Vendedor vendedor6 = new Vendedor("juanki","apellido6","00006","DirecciónX","user6","12345","06");
        Vendedor vendedor7 = new Vendedor("Sofi","apellido7","00007","DirecciónX","user7","12345","07");
        Vendedor vendedor8 = new Vendedor("Juli", "apellido8","00008","DirecciónX","user8","12345","08");
        Vendedor vendedor9 = new Vendedor("Dianita","apellido9","00009","DirecciónX","user9","12345","09");
        Vendedor vendedor10 = new Vendedor("Yefri", "apellido10","000010","DirecciónX","user10","12345","10");
        Vendedor vendedor11 = new Vendedor("6","apellido11","000011","DirecciónX","user11","12345","11");

        //Likes
        publicacion1.getListaMegustas().add(vendedor2);
        publicacion1.getListaMegustas().add(vendedor3);
        publicacion1.getListaMegustas().add(vendedor4);

        publicacion2.getListaMegustas().add(vendedor5);
        publicacion2.getListaMegustas().add(vendedor6);

        publicacion3.getListaMegustas().add(vendedor7);

        //Creacion de administradores
        Administrador admin = new Administrador("Admin", "istrador", "00000", "Privada", "admin", "12345","00");

        //Creacion de comentarios
        Comentario comentario = new Comentario(vendedor4, LocalDate.now(), LocalTime.now(),"Precio?", "1");
        Comentario comentario2 = new Comentario(vendedor2,LocalDate.now().plusDays(2),LocalTime.now(),"Yo conozco donde lo venden más barato", "2");

        //(Usuario usuario, LocalDate fecha, LocalTime hora, String mensaje, String idMensaje

        //Agregar comentario a publicaciones
        publicacion4.agregarComentario(comentario);
        publicacion1.agregarComentario(comentario2);

        //Creacion de muros
        Muro muro = new Muro();
        Muro muro2 = new Muro();

        //Agregar publicaciones al muro
        muro.agregarPublicacion(publicacion1);
        muro.agregarPublicacion(publicacion2);
        muro.agregarPublicacion(publicacion3);
        muro.agregarPublicacion(publicacion4);
        muro.agregarPublicacion(publicacion5);
        muro.agregarPublicacion(publicacion6);

        //Agregar muro a un vendedor
        vendedor1.setMuro(muro);
        vendedor2.setMuro(muro2);

        //Agregar Producto a vendedor
        vendedor1.agregarProducto(producto5);
        vendedor1.agregarProducto(producto1);
        vendedor1.agregarProducto(producto2);
        vendedor1.agregarProducto(producto3);
        vendedor1.agregarProducto(producto4);

        //Relacionar contactos bilateralmente
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor2);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor3);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor4);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor5);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor6);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor7);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor8);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor9);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor10);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor11);

        //Agregar distintos objetos al marketplace automaticamente
        List<Object> parametros = Arrays.asList(vendedor1,vendedor2,admin,vendedor3,vendedor4,vendedor5,vendedor6,vendedor10,vendedor11);
        parametros.forEach(marketPlace1::agregarAutomatico);

        marketPlace = marketPlace1;
    }
}

