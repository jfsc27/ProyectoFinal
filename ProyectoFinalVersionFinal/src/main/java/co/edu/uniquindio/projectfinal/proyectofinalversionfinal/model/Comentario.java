package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Comentario extends Mensaje{
    private int numMeGustas;

    public Comentario(Usuario usuario, LocalDate fecha, LocalTime hora, String mensaje, String idMensaje) {
        super(usuario, fecha, hora, mensaje, idMensaje);
        this.numMeGustas = 0;
    }
    public Comentario(){}


    public int getNumMeGustas() {
        return numMeGustas;
    }
    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }
}
