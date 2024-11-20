package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.controller;

import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.factory.ModelFactory;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.ChatDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.MensajeDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.mapping.dto.VendedorDto;
import co.edu.uniquindio.projectfinal.proyectofinalversionfinal.services.IServiceMensaje;

import java.util.List;

/**
 * La clase MensajeController actúa como un controlador para gestionar las operaciones
 * relacionadas con los mensajes y los chats entre vendedores.
 * Implementa la interfaz {@link IServiceMensaje} y utiliza el patrón de diseño
 * Singleton a través de {@link ModelFactory} para acceder a los datos del modelo.
 */
public class MensajeController implements IServiceMensaje {
    ModelFactory modelFactory;

    /**
     * Constructor de la clase MensajeController.
     * Inicializa la instancia de ModelFactory.
     */
    public MensajeController() {
        modelFactory = ModelFactory.getInstance();
    }

    /**
     * Obtiene un chat específico entre dos vendedores.
     *
     * @param vendedor el vendedor que inicia el chat.
     * @param contacto el contacto del vendedor con quien se realiza el chat.
     * @return un objeto {@link ChatDto} que representa el chat entre los dos vendedores.
     */
    @Override
    public ChatDto getChat(VendedorDto vendedor, VendedorDto contacto) {
        return modelFactory.getChat(vendedor, contacto);
    }

    /**
     * Obtiene la lista de mensajes de un chat específico dado su ID.
     *
     * @param id el identificador único del chat.
     * @return una lista de objetos {@link MensajeDto} que contiene los mensajes del chat.
     */
    @Override
    public List<MensajeDto> getListaMensaje(String id) {
        return modelFactory.getListaMensajeChat(id);
    }

    /**
     * Agrega un mensaje a un chat específico.
     *
     * @param mensaje el mensaje que se desea agregar, representado por {@link MensajeDto}.
     * @param chat el chat al cual se agregará el mensaje, representado por {@link ChatDto}.
     * @return un valor booleano que indica si el mensaje fue agregado exitosamente.
     */
    @Override
    public boolean agregarMensajeChat(MensajeDto mensaje, ChatDto chat) {
        return modelFactory.agregarMensajeChat(mensaje, chat);
    }

    /**
     * Elimina un mensaje de un chat en una posición específica de la lista de mensajes.
     *
     * @param index el índice del mensaje a eliminar.
     * @param chat el chat del cual se eliminará el mensaje, representado por {@link ChatDto}.
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista de mensajes.
     */
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
