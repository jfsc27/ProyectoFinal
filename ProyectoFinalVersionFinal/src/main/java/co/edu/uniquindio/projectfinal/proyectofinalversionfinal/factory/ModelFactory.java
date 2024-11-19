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
        Producto producto1 = new Producto("Servicio Guardaespaldas", "", "Servicio privado", Estado.PUBLICADO, 200);
        Producto producto2 = new Producto("Camiseta Local JUNIOR FC 2024", "","Equipación deportiva",Estado.PUBLICADO, 299950);
        Producto producto3 = new Producto("Bicibleta usada", "","Artículos usados",Estado.PUBLICADO, 20000);
        Producto producto4 = new Producto("Mano de guineo verde", "","Alimentos",Estado.PUBLICADO, 2000);
        Producto producto5 = new Producto("Pelota de fútbol adidas Brazuca","", "Artículas deportivos",Estado.VENDIDO,4500000);

        //Creacion de publicaciones
        Publicacion publicacion1 = new Publicacion(LocalDate.now(), LocalTime.now(), producto1,"Servicio privado de seguidad 5 estrellas y 24 hpras. Cuenta con 2 guardaespaldas expertos en MMA para protegerlo a usted y su familia (uno de ellos se cree capaz de ganarle una pelea a Khabid). Precio negociable", "00001");
        Publicacion publicacion2 = new Publicacion(LocalDate.now().plusDays(1),LocalTime.now(),producto2,"Camiseta Local tipo jugador del mejor club de Colomboia. Disponible en todas las tallas de hombre, mujer y feministas", "00002");
        Publicacion publicacion3 = new Publicacion(LocalDate.now().plusDays(2),LocalTime.now(),producto3,"Vendo mi bicicleta con 2 semanas de uso por necesidad, le hacen falta las ruedas pero ella sirve. Precio negociable =D", "00003");
        Publicacion publicacion4 = new Publicacion(LocalDate.now().plusDays(3),LocalTime.now(),producto4,"Deliciosa mano de guineo verde perfecta pa un cayeye. 1 mano por 2k, 3 manos en 5k pa ti", "00004");
        Publicacion publicacion5 = new Publicacion(LocalDate.now().plusDays(4),LocalTime.now(),producto5,"El mejor balón que se ha creado en la historia del fútbol. Firmada por Camilo Zúñiga", "00005");

        //Creacion de vendedores
        Vendedor vendedor1 = new Vendedor("Miguel", "Durant", "00001", "Cambia a diario", "MigueMC", "12345", "01");
        Vendedor vendedor2 = new Vendedor("Juan", "Chica", "00002", "Bosconia", "JuanMC", "12345", "02");
        Vendedor vendedor3 = new Vendedor("Yulieth", "Cazanova", "00003", "Frente a la U", "Yulie", "12345", "03");
        Vendedor vendedor4 = new Vendedor("Yonaiker", "Ceballos", "00004", "Atrás de la u", "Yoni", "12345", "04");
        Vendedor vendedor5 = new Vendedor("vendedor5","apellido5","00005","DirecciónX","user5","12345","05");
        Vendedor vendedor6 = new Vendedor("vendedor6","apellido6","00006","DirecciónX","user6","12345","06");
        Vendedor vendedor7 = new Vendedor("vendedor7","apellido7","00007","DirecciónX","user7","12345","07");
        Vendedor vendedor8 = new Vendedor("vendedor8", "apellido8","00008","DirecciónX","user8","12345","08");
        Vendedor vendedor9 = new Vendedor("vendedor9","apellido9","00009","DirecciónX","user9","12345","09");
        Vendedor vendedor10 = new Vendedor("vendedor10", "apellido10","000010","DirecciónX","user10","12345","10");
        Vendedor vendedor11 = new Vendedor("vendedor11","apellido11","000011","DirecciónX","user11","12345","11");

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
        muro2.agregarPublicacion(publicacion4);

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

        marketPlace = marketPlace1;
    }
}

