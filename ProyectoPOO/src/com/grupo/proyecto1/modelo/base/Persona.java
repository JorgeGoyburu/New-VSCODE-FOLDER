package com.grupo.proyecto1.modelo.base;

/**
 * Clase abstracta que representa conceptos comunes de personas dentro del sistema.
 * Aplica herencia para evitar repetición de código en usuarios y médicos.
 */
public abstract class Persona extends EntidadBase {

    protected String codigo;
    protected String nombre;

    /**
     * Constructor parametrizado para inicializar una persona.
     * 
     * @param codigo Identificador único autogenerado.
     * @param nombre Nombre completo o identificador del usuario/doctor.
     */
    public Persona(String codigo, String nombre) {
        super(); // Llama al constructor de EntidadBase para registrar creadoEn
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Método abstracto para polimorfismo. Cada subclase implementará 
     * su propia forma de mostrar el resumen de su información.
     * 
     * @return Cadena de texto con la información formateada.
     */
    public abstract String mostrarInfo();

    // --- Setters & Getters ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}