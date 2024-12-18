package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;

public class PublicacionViewController {

    @FXML
    private Label LabelDescripcion;

    @FXML
    private Label LabelFecha;

    @FXML
    private Label LabelNombreProducto;

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label LabelPrecio;


    PublicacionDto publicacion;


    public void setData(PublicacionDto publicacion) {
        LabelDescripcion.setText(publicacion.getDescripcion());
        LabelFecha.setText(publicacion.getFechaPublicacion().toString());
        LabelNombreProducto.setText(publicacion.getProducto().getNombre());
        imgProducto.setImage(publicacion.getProducto().getImagen());
        LabelPrecio.setText(String.valueOf(publicacion.getProducto().getPrecio()));

        this.publicacion = publicacion;
    }

}
