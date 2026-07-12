package com.grupo.proyecto1.modelo.entidades; 

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class Toma {
    private static int contador = 1; 
    private String codigo; 
    private LocalDateTime fechaHora; 
    private String cantidadTomada; 
    public Toma(LocalDateTime fechaHora, String cantidadTomada) { 
        this.codigo = "T-" + (contador++); 
        this.fechaHora = fechaHora; 
        this.cantidadTomada = cantidadTomada; 
    }

    public String getCodigo() { 
        return codigo; 
    } 
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    } 
    public String getFechaHoraFormateada() { 
        return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy a las HH:mm")); 
    }

    public String getCantidadTomada() { 
        return cantidadTomada; 
    }
}
