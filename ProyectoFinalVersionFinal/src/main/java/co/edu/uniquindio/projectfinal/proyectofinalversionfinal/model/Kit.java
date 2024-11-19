package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Kit extends Producto{
    private List<Producto> productos;

    public Kit(String nombre, String rutaImagen, String categoria, Estado estado, double precio){
        super(nombre, rutaImagen, categoria, estado, precio);
        productos = new ArrayList<Producto>();

    }
    public void agregarProducto(Producto producto){
        productos.add(producto);
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
