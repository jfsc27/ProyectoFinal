package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CasillaTopViewController {

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelNum;

    PublicacionDto publicacion;

    public void setData(PublicacionDto publicacion, String num) {
        labelNombre.setText(publicacion.getProducto().getNombre());
        labelNum.setText(num);
        this.publicacion = publicacion;
    }
}
