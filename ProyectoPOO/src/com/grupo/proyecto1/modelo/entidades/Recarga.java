package com.grupo.proyecto1.modelo.entidades;

import java.time.LocalDateTime;

public class Recarga {
    private static int contador = 1; 
    private String codigo; 
    private LocalDateTime fechaHora; 
    private int cantidadAgregada;

    public Recarga(LocalDateTime fechaHora, int cantidadAgregada) { 
        this.codigo = "R-" + (contador++); 
        this.fechaHora = fechaHora; 
        this.cantidadAgregada = cantidadAgregada; 
    }

    public String getCodigo() { return codigo; } 
    public LocalDateTime getFechaHora() { return fechaHora; } 
    public int getCantidadAgregada() { return cantidadAgregada; } 
}
    