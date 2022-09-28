package com.example.testegraficos;

public class Dado {

    private int id;
    private String data;
    private String categoria;
    private String valor;
    private String turno;

    public Dado(int id, String data, String categoria, String valor, String turno) {
        this.id = id;
        this.data = data;
        this.categoria = categoria;
        this.valor = valor;
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getValor() {
        return valor;
    }

    public String getTurno() {
        return turno;
    }
}
