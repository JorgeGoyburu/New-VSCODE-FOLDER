package com.grupo.proyecto1.modelo.frecuencia;

import java.time.LocalDateTime; 
import java.time.LocalTime; 
import java.util.List;

public class FrecuenciaDiasEspecificos extends Frecuencia {

    private List<String> diasSemana; 
    public FrecuenciaDiasEspecificos(int vecesPorDia, List<LocalTime>
        horasToma, List<String> diasSemana) {
            super(vecesPorDia, horasToma);
            this.diasSemana = diasSemana;
        }
    @Override
    public LocalDateTime calcularProximaToma(LocalDateTime desde) {
        return desde.plusDays(1).toLocalDate().atTime(horasToma.get(0));
    }

    @Override 
    public String describir() { 
        return "Días: " + String.join(",", diasSemana);
    }
}

