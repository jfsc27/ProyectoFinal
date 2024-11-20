package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IPublicacionDecorator;

public class PublicacionDecorator {
    protected IPublicacionDecorator iPublicacionDecorator;
     public PublicacionDecorator(IPublicacionDecorator iPublicacionDecorator) {
         this.iPublicacionDecorator = iPublicacionDecorator;
     }
     public String getDescripcion() {
         return iPublicacionDecorator.getDescripcion();
     }

}
