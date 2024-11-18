package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ChatDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.MensajeDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IServiceMensaje;

import java.util.List;

public class MensajeController implements IServiceMensaje {
    ModelFactory modelFactory;
    public MensajeController() {
        modelFactory = ModelFactory.getInstance();
    }

    @Override
    public ChatDto getChat(VendedorDto vendedor, VendedorDto contacto) {
        return modelFactory.getChat(vendedor, contacto);
    }
    @Override
    public List<MensajeDto> getListaMensaje(String id) {
        return modelFactory.getListaMensajeChat(id);
    }

    @Override
    public boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat) {
        return modelFactory.agregarMensajeChat(mensaje, chat);
    }

    @Override
    public void eliminarMensajeChat(int index, ChatDto chat) {
        List<MensajeDto> mensajes = getListaMensaje(chat.getId());

        if (index >= 0 && index < mensajes.size()) {
            mensajes.remove(index);
            System.out.println("Mensaje eliminado en la posición: " + index);
        } else {
            System.out.println("Índice fuera de rango: " + index);
        }
    }
}
