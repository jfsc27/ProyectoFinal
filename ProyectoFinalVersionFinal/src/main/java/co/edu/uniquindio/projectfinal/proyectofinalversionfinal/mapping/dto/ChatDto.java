package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

public class ChatDto {
    private String id;
    private final int maxUsuarios = 2;

    public ChatDto(){
        this.id = id;
    }

    public int getMaxUsuarios() {
        return maxUsuarios;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
