package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.ICrudPublicacion;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IInteraccionEntreContactos;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IPublicacionDecorator;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements IInteraccionEntreContactos, ICrudPublicacion {
    private String nombre;
    private List<Administrador> listaAdministradores;
    private List<Usuario> listaUsuarios;
    private List<Vendedor> listaVendedores;

    public MarketPlace(String nombre) {
        this.nombre = nombre;
        this.listaAdministradores = new ArrayList<Administrador>();
        this.listaUsuarios = new ArrayList<Usuario>();
        this.listaVendedores = new ArrayList<Vendedor>();
    }

    public MarketPlace() {

    }

    public void aplicarDescuento(String idVendedor, String idPublicacion){
        for (Vendedor vendedor : listaVendedores){
            if (vendedor.getIdVendedor().equals(idVendedor)){
                for(Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()){
                    if (publicacion.getIdPublicacion().equals(idPublicacion)){
                        IPublicacionDecorator iPublicacionDecorator = publicacion;
                        iPublicacionDecorator = (IPublicacionDecorator) new DescuentoDecorator(iPublicacionDecorator);
                        publicacion.setDescripcion(iPublicacionDecorator.getDescripcion());
                    }
                }
            }
        }
    }
    public <T> void agregarAutomatico(T objeto){
        if (objeto instanceof Usuario){
            listaUsuarios.add((Usuario)objeto);
            if(objeto instanceof Administrador){
                listaAdministradores.add((Administrador)objeto);
            }else if(objeto instanceof Vendedor) {
                listaVendedores.add((Vendedor)objeto);
            }
        }
    }

    public Usuario getUsuarioLogin(String usuario, String password) {
        if (verificarUsuario(usuario, password)) {
            for (Usuario usuario1 : listaUsuarios) {
                if (usuario1.getUsuario().equals(usuario) && usuario1.getPassword().equals(password)) {
                    return usuario1;
                }
            }
        }
        return null;
    }

    public List<Mensaje> getMensajesChat(String id) {
        for (Vendedor vendedor : listaVendedores) {
            for (Chat chat : vendedor.getMuro().getListaChats()) {
                if (chat.getIdChat().equals(id)) {
                    return chat.getListaMensajes();
                }
            }
        }
        return null;
    }

    public Usuario getUsuarioPorId(String id){
        for (Vendedor vendedor : listaVendedores){
            if (vendedor.getIdVendedor().equals(id)){
                return vendedor;
            }
        }
        return null;
    }

    public Usuario getUsuario(String usuario, String password){
        if (verificarUsuario(usuario, password)){
            for (Usuario usuario1 : listaUsuarios){
                if (usuario1.getUsuario().equals(usuario) && usuario1.getPassword().equals(password)){
                    return usuario1;
                }
            }
        }
        return null;
    }

    public boolean verificarUsuario(String usuario, String password) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Chat getChat(Vendedor vendedor, Vendedor contacto) {
        for(Vendedor v : listaVendedores){
            if(v.getIdVendedor().equals(vendedor.getIdVendedor())){
                for (Chat c : v.getMuro().getListaChats()){
                    if (c.getUsuario1().getIdVendedor().equals(vendedor.getIdVendedor()) && c.getUsuario2().getIdVendedor().equals(contacto.getIdVendedor())
                            || c.getUsuario1().getIdVendedor().equals(contacto.getIdVendedor()) && c.getUsuario2().getIdVendedor().equals(vendedor.getIdVendedor())){
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public List<Producto> getListaProductosVendedor(String id) {
        List<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getIdVendedor().equals(id)) {
                productos = vendedor.getListaProductos();
            }
        }
        return productos;
    }

    public List<Vendedor> getListaContactos(String id) {
        List<Vendedor> contactos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getIdVendedor().equals(id)) {
                contactos = vendedor.getListaContactos();
            }
        }
        return contactos;
    }

    public List<Comentario> getListaComentariosGenerales(String idVendedor) {
        List<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getIdVendedor().equals(idVendedor)) {
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()) {
                    if (publicacion.getIdVendedor().equals(idVendedor)) {
                        comentarios.addAll(publicacion.getListaComentarios());
                    }
                }
            }
        }
        return comentarios;
    }

    public List<Comentario> getListaComentarios(String idVendedor, LocalDate fecha, LocalTime hora) {
        // Creamos una lista para almacenar los comentarios encontrados
        List<Comentario> comentarios = new ArrayList<>();

        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Verificamos si el ID del vendedor coincide con el proporcionado
            if (vendedor.getIdVendedor().equals(idVendedor)) {
                // Recorremos las publicaciones del vendedor
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()) {
                    // Comprobamos si la fecha y la hora de la publicación coinciden
                    if (p.getFechaPublicacion().equals(fecha) && p.getHoraPublicacion().equals(hora)) {
                        // Si coincide, obtenemos los comentarios de esa publicación
                        comentarios = p.getListaComentarios();
                        break; // Salimos del bucle ya que hemos encontrado los comentarios
                    }
                }
            }
        }

        // Retornamos la lista de comentarios
        return comentarios;
    }
    public List<Vendedor> getListaContactosNuevos(Vendedor vendedor) {
        List<Vendedor> contactos = new ArrayList<>();
        contactos.add(vendedor);
        List<Vendedor> nuevos = new ArrayList<>();

        for (Vendedor v : listaVendedores) {
            if (v.getIdVendedor().equals(vendedor.getIdVendedor())) {
                contactos = v.getListaContactos();
            }
        }
        for (Vendedor v2 : listaVendedores) {
            if(!v2.getIdVendedor().equals(vendedor.getIdVendedor())) {
                nuevos.add(v2);
            }
        }
        return nuevos;
    }
    public List<Vendedor> getListaMeGusta(String idVendedor, Producto producto) {
        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Verificamos si el ID del vendedor coincide con el proporcionado
            if (vendedor.getIdVendedor().equals(idVendedor)) {
                // Recorremos las publicaciones del vendedor
                for (Publicacion publicacion : vendedor.getMuro().getListaPublicaciones()) {
                    // Comprobamos si la imagen del producto de la publicación coincide con la imagen del producto proporcionado
                    if (publicacion.getProducto().getImagen().equals(producto.getImagen())) {
                        // Si coincide, retornamos la lista de vendedores que han dado "me gusta" a la publicación
                        return publicacion.getListaMegustas();
                    }
                }
            }
        }

        // Si no se encuentra la publicación o los "me gusta", retornamos null
        System.out.println("No se encontró la publicación o los 'me gusta'.");
        return null;
    }

    public List<Publicacion> getListaPublicaciones(String idVendedor) {
        // Creamos una lista vacía para almacenar las publicaciones
        List<Publicacion> publicaciones = new ArrayList<>();

        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Comprobamos si el ID del vendedor coincide con el proporcionado
            if (vendedor.getIdVendedor().equals(idVendedor)) {
                // Recorremos las publicaciones del muro del vendedor
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()) {
                    // Si el ID de la publicación coincide con el del vendedor, la agregamos a la lista
                    if (p.getIdVendedor().equals(idVendedor)) {
                        publicaciones.add(p);
                    }
                }
            }
        }
        // Retornamos la lista de publicaciones (vacía si no se encuentran publicaciones)
        return publicaciones;
    }

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }
    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean crearPublicacion(Publicacion publicacion, String id) {
        // Primero verificamos si la publicación ya existe
        if (!verificarPublicacionExiste(publicacion)) {
            // Si la publicación no existe, buscamos al vendedor con el id proporcionado
            for (Vendedor vendedor : listaVendedores) {
                if (vendedor.getIdVendedor().equals(id)) {
                    // Establecemos el estado del producto para el vendedor
                    vendedor.setEstadoProducto(publicacion.getProducto());

                    // Agregamos la publicación al muro del vendedor
                    vendedor.getMuro().agregarPublicacion(publicacion);

                    // Retornamos true para indicar que la publicación fue creada exitosamente
                    return true;
                }
            }
        }
        // Si la publicación ya existe o el vendedor no fue encontrado, retornamos false
        return false;
    }

    public boolean crearUsuario(Vendedor vendedor){
        if (vendedor !=null){
            listaVendedores.add(vendedor);
            listaUsuarios.add(vendedor);
            return true;
        }else {
            return false;
        }

    }

    public boolean agregarComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        p.agregarComentario(comentario);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void darLikeComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        for (Comentario c : p.getListaComentarios()){
                            if (c.getHora().equals(comentario.getHora()) && c.getFecha().equals(comentario.getFecha())){
                                c.setNumMeGustas(c.getNumMeGustas() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getLikesComentario(Comentario comentario, Publicacion publicacion){
        for (Vendedor v : listaVendedores){
            if (v.getIdVendedor().equals(publicacion.getIdVendedor())){
                for (Publicacion p : v.getMuro().getListaPublicaciones()){
                    if (p.getFechaPublicacion().equals(publicacion.getFechaPublicacion())
                            && p.getHoraPublicacion().equals(publicacion.getHoraPublicacion())){
                        for (Comentario c : p.getListaComentarios()){
                            if (c.getHora().equals(comentario.getHora()) && c.getFecha().equals(comentario.getFecha())){
                                return c.getNumMeGustas();
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean eliminarPublicacion(Publicacion publicacion, Vendedor vendedor) {
        return false;
    }

    @Override
    public boolean actualizarPublicacion(Publicacion publicacion, Vendedor vendedor) {
        return false;
    }

    @Override
    public void agregarContactosEntreSi(Vendedor contacto1, Vendedor contacto2) {
        contacto1.agregarContacto(contacto2);
        contacto2.agregarContacto(contacto1);
    }

    @Override
    public void darMeGustaPublicacion(Vendedor usuario, String id, LocalDate fecha, LocalTime hora) {
        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Comprobamos si el ID del vendedor coincide con el proporcionado
            if (vendedor.getIdVendedor().equals(id)) {
                // Recorremos las publicaciones del vendedor
                for (Publicacion p : vendedor.getMuro().getListaPublicaciones()) {
                    // Verificamos si la fecha y hora de la publicación coinciden con los parámetros dados
                    if (p.getFechaPublicacion().equals(fecha) && p.getHoraPublicacion().equals(hora)) {
                        // Si coinciden, se agrega el "me gusta" de parte del vendedor
                        p.agregarMeGusta(usuario);
                        break;  // Salimos del bucle porque ya se agregó el "me gusta"
                    }
                }
            }
        }
    }

    public boolean verificarProductoExiste(Producto producto) {
        // Lista que contendrá todos los productos de todos los vendedores
        List<Producto> productos = new ArrayList<>();

        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Recorremos la lista de productos de cada vendedor
            for (Producto p : vendedor.getListaProductos()) {
                productos.add(p); // Añadimos el producto a la lista general
            }
        }

        // Comparamos la imagen del producto dado con la imagen de los productos existentes
        for (Producto p : productos) {
            if (p.getImagen().equals(producto.getImagen())) { // Usamos .equals() para comparar el contenido
                return true; // Si se encuentra una imagen igual, retornamos true
            }
        }

        // Si no se encuentra el producto con la misma imagen, retornamos false
        return false;
    }

    public boolean verificarPublicacionExiste(Publicacion publicacion) {
        // Lista que contendrá todas las publicaciones de todos los vendedores
        List<Publicacion> publicaciones = new ArrayList<>();

        // Recorremos la lista de vendedores
        for (Vendedor vendedor : listaVendedores) {
            // Añadimos todas las publicaciones del muro del vendedor a la lista
            publicaciones.addAll(vendedor.getMuro().getListaPublicaciones());
        }

        // Comparamos el producto de la publicación dada con los productos de las publicaciones existentes
        for (Publicacion p : publicaciones) {
            // Usamos .equals() para comparar el contenido del producto
            if (p.getProducto().equals(publicacion.getProducto())) {
                return true; // Si se encuentra un producto igual, retornamos true
            }
        }

        // Si no se encuentra la publicación con el mismo producto, retornamos false
        return false;
    }

    public boolean agregarMensajeChat(Mensaje mensaje, Chat chat){
        for (Vendedor vendedor: listaVendedores){
            for (Chat c : vendedor.getMuro().getListaChats()){
                if (c.getIdChat().equals(chat.getIdChat())){
                    c.agregarMensaje(mensaje);
                    return true;
                }
            }
        }
        return false;
    }


}
