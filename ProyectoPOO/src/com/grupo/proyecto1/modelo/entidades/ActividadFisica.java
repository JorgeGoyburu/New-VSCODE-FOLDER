package com.grupo.proyecto1.modelo.entidades;

import com.grupo.proyecto1.modelo.enums.Horario; 
import com.grupo.proyecto1.modelo.enums.TipoActividad; 
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

public class ActividadFisica {

    private static int contador = 1; 
    private String codigo; 
    private LocalDate fecha; 
    private TipoActividad tipo; 
    private int duracionMinutos; 
    private Horario horario; 
    public ActividadFisica(LocalDate fecha, TipoActividad tipo, int duracionMinutos, Horario horario) {
        this.codigo = "ACT-" + (contador++); 
        this.fecha = fecha; 
        this.tipo = tipo; 
        this.duracionMinutos = duracionMinutos; 
        this.horario = horario; 
    }
    
    public String getCodigo() { return codigo; } 
    public LocalDate getFecha() { return fecha; } 
    
    public String getFechaFormateada() { 
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
    } 
    public TipoActividad getTipo() { return tipo; } 
    
    public String getNombreActividad() { 
        String nombre = tipo.name().replace("_", " ").toLowerCase(); 
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1); 
    } 
    public int getDuracionMinutos() { return duracionMinutos; } 
    public Horario getHorario() { return horario; } 
    
    public String getHorarioFormateado() { 
        String h = horario.name().toLowerCase(); 
        return h.substring(0, 1).toUpperCase() + h.substring(1); 
    }
}
