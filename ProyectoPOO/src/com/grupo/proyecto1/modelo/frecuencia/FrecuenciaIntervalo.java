package com.grupo.proyecto1.modelo.frecuencia;

import java.time.LocalDateTime; 
import java.time.LocalTime; 
import java.util.List;

public class FrecuenciaIntervalo {

    public class FrecuenciaIntervalo extends Frecuencia {
        private int cantidadIntervalo;
        private String tipoIntervalo; // "DIAS", "SEMANAS", "MESES" 
        public FrecuenciaIntervalo(int vecesPorDia, List<LocalTime> horasToma, int cantidadIntervalo, String tipoIntervalo) {
            super(vecesPorDia, horasToma);
            this.cantidadIntervalo = cantidadIntervalo;
            this.tipoIntervalo = tipoIntervalo;
        }

        @Override
        public LocalDateTime calcularProximaToma(LocalDateTime desde) {
            LocalDateTime calculo = desde;
            switch (tipoIntervalo.toUpperCase()) {
                case "DIAS": calculo = desde.plusDays(cantidadIntervalo);
                break;

                case "SEMANAS": calculo = desde.plusWeeks(cantidadIntervalo); 
                break;
        
                case "MESES": calculo =desde.plusMonths(cantidadIntervalo); 
                break;

                default: calculo = desde.plusDays(cantidadIntervalo);
                break;
            }
            return calculo.toLocalDate().atTime(horasToma.get(0));
        } 
        
        @Override
        public String describir() {
            return "Cada " + cantidadIntervalo + " " + tipoIntervalo.toLowerCase();
        }
    }  
}
