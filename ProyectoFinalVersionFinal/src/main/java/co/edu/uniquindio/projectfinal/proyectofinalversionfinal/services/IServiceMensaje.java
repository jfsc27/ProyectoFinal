package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ChatDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.MensajeDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;

import java.util.List;

public interface IServiceMensaje {
    ChatDto getChat(VendedorDto vendedor, VendedorDto contacto);
    List<MensajeDto> getListaMensaje(String id);
    boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat);
    void eliminarMensajeChat(int posicion, ChatDto chat);

}
