package co.edu.uniquindio.projectfinal.proyectofinalversionfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private final int maxUsuarios = 2;
    private String idChat;
    private List<Vendedor> listaUsuarios;
    private List<Mensaje> listaMensajes;

    public Chat(String idChat, Vendedor vendedor1, Vendedor vendedor2) {
        this.idChat = idChat;
        listaUsuarios = new ArrayList<Vendedor>();
        listaMensajes = new ArrayList<Mensaje>();
    }
    public void agregarUsuario(Vendedor usuario) {
        if (listaUsuarios.size() < maxUsuarios) {
            listaUsuarios.add(usuario);
        }
    }
    public void agregarMensaje(Mensaje mensaje) {
        listaMensajes.add(mensaje);
    }



    public int getMaxUsuarios() {
        return maxUsuarios;
    }

    public List<Vendedor> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Vendedor> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

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
