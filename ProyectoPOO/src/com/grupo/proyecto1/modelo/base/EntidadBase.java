package com.grupo.proyecto1.modelo.base;

import java.time.LocalDateTime;

/**
 * Clase base que proporciona auditoría temporal de creación y actualización
 * para las entidades del sistema.
 */
public abstract class EntidadBase {
    
    protected LocalDateTime creadoEn;
    protected LocalDateTime actualizadoEn;

    /**
     * Constructor que inicializa las marcas de tiempo con la fecha y hora actual.
     */
    public EntidadBase() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    // --- Setters & Getters ---

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}