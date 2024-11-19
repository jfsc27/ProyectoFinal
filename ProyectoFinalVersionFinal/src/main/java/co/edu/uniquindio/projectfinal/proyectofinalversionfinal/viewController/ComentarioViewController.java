package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ComentarioDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model.Comentario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComentarioViewController {

    @FXML
    private Label labelComentario;

    @FXML
    private Label labelFecha;

    ComentarioDto comentario;

    public void setData(ComentarioDto comentario) {
        labelComentario.setText(comentario.getMensaje());
        labelFecha.setText(comentario.getFecha().toString());
        this.comentario = comentario;
    }
}
