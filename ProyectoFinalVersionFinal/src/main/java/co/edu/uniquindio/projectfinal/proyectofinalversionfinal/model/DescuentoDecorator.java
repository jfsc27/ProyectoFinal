package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IPublicacionDecorator;

public class DescuentoDecorator extends PublicacionDecorator {
    public DescuentoDecorator(IPublicacionDecorator iPublicacionDecorator) {
        super(iPublicacionDecorator);
    }

    @Override
    public String getDescripcion() {
        return iPublicacionDecorator.getDescripcion()+"Se aplica un descuento del 50%";
    }

}
