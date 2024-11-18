package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

public class ChatDto {
    private String id;
    private final int maxUsuarios = 2;
    private VendedorDto usuario1;
    private VendedorDto usuario2;

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

    public VendedorDto getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(VendedorDto usuario1) {
        this.usuario1 = usuario1;
    }

    public VendedorDto getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(VendedorDto usuario2) {
        this.usuario2 = usuario2;
    }
}
