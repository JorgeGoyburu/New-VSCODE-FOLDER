package com.grupo.proyecto1.vista; 

import com.grupo.proyecto1.modelo.entidades.*; 
import java.util.Comparator; import java.util.List; 
import java.util.Scanner; 

/** 
 * Encargada de renderizar pantallas, menús y tablas por consola. 
 *Ref. Rúbrica: Criterio "Clases vista para la interfaz de usuario" (4 pts). 
 */ 

 public class ConsolaVista { 
    private Scanner scanner; public ConsolaVista(Scanner scanner) { this.scanner = scanner; } 
    public void mostrarEncabezado(String titulo) {

        System.out.println("\n======================================================================"); 
        System.out.println(" SISTEMA DE CONTROL DE MEDICAMENTOS (Versión 1.0)"); 
        System.out.println("======================================================================"); 
        System.out.println("[" + titulo + "]");
    }
    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); } 
    public void mostrarError(String error) { System.out.println("[ERROR]: " + error); }


    public String leerTexto(String prompt) { 
        System.out.print("> " + prompt + ": "); 
        return scanner.nextLine().trim(); 
    } 
    public int leerEntero(String prompt) { 
        while (true) { 
            try { System.out.print("> " + prompt + ": "); 
            String input = scanner.nextLine().trim(); 
            return Integer.parseInt(input); 
        } 
            catch (NumberFormatException e) { 
                mostrarError("Por favor, ingrese un número entero válido."); 
            } 
        } 
    } 
    
    public void pausar() { 
System.out.println("\n======================================================================"); 
        System.out.print("Presione ENTER para continuar o regresar al menú..."); 
        scanner.nextLine(); 
    }

    // --- Tablas y Listados Especializados según PDF ---

    public void mostrarTablaMedicamentos(List<Medicamento> lista) { 
        if (lista.isEmpty()) { 
            System.out.println("No se encontraron medicamentos registrados en el sistema."); 
            return; 
        }
        System.out.println("Se encontraron " + lista.size() + " medicamentos activos.\n"); 
        System.out.printf("%-4s | %-14s | %-15s | %-20s | %-12s\n", "ID", "NOMBRE", "PRESENTACIÓN", "FRECUENCIA", "STOCK/CANT."); 
System.out.println("----------------------------------------------------------------------");
        
        int totalTomasHoy = 0; 
        boolean hayAlertas = false; 
        for (Medicamento m : lista) { 
            String pres = m.getPresentacion().name().toLowerCase(); 
            pres = pres.substring(0, 1).toUpperCase() + pres.substring(1); 
            String stockStr = m.getInventario() + " unidades"; 
            System.out.printf("%-4s | %-14s | %-15s | %-20s | %-12s\n", m.getCodigo(), m.getNombre(), pres, m.getFrecuencia().describir(), stockStr); 
            totalTomasHoy += m.getHistorialTomas().size(); 
            if (m.estaProximoAgotarse()) hayAlertas = true; 
        }
System.out.println("----------------------------------------------------------------------"); 
        System.out.println("Estadísticas rápidas:"); 
        System.out.println("-> Total de dosis administradas hoy: " + totalTomasHoy); 
        if (hayAlertas) { 
            System.out.println("-> Alertas: ¡Atención! Uno o más medicamentos están por agotarse."); 
        } else { System.out.println("-> Alertas: Ningún medicamento está próximo a agotarse."); } 
    } 

        /** * Ref. Guía de Proyecto: Sección 2.1 Listado actual del personal médico registrado. */ 
        public void mostrarTablaDoctores(List<Doctor> lista) { 
            if (lista.isEmpty()) { 
                System.out.println("No hay médicos registrados en el sistema."); 
                return; 
            } 
            System.out.println("Listado actual del personal médico registrado:\n"); 
            System.out.printf("%-5s | %-22s | %-16s | %-18s\n", "ID", "NOMBRE COMPLETO", "ESPECIALIDAD", "TELÉFONO / CONTACTO");
    
            System.out.println("----------------------------------------------------------------------");
        
            for (Doctor d : lista) { 
                System.out.printf("%-5s | %-22s | %-16s | %-18s\n", d.getCodigo(), d.getNombre(), d.getEspecialidad(), d.getTelefono()); 
            }
            
System.out.println("----------------------------------------------------------------------"); 
    }

    public void mostrarTablaCitas(List<Cita> lista) { 
        if (lista.isEmpty()) { 
            System.out.println("No hay citas programadas en el sistema."); 
            return; 
        }
        System.out.println("Listado de citas programadas (Ordenadas por la más próxima):\n"); 
        System.out.printf("%-5s | %-22s | %-22s | %-16s\n", "ID", "TÍTULO DE LA CITA", "MÉDICO ASIGNADO", "FECHA Y HORA"); 
System.out.println("----------------------------------------------------------------------");

        lista.stream() .sorted(Comparator.comparing(Cita::getFechaHora))
        .forEach(c -> { 
            System.out.printf("%-5s | %-22s | %-22s | %-16s\n", 
            c.getCodigo(), c.getTitulo(), c.getDoctor().getNombre(), c.getFechaHoraFormateada()); }); 
System.out.println("----------------------------------------------------------------------"); 
    }
    public void mostrarTablaActividades(List<ActividadFisica> lista) {
        if (lista.isEmpty()) { 
            System.out.println("No hay actividades físicas registradas en el sistema."); 
            return; 
        } 
        System.out.println("Listado de actividades registradas (Ordenadas desde la más reciente):\n"); 
        System.out.printf("%-12s | %-22s | %-12s | %-10s\n", "FECHA", "ACTIVIDAD", "DURACIÓN", "HORARIO"); 
        System.out.println("----------------------------------------------------------------------"); 
        lista.stream() .sorted(Comparator.comparing(ActividadFisica::getFecha).reversed())
        .forEach(a -> { System.out.printf("%-12s | %-22s | %-12s | %-10s\n", a.getFechaFormateada(), 
        a.getNombreActividad(), a.getDuracionMinutos() + " min", a.getHorarioFormateado()); }); 
System.out.println("----------------------------------------------------------------------"); 
    }

    public void mostrarTablaNotas(List<NotaSalud> lista) { 
        if (lista.isEmpty()) { 
            System.out.println("No hay notas de salud registradas en el sistema."); 
            return; 
        } 
        System.out.println("Listado de notas registradas (Ordenadas desde la más reciente):\n"); 
        System.out.printf("%-18s | %-45s\n", "FECHA Y HORA", "DESCRIPCIÓN DE SALUD / SÍNTOMAS"); 
        System.out.println("----------------------------------------------------------------------"); lista.stream()
        .sorted(Comparator.comparing(NotaSalud::getFechaHora).reversed()).forEach(n -> { 
            System.out.printf("%-18s | %-45s\n", n.getFechaHoraFormateada(), n.getDescripcion()); }); 
System.out.println("----------------------------------------------------------------------"); 
    } 

}



