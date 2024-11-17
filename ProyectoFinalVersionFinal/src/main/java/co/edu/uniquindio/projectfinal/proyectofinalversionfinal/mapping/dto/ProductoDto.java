package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Estado;
import javafx.scene.image.Image;

public class ProductoDto {
    private String idVendedor;
    private String nombre;
    private Image imagen;
    private String categoria;
    private Estado estado;
    private double precio;

    public ProductoDto() {}
    public ProductoDto(String nombre, Image imagen, String categoria, Estado estado, double precio, String idVendedor) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.categoria = categoria;
        this.estado = estado;
        this.precio = precio;
        this.idVendedor = idVendedor;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
}
