package com.grupo.proyecto1.modelo.entidades;

import com.grupo.proyecto1.modelo.base.EntidadBase; 
import com.grupo.proyecto1.modelo.enums.Presentacion; 
import com.grupo.proyecto1.modelo.frecuencia.Frecuencia; 
import java.util.ArrayList; 
import java.util.List;

public class Medicamento extends EntidadBase {
    private static int contador = 1;
    private String codigo;
    private String nombre; 
    private Presentacion presentacion; 
    private int inventario; 
    private String dosis; 
    private Frecuencia frecuencia; 
    private int umbralAlerta; 
    private List<Toma> historialTomas; 
    private List<Recarga> historialRecargas;

    public Medicamento(String nombre, Presentacion presentacion, int 
        inventario, String dosis, Frecuencia frecuencia) { 
            super();
            this.codigo = String.format("%03d", contador++);
            this.nombre = nombre; 
            this.presentacion = presentacion; 
            this.inventario = inventario; 
            this.dosis = dosis; 
            this.frecuencia = frecuencia;
            this.umbralAlerta = 0;
            this.historialTomas = new ArrayList<>(); 
            this.historialRecargas = new ArrayList<>();
    }
    public void registrarToma(Toma toma) { 
        this.historialTomas.add(toma); 
        if (this.inventario > 0) { 
            this.inventario--;
        }
    }

    public void registrarRecarga(Recarga recarga) { 
        this.historialRecargas.add(recarga); 
        this.inventario += recarga.getCantidadAgregada();
    }

    public boolean estaProximoAgotarse() { 
        return umbralAlerta > 0 && inventario <= umbralAlerta;

    }
    // Getters y Setters 
    public String getCodigo() { 
        return codigo;
    }
    public String getNombre() {
        return nombre;
    } 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Presentacion getPresentacion() {
        return presentacion;
    }
    public int getInventario() { 
        return inventario; 
    } 
    public String getDosis() { 
        return dosis; 
    } 
    public Frecuencia getFrecuencia() { 
        return frecuencia; 
    } 
    public int getUmbralAlerta() { 
        return umbralAlerta; 
    } 
    public void setUmbralAlerta(int umbralAlerta) { 
        this.umbralAlerta = umbralAlerta; 
    } 
    public List<Toma> getHistorialTomas() { 
        return historialTomas; 
    } 
    public List<Recarga> getHistorialRecargas() { 
        return historialRecargas; 
    }
}
