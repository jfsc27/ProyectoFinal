package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private String idVendedor;

    private LocalDate fechaPublicacion;
    private LocalTime horaPublicacion;
    private String descripcion;
    private Producto producto;
    private List<Comentario> listaComentarios;
    private List<Vendedor> listaMegustas;

    public Publicacion(LocalDate fechaPublicacion, LocalTime horaPublicacion, Producto producto,String descripcion) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.producto = producto;
        listaComentarios = new ArrayList<Comentario>();
        this.descripcion = descripcion;
        listaMegustas = new ArrayList<>();
    }
    public Publicacion() {
        listaComentarios = new ArrayList<Comentario>();
        listaMegustas = new ArrayList<>();
    }

    /**
     * Metodo para agregar un comentario a la publicacion
     * @param comentario
     */
    public void agregarComentario(Comentario comentario) {
        listaComentarios.add(comentario);
    }

    /**
     * Metodo para agregar me gustas
     * @param vendedor
     */
    private void agregarMegusta(Vendedor vendedor) {
        listaMegustas.add(vendedor);
    }

    @Override
    public void agregarMeGusta(Vendedor vendedor) {
        agregarMegusta(vendedor);
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalTime getHoraPublicacion() {
        return horaPublicacion;
    }

    public void setHoraPublicacion(LocalTime horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Vendedor> getListaMegustas() {
        return listaMegustas;
    }

    public void setListaMegustas(List<Vendedor> listaMegustas) {
        this.listaMegustas = listaMegustas;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}
