package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.viewController;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.MensajeController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.MuroController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.PublicacionController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller.UsuarioController;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.PublicacionDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginaPrincipalViewController {
    ModelFactory modelFactory;
    VendedorDto vendedor;
    PublicacionController publicacionController;
    UsuarioController usuarioController;
    MuroController muroController;
    MensajeController mensajeController;


    public void inicializarPaginaPrincipal(VendedorDto vendedor) throws IOException {
        modelFactory = ModelFactory.getInstance();
        publicacionController = new PublicacionController();
        usuarioController = new UsuarioController();
        muroController = new MuroController();
        mensajeController = new MensajeController();
        this.vendedor = vendedor;

        inicializarMarketPlace();
        inicializarContactos();
        inicializarEstadisticas();
    }
    /**
     * ///////////////////////////////////////  TAB MARKETPLACE  /////////////////////////////////////////////////////////
     */
    @FXML
    private Tab tabMarketPlace;

    @FXML
    private GridPane gridProductos;

    @FXML
    private GridPane gridSugerencias;

    /**
     * Metodo para inicializar la pestana de marketplace
     */
    public void inicializarMarketPlace() throws IOException {
        llenarProductos();
        llenarSugerencias();
    }

    public void llenarProductos() throws IOException {
        int columnas = 0;
        int filas = 0;
        gridProductos.getChildren().clear();
        List<PublicacionDto> publicaciones = muroController.getListaPublicaciones(vendedor);
        for (int i = 0; i < publicaciones.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/publicacion-view.fxml"));
            AnchorPane pane = loader.load();

            PublicacionViewController controller = loader.getController();
            controller.setData(publicaciones.get(i));

            gridProductos.add(pane,columnas,filas);
            filas++;
        }
    }

    public void llenarSugerencias() throws IOException {
        int columnas = 0;
        int filas = 0;
        gridSugerencias.getChildren().clear();
        List<VendedorDto> sugerencias = usuarioController.getContactosNuevos(vendedor);
        for (int i = 0; i < sugerencias.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/contacto-view.fxml"));
            AnchorPane pane = loader.load();

            ContactoViewController controller = loader.getController();
            controller.setData(sugerencias.get(i));

            gridSugerencias.add(pane,columnas,filas);
            filas++;
        }
    }
    /**
     * ///////////////////////////////////////  TAB CONTACTO  /////////////////////////////////////////////////////////
     */
    @FXML
    private Tab tabContactos;

    @FXML
    private GridPane gridContacto;

    public void inicializarContactos() throws IOException {
        llenarContactos();
    }

    public void llenarContactos() throws IOException {
        int columnas = 0;
        int filas = 0;
        gridContacto.getChildren().clear();
        List<VendedorDto> contactos = usuarioController.getListaContactos(vendedor);

        for (int i = 0; i < contactos.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/contacto-view.fxml"));
            AnchorPane pane = loader.load();

            ContactoViewController controller = loader.getController();
            controller.setData(contactos.get(i));

            gridContacto.add(pane,columnas,filas);
            filas++;
        }
    }
    /**
     * ///////////////////////////////////////  TAB ESTADISTICA  /////////////////////////////////////////////////////////
     */
    @FXML
    private Tab tabEstadisticas;

    @FXML
    private Button btnReporte;

    @FXML
    private Label labelCantContactos;

    @FXML
    private Label labelCantLikes;

    @FXML
    private Label labelCantProductos;

    @FXML
    private Label labelPrecioPro;

    @FXML
    private GridPane gridPopulares;

    public void inicializarEstadisticas() throws IOException {
        llenarTop(getTop());
        llenarDatos();
    }

    public void llenarDatos(){
        int cont = 0;
        labelCantContactos.setText(String.valueOf(usuarioController.getListaContactos(vendedor).size()));
        for (PublicacionDto p : muroController.getListaPublicaciones(vendedor)) {
            cont += publicacionController.getListaMeGustas(vendedor.getIdVendedor(), p).size();
        }
        labelCantLikes.setText(String.valueOf(cont));
        labelCantProductos.setText(String.valueOf(usuarioController.getListaProductosDisponibles(vendedor).size()));
        labelPrecioPro.setText("9000");
    }

    public void llenarTop(List<PublicacionDto> top) throws IOException {
        int columnas = 0;
        int filas = 0;
        gridPopulares.getChildren().clear();

        for (int i = 0; i < 11; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/projectfinal/proyectofinalversionfinal/casilla-top-view.fxml"));
            AnchorPane pane = loader.load();

            CasillaTopViewController controller = loader.getController();
            if (i<top.size()){
                controller.setData(top.get(i), String.valueOf(publicacionController.getListaMeGustas(top.get(i).getIdVendedor(), top.get(i)).size()));

                gridPopulares.add(pane,columnas,filas);
                filas++;
            }

        }
    }

    public List<PublicacionDto> getTop(){
        Map<PublicacionDto, Integer> publicaciones = new HashMap<>();
        for (PublicacionDto p : muroController.getListaPublicaciones(vendedor)) {
            int cont= publicacionController.getListaMeGustas(vendedor.getIdVendedor(), p).size();
            publicaciones.put(p, cont);
        }
        return publicaciones.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
    @FXML
    void clickReporte(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar informe de estadisticas");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt"));

        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(generarReporteEstadisticas());
                System.out.println("Reporte exportado correctamente!");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El reporte no se pudo exportar");
        }
    }
    public String generarReporteEstadisticas(){
        String texto = "//////////////////////////////// REPORTE DE ESTADISTICAS //////////////////////////////////////\n";
        texto       += "Cantidad de productos publicados: " + labelCantProductos.getText() + "\n";
        texto       += "Precio del producto mas caro: " + labelPrecioPro.getText()+ "\n";
        texto       += "Cantidad de total de likes: " + labelCantLikes.getText()+ "\n";
        texto       += "Cantidad de contactos: " + labelCantContactos.getText()+ "\n";
        return texto;
    }
}
