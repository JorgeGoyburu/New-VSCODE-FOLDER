package com.grupo.proyecto1.modelo.entidades;

import com.grupo.proyecto1.modelo.base.Persona; 
import com.grupo.proyecto1.modelo.enums.RelacionFamiliar; 
import java.util.ArrayList; 
import java.util.List;

public class Usuario extends Persona {
    private static int contador = 1; 
    private RelacionFamiliar relacion; 
    private String email; 
    private List<Medicamento> listaMedicamentos; 
    private List<Doctor> listaDoctores; 
    private List<Cita> listaCitas; 
    private List<ActividadFisica> listaActividades; 
    private List<NotaSalud> listaNotas;

    public Usuario(String nombre, RelacionFamiliar relacion, String email) { 
        super(String.valueOf(contador++), nombre); 
        this.relacion = relacion; 
        this.email = email; 
        this.listaMedicamentos = new ArrayList<>(); 
        this.listaDoctores = new ArrayList<>();
        this.listaCitas = new ArrayList<>(); 
        this.listaActividades = new ArrayList<>(); 
        this.listaNotas = new ArrayList<>(); 
    }

    @Override
    public String mostrarInfo() { 
        return String.format("Perfil: %s | Relación: %s | Email: %s", nombre, relacion.name(), email); 
    }

    
    // Métodos delegados para gestión de colecciones 
    public void agregarMedicamento(Medicamento med) { listaMedicamentos.add(med); } 
    
    public boolean eliminarMedicamento(String codigo) { 
        return listaMedicamentos.removeIf(m -> m.getCodigo().equalsIgnoreCase(codigo)); 
    } 
    
    public Medicamento buscarMedicamento(String codigo) { 
        return listaMedicamentos.stream().filter(m -> m.getCodigo().equalsIgnoreCase(codigo)).findFirst().orElse(null);
    }

    public void agregarDoctor(Doctor doc) { listaDoctores.add(doc); } 
    
    public boolean eliminarDoctor(String codigo) { 
        return listaDoctores.removeIf(d -> d.getCodigo().equalsIgnoreCase(codigo)); 
    } 
    
    public Doctor buscarDoctor(String codigo) { 
        return listaDoctores.stream().filter(d -> d.getCodigo().equalsIgnoreCase(codigo)).findFirst().orElse(null); 
    } 
    
    public Doctor buscarDoctorPorIndice(int index) { 
        if (index >= 0 && index < listaDoctores.size()) { 
            return listaDoctores.get(index); } return null; 
        } 
        
    public void agregarCita(Cita cita) { listaCitas.add(cita); } 
    public boolean eliminarCita(String codigo) { 
        return listaCitas.removeIf(c -> c.getCodigo().equalsIgnoreCase(codigo)); 
    }

    public Cita buscarCita(String codigo) { 
        return listaCitas.stream().filter(c -> c.getCodigo().equalsIgnoreCase(codigo)) .findFirst().orElse(null); 
    } 
    public void agregarActividad(ActividadFisica act) { listaActividades.add(act); } 
    public void agregarNota(NotaSalud nota) { listaNotas.add(nota); } 
    
    // Getters 
    public RelacionFamiliar getRelacion() { return relacion; } 
    public String getEmail() { return email; } 
    public List<Medicamento> getListaMedicamentos() { return listaMedicamentos; } 
    public List<Doctor> getListaDoctores() { return listaDoctores; } 
    public List<Cita> getListaCitas() { return listaCitas; } 
    public List<ActividadFisica> getListaActividades() { return listaActividades; } 
    public List<NotaSalud> getListaNotas() { return listaNotas; } 
    
}
