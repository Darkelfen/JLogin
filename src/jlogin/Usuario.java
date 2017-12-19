/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlogin;

/**
 *
 * @author usuario
 */
public class Usuario implements serializable{
    private String usuario;
    private String nombre;
    private String mail;
    private String pass;

    public Usuario(String usuario, String nombre, String mail, String pass) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
