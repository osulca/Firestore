package com.example.appfirestore;

public class Usuario {
    private String username;
    private String password;
    private String nombres;

    public Usuario(String username, String password, String nombres) {
        this.username = username;
        this.password = password;
        this.nombres = nombres;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
