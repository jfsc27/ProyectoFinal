package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

public class AdministradorDto extends UsuarioDto{
    private String IdAdmin;

    public AdministradorDto(String nombre, String apellido, String cedula, String direccion, String usuario, String password,String IdAdmin) {
        super(nombre, apellido, cedula, direccion, usuario, password);
        this.IdAdmin = IdAdmin;
    }

    public String getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        IdAdmin = idAdmin;
    }
}
