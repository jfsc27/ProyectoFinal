package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ContactoViewController {
    @FXML
    private Label labelNombreContacto;

    VendedorDto vendedor;

    public void setData(VendedorDto vendedor) {
        labelNombreContacto.setText(vendedor.getNombre());
        this.vendedor = vendedor;
    }
}
