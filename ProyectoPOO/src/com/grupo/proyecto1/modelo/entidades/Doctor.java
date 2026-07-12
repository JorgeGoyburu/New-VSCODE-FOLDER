package com.grupo.proyecto1.modelo.entidades;
import com.grupo.proyecto1.modelo.base.Persona;


public class Doctor {

    private static int contador = 1; 
    private String especialidad; 
    private String telefono; 
    private String email; 
    private String direccion;

    public Doctor(String nombre, String especialidad, String telefono, String email, String direccion) { 
        super(String.format("M-%02d", contador++), nombre); 
        this.especialidad = especialidad; 
        this.telefono = telefono; 
        this.email = email; 
        this.direccion = direccion; 
    }

    @Override
    public String mostrarInfo() { 
        return String.format("%s | %s | %s | %s", codigo, nombre, especialidad, telefono); 
    }
// Getters y Setters

    public String getEspecialidad() { return especialidad; } 
    public String getTelefono() { return telefono; } 
    public String getEmail() { return email; } 
    public String getDireccion() { return direccion; }

}
