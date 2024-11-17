package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComentarioDto extends MensajeDto {
    private int numMeGustas;

    public ComentarioDto(UsuarioDto usuario , LocalDate fecha , LocalTime hora , String mensaje ) {
        super(usuario,fecha,hora,mensaje);
        this.numMeGustas = 0;
    }
    public ComentarioDto() {
        super();
        this.numMeGustas = 0;
    }

    public int getNumMeGustas() {
        return numMeGustas;
    }

    public void setNumMeGustas(int numMeGustas) {
        this.numMeGustas = numMeGustas;
    }
}
