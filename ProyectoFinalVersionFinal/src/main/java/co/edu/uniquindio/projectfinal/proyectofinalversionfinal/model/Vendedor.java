package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario{
    private final int maxContactos = 10;
    private String IdVendedor;
    private List<Vendedor> listaContactos;
    private List<Producto> listaProductos;
    private Muro muro;

    public Vendedor(String nombre, String apellido, String cedula, String direccion, String usuario, String password, String idVendedor) {
        super(nombre,apellido,cedula,direccion,usuario,password);
        this.IdVendedor = idVendedor;
        listaContactos = new ArrayList<Vendedor>();
        listaProductos = new ArrayList<Producto>();
        muro = new Muro();
    }
    public Vendedor(){

    }

    public boolean verificarContactoRepetido(Vendedor vendedor){
        boolean repetido = false;
        for (Vendedor vendedor1 : listaContactos){
            if(vendedor1.getIdVendedor().equals(vendedor.getIdVendedor())){
                repetido = true;
            }
        }
        return repetido;
    }

    public void agregarContacto(Vendedor vendedor) {
        if(!verificarContactoRepetido(vendedor) && listaContactos.size() < maxContactos){
            listaContactos.add(vendedor);
        }
    }

    public List<Producto> getListaProductosDisponibles(){
        ArrayList<Producto> disponibles = new ArrayList<>();

        for (Producto producto : listaProductos){
            if (producto.getEstado() == Estado.DISPONIBLE){
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public int getMaxContactos() {
        return maxContactos;
    }

    public String getIdVendedor() {
        return IdVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        IdVendedor = idVendedor;
    }

    public List<Vendedor> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Vendedor> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    public void setEstadoProducto(Producto pr) {
        for (Producto producto : listaProductos){
            if (pr.getImagen().equals(producto.getImagen())){
                producto.setEstado(Estado.PUBLICADO);
            }
        }
    }
    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
        darIdATodo();
    }

    public void darIdATodo(){
        muro.setIdVendedor(IdVendedor);
        for (Publicacion publicacion : muro.getListaPublicaciones()){
            publicacion.setIdVendedor(IdVendedor);
        }
    }
}
