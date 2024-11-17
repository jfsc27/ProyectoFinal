package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto;

public class VendedorDto extends UsuarioDto{
    private String IdVendedor;
    private MuroDto muro;

    public VendedorDto(String nombre, String apellido, String cedula, String direccion, String usuario, String password, String idVendedor) {
        super(nombre, apellido, cedula, direccion, usuario, password);
        this.IdVendedor = idVendedor;
    }
    public VendedorDto(){
    }

    public String getIdVendedor() {return IdVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        IdVendedor = idVendedor;
    }

    public MuroDto getMuro() {
        return muro;
    }

    public void setMuro(MuroDto muro) {
        this.muro = muro;
    }
}
