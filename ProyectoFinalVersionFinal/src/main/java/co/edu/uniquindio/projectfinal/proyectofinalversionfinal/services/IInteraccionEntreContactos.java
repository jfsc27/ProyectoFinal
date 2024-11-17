package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Vendedor;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IInteraccionEntreContactos {
    public void agregarContactosEntreSi(Vendedor contacto1, Vendedor contacto2);
    public void darMeGustaPublicacion(Vendedor usuario, String id, LocalDate fecha, LocalTime hora);
}
