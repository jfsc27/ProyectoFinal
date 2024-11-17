package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Publicacion;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Vendedor;

public interface ICrudPublicacion {
    boolean crearPublicacion(Publicacion publicacion, String id);
    boolean eliminarPublicacion(Publicacion publicacion, Vendedor vendedor);
    boolean actualizarPublicacion(Publicacion publicacion, Vendedor vendedor);
}
