package com.grupo.proyecto1.modelo.entidades;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class Cita {

    private static int contador = 1; 
    private String codigo; 
    private String titulo; 
    private Doctor doctor; 
    private LocalDateTime fechaHora; 
    public Cita(String titulo, Doctor doctor, LocalDateTime fechaHora) { 
        this.codigo = String.format("C-%02d", contador++); 
        this.titulo = titulo; this.doctor = doctor;
        this.fechaHora = fechaHora; 
    }

    public boolean esFutura(LocalDateTime fechaReferencia) { 
        return this.fechaHora.isAfter(fechaReferencia); 
    } 
    public String getCodigo() { 
        return codigo; 
    } 
    public String getTitulo() { 
        return titulo; 
    } 
    public Doctor getDoctor() { 
        return doctor; 
    } 
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    } 
    public String getFechaHoraFormateada() { 
        return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")); 
    }
    
}
