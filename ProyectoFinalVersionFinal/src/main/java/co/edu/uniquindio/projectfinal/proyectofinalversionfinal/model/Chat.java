package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private final int maxUsuarios = 2;
    private String idChat;
    private Vendedor usuario1;
    private Vendedor usuario2;
    private List<Mensaje> listaMensajes;

    public Chat(String idChat) {
        this.idChat = idChat;
        listaMensajes = new ArrayList<Mensaje>();
    }
    public Chat() {
        listaMensajes = new ArrayList<Mensaje>();
    }


    public void agregarMensaje(Mensaje mensaje) {
        listaMensajes.add(mensaje);
    }
    /**
     * SECCION GETTERS Y SETTERS
     */
    public int getMaxUsuarios() {
        return maxUsuarios;
    }

    public Vendedor getUsuario1() {return usuario1;}

    public Vendedor getUsuario2() {return usuario2;}

    public void setUsuario1(Vendedor usuario1) {this.usuario1 = usuario1;}

    public void setUsuario2(Vendedor usuario2) {this.usuario2 = usuario2;}

    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }
}
