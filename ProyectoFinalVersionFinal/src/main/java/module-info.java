module co.edu.uniquindio.projectfinal.proyectofinalversionfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens co.edu.uniquindio.projectfinal.proyectofinalversionfinal to javafx.fxml;
    exports co.edu.uniquindio.projectfinal.proyectofinalversionfinal;
}