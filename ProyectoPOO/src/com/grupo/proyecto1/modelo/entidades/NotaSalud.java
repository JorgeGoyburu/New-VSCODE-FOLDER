package com.grupo.proyecto1.modelo.entidades;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class NotaSalud {

    private static int contador = 1; 
    private String codigo; 
    private LocalDateTime fechaHora; 
    private String descripcion; 
    
    public NotaSalud(String descripcion) { 
        this.codigo = "N-" + (contador++);
        this.fechaHora = LocalDateTime.now(); // Captura automática en el instante preciso 
        this.descripcion = descripcion; 
    } 
    
    public String getCodigo() { return codigo; } 
    public LocalDateTime getFechaHora() { return fechaHora; } 
    
    public String getFechaHoraFormateada() { 
        return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")); 
    } 
    
    public String getDescripcion() { return descripcion; }
    
}
