package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MensajeDto {
    private UsuarioDto usuario;
    private LocalDate fecha;
    private LocalTime hora;
    private String mensaje;

    public MensajeDto(UsuarioDto usuario , LocalDate fecha , LocalTime hora , String mensaje) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }

    public MensajeDto() {}

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
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
