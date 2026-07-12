package com.grupo.proyecto1.modelo.frecuencia;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public abstract class Frecuencia {
    protected int vecesPorDia; protected List<LocalTime> horasToma; 
    public Frecuencia(int vecesPorDia, List<LocalTime> horasToma) {
        this.vecesPorDia = vecesPorDia; this.horasToma = new ArrayList<>(horasToma);
    }
    public abstract LocalDateTime calcularProximaToma(LocalDateTime desde);
    public abstract String describir();
    public int getVecesPorDia() { return vecesPorDia; }
    public List<LocalTime> getHorasToma() { return horasToma; }
}