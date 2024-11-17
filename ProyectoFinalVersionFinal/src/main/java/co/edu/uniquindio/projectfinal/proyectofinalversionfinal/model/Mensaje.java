package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Mensaje {
    private String idMensaje;
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime hora;
    private String mensaje;

    public Mensaje(Usuario usuario, LocalDate fecha, LocalTime hora, String mensaje, String idMensaje) {
        this.idMensaje = idMensaje;
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }
    public Mensaje() {}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
