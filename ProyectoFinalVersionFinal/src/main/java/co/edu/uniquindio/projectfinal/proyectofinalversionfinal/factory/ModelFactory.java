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

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory instance;
    private static MarketPlace marketPlace;
    private static MarketPlaceMappingImpl mapping;

    private ModelFactory() {
        inicializarDatos();
        mapping = new MarketPlaceMappingImpl();
        mapping.setModelFactory(this);
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }
    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        return validarLogin(usuario) ?
                mapping.usuarioToUsuarioDto(marketPlace.getUsuarioLogin(usuario.getUsuario(), usuario.getPassword())) :
                null;
    }
    public UsuarioDto getUsuarioPorId(String id) {
        return mapping.usuarioToUsuarioDto(marketPlace.getUsuarioPorId(id));
    }

    @Override
    public boolean validarLogin(UsuarioDto usuario) {
        if (marketPlace.verificarUsuario(usuario.getUsuario(), usuario.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public Usuario getUsuarioCompleto(UsuarioDto usuario) {
        return marketPlace.getUsuarioLogin(usuario.getUsuario(), usuario.getPassword());
    }

    @Override
    public void darMeGustaPublicacion(UsuarioDto usuario, String idVendedor, PublicacionDto dto) {
        marketPlace.darMeGustaPublicacion((Vendedor) mapping.usuarioDtoToUsuario(usuario), idVendedor, dto.getFechaPublicacion(), dto.getHoraPublicacion());
    }

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

    @Override
    public boolean eliminarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return false;
    }

    @Override
    public boolean actualizarPublicacion(PublicacionDto publicacion, VendedorDto vendedor) {
        return false;
    }

    @Override
    public List<PublicacionDto> getListaPublicaciones(Muro muro) {
        return List.of();
    }

    @Override
    public boolean crearUsuario(VendedorDto vendedor) {
        return marketPlace.crearUsuario((Vendedor) mapping.usuarioDtoToUsuario(vendedor));
    }

    @Override
    public boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat) {
        return marketPlace.agregarMensajeChat(mapping.mesajeDtoToMensaje(mensaje), mapping.chatDtoToChat(chat));
    }

    @Override
    public ChatDto getChat(VendedorDto vendedor, VendedorDto contacto) {
        Chat chat = marketPlace.getChat(
                (Vendedor) mapping.usuarioDtoToUsuario(vendedor),
                (Vendedor) mapping.usuarioDtoToUsuario(contacto)
        );
        return mapping.chatToChatDto(chat);
    }

    @Override
    public List<ProductoDto> getListaProductosDto(String id) {
        return mapping.productosToProductosDto(marketPlace.getListaProductosVendedor(id));

    }

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
        return marketPlace.getListaMeGusta(idVendedor, mapping.productoDtoToProducto(PublicacionDto.getProducto()));
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
        return List.of();
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

    private static void inicializarDatos() {

        MarketPlace marketPlace1 = new MarketPlace("Marketsito");

        //Creacion de productos
        Producto producto1 = new Producto("Mazda carro", "/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/imagenes/biciPro.png", "Vehiculos usados", Estado.PUBLICADO, 40000000);
        Producto producto2 = new Producto("Nintendo Switch", "","Consolas de video",Estado.PUBLICADO, 500000);
        Producto producto3 = new Producto("Closet de dos puertas", "","Muebles para el hogar",Estado.PUBLICADO, 450000);
        Producto producto4 = new Producto("Iphone 25", "","Celulares",Estado.PUBLICADO, 450000);
        Producto producto5 = new Producto("Moto cualquiera","", "Vehiculos",Estado.DISPONIBLE,2500000);

        //Creacion de publicaciones
        Publicacion publicacion = new Publicacion(LocalDate.now(), LocalTime.now(), producto1,"Flamante vehiculo mazda dos dias de uso, mas informacion al interno");
        Publicacion publicacion2 = new Publicacion(LocalDate.now().plusDays(1),LocalTime.now(),producto2,"Espectacular consola de video, nintendo porfavor no me demandes, mas informacion al interno");
        Publicacion publicacion3 = new Publicacion(LocalDate.now().plusDays(2),LocalTime.now(),producto3,"Closet de dos puertas, de 3 metros de alto y 1,5 de ancho, mdf, mas info al interno");
        Publicacion publicacion4 = new Publicacion(LocalDate.now().plusDays(3),LocalTime.now(),producto4,"Unico en el mundo, iphone 25, extraido de los laboratorios de apple, promosion del 10% al primer interesado, precio real: 1 billon de dolars");

        //Creacion de vendedores
        Vendedor vendedor1 = new Vendedor("Pepe", "Bermuda", "00001", "Alli al lado", "pepito07", "1234", "01");
        Vendedor vendedor2 = new Vendedor("Gustava", "Santos", "00002", "Alli al otro lado", "Pollos08", "12345", "02");
        Vendedor vendedor3 = new Vendedor("Martin", "Santos", "00003", "Alli", "user01", "00001", "03");
        Vendedor vendedor4 = new Vendedor("Pedro", "Martinez", "00004", "Alli", "user02", "00002", "04");
        Vendedor vendedor5 = new Vendedor("vendedor5","apellido5","00005","Alli","user05","00005","05");
        Vendedor vendedor6 = new Vendedor("vendedor6","apellido6","00006","Alli","user06","00006","06");
        Vendedor vendedor7 = new Vendedor("vendedor7","apellido7","00007","Alli","user07","00007","07");
        Vendedor vendedor8 = new Vendedor("vendedor8", "apellido8","00008","Alli","user08","00008","08");
        Vendedor vendedor9 = new Vendedor("vendedor9","apellido9","00009","Alli","user09","00009","09");
        Vendedor vendedor10 = new Vendedor("vendedor10", "apellido10","000010","Alli","user10","000010","10");
        Vendedor vendedor11 = new Vendedor("vendedor11","apellido11","000011","Alli","user11","000011","11");

        //Creacion de administradores
        Administrador admin = new Administrador("Admin", "admin", "00000", "desconocida", "admin", "admin","00");

        //Creacion de comentarios
        Comentario comentario = new Comentario(vendedor4,LocalDate.now(),LocalTime.now(),"JAJAJAJAJ el parcerito mas alucin","0");
        Comentario comentario2 = new Comentario(vendedor2,LocalDate.now().plusDays(2),LocalTime.now(),"Esta feo","1");
        Comentario comentario3 = new Comentario(vendedor1, LocalDate.now().plusDays(3),LocalTime.now(),"Un saludo","2");
        Comentario comentario4 = new Comentario(vendedor3,LocalDate.now().plusDays(4),LocalTime.now(),"Gas","3");

        //Creacion de chats
        Chat chat1 = new Chat("01");
        chat1.setUsuario1(vendedor1);
        chat1.setUsuario2(vendedor2);


        //Mensajes
        Mensaje mensaje1 = new Mensaje(vendedor1, LocalDate.now().minusDays(5), LocalTime.now().minusHours(2), "Hola holaaa", "01");
        Mensaje mensaje2 = new Mensaje(vendedor1, LocalDate.now().minusDays(5), LocalTime.now().minusHours(2).plusMinutes(2), "Como estas?", "02");
        Mensaje mensaje3 = new Mensaje(vendedor1, LocalDate.now().minusDays(5), LocalTime.now().minusHours(2).plusMinutes(5), "Que pena molestar, tu sabes a que horas es mañana la induccion?", "03");
        Mensaje mensaje4 = new Mensaje(vendedor1, LocalDate.now().minusDays(5), LocalTime.now().minusHours(1), "Hey que tal, no me acuerdo, espera busco donde anote la hora", "04");
        Mensaje mensaje5 = new Mensaje(vendedor1, LocalDate.now().minusDays(5), LocalTime.now().minusHours(1).plusMinutes(5), "Okey okey", "05");

        //Agregar mensajes al chat
        chat1.agregarMensaje(mensaje1);
        chat1.agregarMensaje(mensaje2);
        chat1.agregarMensaje(mensaje3);
        chat1.agregarMensaje(mensaje4);
        chat1.agregarMensaje(mensaje5);

        //Agregar comentario a publicaciones
        publicacion4.agregarComentario(comentario);
        publicacion.agregarComentario(comentario2);
        publicacion2.agregarComentario(comentario3);
        publicacion3.agregarComentario(comentario4);

        //Agregar interaccion a una publicacion
        publicacion4.agregarMeGusta(vendedor1);
        publicacion.agregarMeGusta(vendedor2);

        //Creacion de muros
        Muro muro = new Muro();
        Muro muro2 = new Muro();

        //Agregar publicaciones al muro
        muro.agregarPublicacion(publicacion);
        muro.agregarPublicacion(publicacion2);
        muro.agregarPublicacion(publicacion3);
        muro2.agregarPublicacion(publicacion4);

        muro.getListaChats().add(chat1);
        muro2.getListaChats().add(chat1);
        //Agregar muro a un vendedor
        vendedor1.setMuro(muro);
        vendedor2.setMuro(muro2);

        //Agregar Producto a vendedor
        vendedor1.agregarProducto(producto5);
        vendedor1.agregarProducto(producto1);
        vendedor1.agregarProducto(producto2);
        vendedor1.agregarProducto(producto3);
        vendedor2.agregarProducto(producto4);

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
        List<Object> parametros = Arrays.asList(vendedor1,vendedor2,admin);
        parametros.forEach(marketPlace1::agregarAutomatico);

        //Agregar chat al mur0

        marketPlace = marketPlace1;
    }
}

