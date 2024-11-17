package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

public class Administrador extends Usuario {
    private String IdAdmin;

    public Administrador(String nombre, String apellido, String cedula, String direccion, String usuario, String password,String IdAdmin) {
        super(nombre,apellido,cedula,direccion,usuario,password);
        this.IdAdmin = IdAdmin;
    }
    public Administrador() {}

    public String getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        IdAdmin = idAdmin;
    }
}
