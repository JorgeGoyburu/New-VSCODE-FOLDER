package com.grupo.proyecto1.modelo.frecuencia;

import java.time.LocalDateTime; 
import java.time.LocalTime; 
import java.util.List;

public class FrecuenciaDiaria extends Frecuencia {
    public FrecuenciaDiaria(int vecesPorDia, List<LocalTime> horasToma) {
        super(vecesPorDia, horasToma);
    }
    @Override
    public LocalDateTime calcularProximaToma(LocalDateTime desde){
        LocalTime horaActual = desde.toLocalTime();
        for (LocalTime hora : horasToma) {
            if (hora.isAfter(horaActual)) {
                return desde.toLocalDate().atTime(hora);
            }
        }
        return desde.toLocalDate().plusDays(1).atTime(horasToma.get(0));

    }
    

    @Override
    public String describir() {
        return "Cada día (" + vecesPorDia + " veces)";
    }
}