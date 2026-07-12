package com.grupo.proyecto1.controlador;

import com.grupo.proyecto1.modelo.entidades.*; 
import com.grupo.proyecto1.modelo.enums.*; 
import com.grupo.proyecto1.modelo.frecuencia.*; 
import com.grupo.proyecto1.util.GeminiAIService; 
import com.grupo.proyecto1.vista.ConsolaVista; 
import java.time.LocalDate; 
import java.time.LocalDateTime; 
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter; 
import java.time.format.DateTimeParseException; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Scanner;

/**
 * Controlador principal (Patrón MVC). Orquesta las llamadas entre
 * Vista y Modelo.
 * Ref. Rúbrica: Criterio "Clases controladores para la interacción
 * entre la vista y el modelo" (4 pts).
 */
public class SistemaControlador {
    private List<Usuario> perfiles;
    private Usuario perfilActual;
    private ConsolaVista vista; 
    private Scanner scanner;

    public SistemaControlador() { 
        this.perfiles = new ArrayList<>(); 
        this.scanner = new Scanner(System.in); 
        this.vista = new ConsolaVista(scanner); 
        inicializarDatosDePrueba();
    }
    private void inicializarDatosDePrueba() {
         // 1. Perfil 
        Usuario daniel = new Usuario("Daniel Noboa", RelacionFamiliar.OTRO, "daniel.noboa@gob.ec"); 
        // 2. Medicación: pastilla con toma diaria a las 8:00 con 30 unidades 
        List<LocalTime> horas = List.of(LocalTime.of(8, 0)); 
        Frecuencia frec = new FrecuenciaDiaria(1, horas); 
        Medicamento med = new Medicamento("Amoxicilina", Presentacion.PASTILLA, 30, "1 pastilla", frec); 
        med.setUmbralAlerta(5); // Umbral inicial para pruebas 
        daniel.agregarMedicamento(med); 
        // 3. Actividad física 
        ActividadFisica act = new ActividadFisica(LocalDate.now(), TipoActividad.ENTRENAMIENTO_PESAS, 60, Horario.TARDE); 
        daniel.agregarActividad(act); 
        // 4. Doctor 
        Doctor doc = new Doctor("Dr. Carlos Mendoza", "Pediatría", "+593 98 765 4321", "c.mendoza@med.com", "Av. Principal 123"); 
        daniel.agregarDoctor(doc); 
        // 5. Cita 
        Cita cita = new Cita("Chequeo Semestral", doc, LocalDateTime.now().plusDays(5).withHour(15).withMinute(0)); 
        daniel.agregarCita(cita); 
        // 6. Nota de salud 
        NotaSalud nota = new NotaSalud("Siento un leve dolor de cabeza en la zona frontal.");
        daniel.agregarNota(nota); 
        perfiles.add(daniel);

        perfiles.add(new Usuario("XYZ", RelacionFamiliar.OTRO, "xyz@test.com")); 
    }
    /** 
    * Bucle del menú de inicio de aplicación. 
    */ 
    
    public void iniciar() { 
        while (true) { 
            vista.mostrarEncabezado("Menú de Inicio - Selección de Perfiles"); 
            System.out.println("Perfiles disponibles en el sistema:"); 
            for (int i = 0; i < perfiles.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + perfiles.get(i).getNombre()); 
            }
            System.out.println("\nOpciones:"); 
            System.out.println(" a. Crear perfil"); 
            System.out.println(" b. Seleccionar perfil"); 
            System.out.println(" 0. Salir del programa"); 
            String op = vista.leerTexto("Seleccione una opción");

            if (op.equalsIgnoreCase("a")) { 
                crearPerfil(); 
            } else if (op.equalsIgnoreCase("b")) { 
                seleccionarPerfil(); 
            } else if (op.equals("0")) { 
                vista.mostrarMensaje("¡Gracias por utilizar el Sistema de Control de Salud! Hasta pronto."); 
                break; 
            } else { 
                vista.mostrarError("Opción no válida."); 
            } 
        } 
    }

    private void crearPerfil() { 
        vista.mostrarEncabezado("Crear Nuevo Perfil"); 
        String nombre = vista.leerTexto("Nombre del Usuario (miembro de la familia)");
        if (nombre.isEmpty()) { 
            vista.mostrarError("El nombre no puede estar vacío."); 
            return; 
        } 
        System.out.println("\nRelaciones disponibles:"); 
        RelacionFamiliar[] rels = RelacionFamiliar.values(); 
        for (int i = 0; i < rels.length; i++) { 
            System.out.println(" [" + (i + 1) + "] " + rels[i].name()); 
        } 
        int idxRel = vista.leerEntero("Seleccione el número de la relación") - 1; 
        RelacionFamiliar relacion = (idxRel >= 0 && idxRel < rels.length) ? rels[idxRel] : RelacionFamiliar.OTRO; 
        String email = vista.leerTexto("Email (puede dejar en blanco presionando ENTER)"); 
        Usuario nuevo = new Usuario(nombre, relacion, email); 
        perfiles.add(nuevo); 
        vista.mostrarMensaje("¡Éxito! Perfil para '" + nombre + "' creado correctamente."); 
        vista.pausar(); 
    }
    private void seleccionarPerfil() { 
        if (perfiles.isEmpty()) { 
            vista.mostrarError("No existen perfiles. Cree uno primero."); 
            return; 
        }
        vista.mostrarEncabezado("Seleccionar Perfil"); 
        for (int i = 0; i < perfiles.size(); i++) { 
            System.out.println(" [" + (i + 1) + "] " + perfiles.get(i).getNombre()); 
        }
        int idx = vista.leerEntero("Seleccione el número del perfil que desea gestionar") - 1;
        if (idx >= 0 && idx < perfiles.size()) { 
            perfilActual = perfiles.get(idx); menuPrincipalPerfil(); 
        } else { 
            vista.mostrarError("Número de perfil incorrecto.");
        }
    }

    //Menu principal para un perfil selecionado
    private void menuPrincipalPerfil() {
        while (true) { 
            vista.mostrarEncabezado("Menú Principal > Perfil Activo: " + perfilActual.getNombre()); 
            System.out.println("1.- Administrar medicamentos"); 
            System.out.println("2.- Administrar médicos"); 
            System.out.println("3.- Administrar citas médicas"); 
            System.out.println("4.- Administrar Actividad Física"); 
            System.out.println("5.- Administrar Notas de salud"); 
            System.out.println("0.- Regresar a selección de perfil"); 
            String op = vista.leerTexto("Seleccione una opción"); 
            switch (op) { 
                case "1": menuMedicamentos(); break; 
                case "2": menuMedicos(); break; 
                case "3": menuCitas(); break; 
                case "4": menuActividades(); break; 
                case "5": menuNotas(); break; 
                case "0": return; // Regresa atrás 
                default: vista.mostrarError("Opción incorrecta."); break; 
            } 
        }
    }

    // ---------------- MODULO 1: MEDICAMENTOS ------------------------

    private void menuMedicamentos() { 
        while (true) { 
            vista.mostrarEncabezado("Menú Principal > 1. Gestión de Medicamentos"); 
            System.out.println("1.1. Lista de Medicamentos activos"); 
            System.out.println("1.2. Añadir Medicina"); 
            System.out.println("1.3. Eliminar Medicina");
            System.out.println("1.4. Registrar Toma"); 
            System.out.println("1.5. Recarga de Medicina"); 
            System.out.println("1.6. Recordatorio de recarga de medicina"); 
            System.out.println("0. Regresar al Menú Principal"); 
            String op = vista.leerTexto("Seleccione una opción"); 
            switch (op) { case "1.1": case "1": vista.mostrarEncabezado("1.1. Lista de Medicamentos Activos"); 
            vista.mostrarTablaMedicamentos(perfilActual.getListaMedicamentos()); 
            vista.pausar(); break; 
            case "1.2": case "2": anadirMedicina(); break; 
            case "1.3": case "3": eliminarMedicina(); break; 
            case "1.4": case "4": registrarToma(); break; 
            case "1.5": case "5": recargarMedicina(); break; 
            case "1.6": case "6": configurarRecordatorio(); break; 
            case "0": return; 
            default: vista.mostrarError("Opción incorrecta."); break; 
            } 
        } 
    }

    private void anadirMedicina() { 
        vista.mostrarEncabezado("1.2. Añadir Medicina"); 
        System.out.println("Por favor, ingrese los datos del nuevo medicamento:\n"); 
        String nombre = vista.leerTexto("1. Nombre del Medicamento"); 
        int inventario = vista.leerEntero("2. Cantidad de unidades disponibles (inventario)"); 
        System.out.println("\n3. Presentación:"); Presentacion[] presValues = Presentacion.values(); 
        for (int i = 0; i < presValues.length; i++) { 
            System.out.println(" [" + (i + 1) + "] " + presValues[i].name()); 
        } 
        int pIdx = vista.leerEntero("Seleccione una opción (1-" + presValues.length + ")") - 1;
        Presentacion pres = (pIdx >= 0 && pIdx < presValues.length) ? presValues[pIdx] : Presentacion.PASTILLA;
        System.out.println("\n4. Frecuencia de consumo:"); 
        System.out.println(" [1] Cada día"); 
        System.out.println(" [2] Cada dos días"); 
        System.out.println(" [3] Días específicos de la semana"); 
        System.out.println(" [4] Cada X días"); 
        System.out.println(" [5] Cada X semanas"); 
        System.out.println(" [6] Cada X meses"); 
        int fOp = vista.leerEntero("Seleccione una opción (1-6)"); 
        int vecesDia = 1; System.out.println("\n5. Frecuencia del Día:"); 
        System.out.println(" [1] Una vez al día"); 
        System.out.println(" [2] Dos veces al día"); 
        System.out.println(" [3] Tres veces al día"); 
        int vOp = vista.leerEntero("Seleccione una opción (1-3)"); 
        if (vOp >= 1 && vOp <= 3) vecesDia = vOp; 
        System.out.println("\n6. Configuración de Horarios (Formato 24h - ej: 08:00, 14:30):"); 
        List<LocalTime> horas = new ArrayList<>(); 
        for (int i = 1; i <= vecesDia; i++) { 
            while (true) { 
                String hStr = vista.leerTexto("Ingrese la hora para la Toma " + i + " (HH:MM)"); 
                try { horas.add(LocalTime.parse(hStr, DateTimeFormatter.ofPattern("HH:mm"))); break; } 
                catch (DateTimeParseException e) { 
                    vista.mostrarError("Formato de hora inválido. Use HH:MM (ejemplo: 08:00 o 20:30)."); 
                } 
            } 
        
        }
        Frecuencia frecuencia; 
        if (fOp == 1) { 
            frecuencia = new FrecuenciaDiaria(vecesDia, horas); 
        } else if (fOp == 2) { 
            frecuencia = new FrecuenciaIntervalo(vecesDia, horas, 2, "DIAS"); 
        } else if (fOp == 3) { 
            String diasStr = vista.leerTexto("Seleccione los días (separe con comas, ej: L,Mi,V)"); 
            List<String> diasList = Arrays.asList(diasStr.split(","));
            frecuencia = new FrecuenciaDiasEspecificos(vecesDia, horas, diasList); 
        } else if (fOp == 4) { 
            int x = vista.leerEntero("Ingrese el número de días (X)"); 
            frecuencia = new FrecuenciaIntervalo(vecesDia, horas, x, "DIAS"); 
        } else if (fOp == 5) { 
            int x = vista.leerEntero("Ingrese el número de semanas (X)"); 
            frecuencia = new FrecuenciaIntervalo(vecesDia, horas, x, "SEMANAS"); 
        } else if (fOp == 6) { 
            int x = vista.leerEntero("Ingrese el número de meses (X)"); 
            frecuencia = new FrecuenciaIntervalo(vecesDia, horas, x, "MESES"); 
        } else { 
            frecuencia = new FrecuenciaDiaria(vecesDia, horas); 
        }
        String dosis = vista.leerTexto("7. Dosis por toma (ej: 1 pastilla, 5ml)");
    
        System.out.println("\nPROCESANDO REGISTRO..."); 
        Medicamento med = new Medicamento(nombre, pres, inventario, dosis, frecuencia); 
        perfilActual.agregarMedicamento(med);
        System.out.println("\n¡Éxito! El medicamento ha sido registrado correctamente:"); 
        System.out.println("-> Medicina: " + med.getNombre() + " (" + med.getInventario() + " unidades en inventario)"); 
        System.out.println("-> Presentación: " + pres.name()); 
        System.out.println("-> Horario: " + frecuencia.describir() + " (Dosis: " + dosis + ")"); 
System.out.println("======================================================================");
        // Puntos Adicionales: Resumen con IA
        String conIA = vista.leerTexto("¿Desea consultar a Gemini AI para obtener un resumen sencillo sobre este medicamento? (S/N)");
        if (conIA.equalsIgnoreCase("S")) { 
            System.out.println("\nConectando con Gemini AI... Por favor, espere.");
System.out.println("======================================================================");
            System.out.println("INFORMACIÓN DEL MEDICAMENTO (VÍA GEMINI AI)");
System.out.println("======================================================================");
System.out.println(GeminiAIService.obtenerResumenSencillo(med.getNombre()));
        }
        vista.pausar();
    }
    private void eliminarMedicina() {
        vista.mostrarEncabezado("1.3. Eliminar Medicina"); 
        System.out.println("Listado actual de medicamentos en el sistema:\n");
    vista.mostrarTablaMedicamentos(perfilActual.getListaMedicamentos());
        if (perfilActual.getListaMedicamentos().isEmpty()) { 
            vista.pausar(); 
            return;
        }
        String id = vista.leerTexto("Ingrese el ID del medicamento que desea eliminar (o 0 para cancelar)"); 
        if (id.equals("0")) return;

        Medicamento med = perfilActual.buscarMedicamento(id); 
        if (med != null) { 
            System.out.println("\n¡ADVERTENCIA: ESTA ACCIÓN NO SE PUEDE DESHACER!"); 
            System.out.println("¿Está seguro de que desea eliminar permanentemente el medicamento?"); 
            System.out.println("[Medicina Seleccionada]: ID: " + med.getCodigo() + " - " + med.getNombre() 
            + " (" + med.getPresentacion().name() + ")"); 
            String conf = vista.leerTexto("¿Confirmar eliminación? (S/N)");
            if (conf.equalsIgnoreCase("S")) { 
                System.out.println("Eliminando registro..."); 
                perfilActual.eliminarMedicamento(id); 
                System.out.println("¡Éxito! El medicamento \"" + med.getNombre() + "\" ha sido removido del sistema.");
            } else { 
                System.out.println("Acción cancelada."); 
            } 
        } else { 
            vista.mostrarError("ID de medicamento no encontrado."); 
        } 
        vista.pausar();
    }
    
    private void registrarToma() { 
        vista.mostrarEncabezado("1.4. Registrar Toma"); 
        if (perfilActual.getListaMedicamentos().isEmpty()) { 
            System.out.println("No hay medicamentos registrados."); 
            vista.pausar(); 
            return; 
        }
        System.out.println("Listado de medicamentos disponibles para toma:\n"); 
        System.out.printf("%-4s | %-14s | %-15s | %-12s | %-12s\n", "ID", "NOMBRE", "PRESENTACIÓN", "DOSIS", "STOCK ACTUAL"); 
System.out.println("----------------------------------------------------------------------");

        for (Medicamento m : perfilActual.getListaMedicamentos()) { 
            System.out.printf("%-4s | %-14s | %-15s | %-12s | %-12s\n", m.getCodigo(), m.getNombre(), m.getPresentacion().name(), 
            m.getDosis(), m.getInventario() + " unidades"); 
        }
System.out.println("----------------------------------------------------------------------");

        String id = vista.leerTexto("Ingrese el ID del medicamento que acaba de tomar (o 0 para salir)"); 
        if (id.equals("0")) return; 
        Medicamento med = perfilActual.buscarMedicamento(id); 
        if (med == null) { 
            vista.mostrarError("Medicamento no encontrado."); 
            vista.pausar(); 
            return; 
        }
        System.out.println("\nREGISTRANDO TOMA..."); 
        System.out.println("Medicamento seleccionado: " + med.getNombre() + " (Dosis: " + med.getDosis() + ")"); 
        LocalDateTime ahora = LocalDateTime.now(); 
        System.out.println("\n¿Desea registrar la toma con la fecha y hora actual del sistema?"); 
        System.out.println("[Fecha actual: " + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
        " | Hora actual: " + ahora.format(DateTimeFormatter.ofPattern("HH:mm")) + "]"); 
        String conf = vista.leerTexto("¿Confirmar? (S/N)"); 
        LocalDateTime fechaToma = ahora; 
        boolean manual = false; 
        if (!conf.equalsIgnoreCase("S")) {
            manual = true; 
            while (true) { 
                try { 
                    String fStr = vista.leerTexto("Ingrese la fecha de la toma (DD/MM/AAAA)"); 
                    String hStr = vista.leerTexto("Ingrese la hora de la toma (HH:MM)"); 
                    fechaToma = LocalDateTime.parse(fStr + " " + hStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); 
                    break; 
                } 
                catch (DateTimeParseException e) { 
                    vista.mostrarError("Formato incorrecto. Respete estrictamente DD/MM/AAAA y HH:MM."); 
                } 
            } 
        }
        System.out.println("Guardando registro en el historial..."); 
        System.out.println("Actualizando inventario de la medicina...");
        Toma toma = new Toma(fechaToma, med.getDosis()); 
        med.registrarToma(toma);
        if (manual) { 
            System.out.println("\n¡Éxito! Toma registrada manualmente de forma correcta."); 
        } else { 
            System.out.println("\n¡Éxito! Toma registrada correctamente."); 
        } 
        System.out.println("-> Detalles: " + med.getNombre() + "tomado el " + toma.getFechaHoraFormateada() + "."); 
        System.out.println("-> Nuevo stock disponible: " + med.getInventario() + " unidades."); 
        vista.pausar(); 
    }  
    private void recargarMedicina() { 
        vista.mostrarEncabezado("Menú Principal > 1. Gestión > 1.5. Recarga de Medicina"); 
        if (perfilActual.getListaMedicamentos().isEmpty()) { 
            System.out.println("No hay medicamentos para recargar."); 
            vista.pausar(); 
            return; 
        }
        System.out.println("Listado actual de inventario:\n"); 
        System.out.printf("%-4s | %-14s | %-15s | %-12s\n", "ID", "NOMBRE", "PRESENTACIÓN", "STOCK ACTUAL"); 
System.out.println("----------------------------------------------------------------------"); 
        for (Medicamento m : perfilActual.getListaMedicamentos()) { 
            System.out.printf("%-4s | %-14s | %-15s | %-12s\n", m.getCodigo(), m.getNombre(), 
            m.getPresentacion().name(), m.getInventario() + " unidades"); 
        } 
System.out.println("----------------------------------------------------------------------");
        String id = vista.leerTexto("Ingrese el ID del medicamento que desea recargar (o 0 para cancelar)"); 
        if (id.equals("0")) return; 
        Medicamento med = perfilActual.buscarMedicamento(id); 
        if (med == null) { 
            vista.mostrarError("Medicamento no encontrado."); 
            vista.pausar(); 
            return; 
        }
        System.out.println("\nRECARGA DE INVENTARIO"); 
        System.out.println("Medicamento seleccionado: " + med.getNombre() + " (Stock actual: " + med.getInventario() + " unidades)"); 
        int cant = vista.leerEntero("Ingrese la cantidad de unidades que va a agregar");
        String conf = vista.leerTexto("¿Confirmar el ingreso de " + cant + " unidades a " + med.getNombre() + "? (S/N)"); 
        if (conf.equalsIgnoreCase("S")) { 
            System.out.println("Actualizando inventario del sistema..."); 
            System.out.println("Registrando en el historial de recargas..."); 
            Recarga recarga = new Recarga(LocalDateTime.now(), cant); 
            med.registrarRecarga(recarga); 
            System.out.println("\n¡Éxito! Inventario actualizado correctamente."); 
            System.out.println("-> Detalles: Se agregaron " + cant + " unidades a " + med.getNombre() + "."); 
            System.out.println("-> Nuevo stock total disponible: " + med.getInventario() + " unidades."); 
        } else { 
            System.out.println("Recarga cancelada."); 
        } 
        vista.pausar();
    }
    
    
    private void configurarRecordatorio() { 
        vista.mostrarEncabezado("Menú Principal > 1. Gestión > 1.6. Recordatorio de Recarga"); 
        if (perfilActual.getListaMedicamentos().isEmpty()) { 
            System.out.println("No hay medicamentos registrados."); 
            vista.pausar(); 
            return; 
        }
        System.out.println("Configuración actual de alertas de inventario:\n"); 
        System.out.printf("%-4s | %-14s | %-14s | %-20s\n", "ID", "NOMBRE", "STOCK ACTUAL", "UMBRAL CONFIGURADO"); 
        System.out.println("----------------------------------------------------------------------"); 
        for (Medicamento m : perfilActual.getListaMedicamentos()) { 
            String umbStr = (m.getUmbralAlerta() == 0) ? "Sin activar (0)" : m.getUmbralAlerta() + " unidades"; 
            System.out.printf("%-4s | %-14s | %-14s | %-20s\n", m.getCodigo(), m.getNombre(), m.getInventario() + " unidades", umbStr); 
        } 
System.out.println("----------------------------------------------------------------------"); 
        String id = vista.leerTexto("Ingrese el ID del medicamento para configurar el recordatorio (o 0 para salir)"); 
        if (id.equals("0")) return; Medicamento med = perfilActual.buscarMedicamento(id); 
        if (med == null) { vista.mostrarError("Medicamento no encontrado."); 
        vista.pausar(); 
        return;
        }
        System.out.println("\nCONFIGURAR UMBRAL DE ALERTA"); 
        System.out.println("Medicamento seleccionado: " + med.getNombre() + " (Stock actual: " + med.getInventario() + " unidades)"); 
        int umbral = vista.leerEntero("Ingrese la cantidad mínima (umbral) para activar la alerta"); 
        String conf = vista.leerTexto("¿Confirmar que el sistema le notifique cuando " + 
        med.getNombre() + " tenga " + umbral + " unidades o menos? (S/N)");

        if (conf.equalsIgnoreCase("S")) { 
            System.out.println("Guardando configuración..."); 
            med.setUmbralAlerta(umbral); 
            System.out.println("\n¡Éxito! Recordatorio configurado correctamente."); 
            System.out.println("-> Detalles: " + med.getNombre() + " activará una alerta cuando su stock sea <= " + umbral + " unidades."); 
        } else { 
            System.out.println("Operación cancelada."); 
        } 
        vista.pausar();
    }

    //---------------- MODULO 2: MEDICOS -------------------

    private void menuMedicos() { 
        while (true) { 
            vista.mostrarEncabezado("Menú Principal > 2. Gestión de Médicos"); 
            vista.mostrarTablaDoctores(perfilActual.getListaDoctores()); 
            System.out.println("\n¿Qué acción desea realizar?"); 
            System.out.println("[1] Agregar un nuevo médico"); 
            System.out.println("[2] Eliminar un médico existente"); 
            System.out.println("[3] Regresar al Menú Principal"); 
            String op = vista.leerTexto("Seleccione una opción (1-3)"); 
            switch (op) { 
                case "1": agregarMedico(); break; 
                case "2": eliminarMedico(); break; 
                case "3": return; 
                default: vista.mostrarError("Opción incorrecta."); break; 
            } 
        } 
    }  
    private void agregarMedico() { 
        vista.mostrarEncabezado("Gestión de Médicos > Agregar Médico"); 
        String nombre = vista.leerTexto("1. Nombre Completo"); 
        String esp = vista.leerTexto("2. Especialidad (ej: Pediatría, Cardiología)"); 
        String tel = vista.leerTexto("3. Número de Teléfono"); 
        String email = vista.leerTexto("4. Correo Electrónico"); 
        String dir = vista.leerTexto("5. Dirección de la Consulta"); 
        Doctor doc = new Doctor(nombre, esp, tel, email, dir); 
        perfilActual.agregarDoctor(doc); 
        System.out.println("\n¡Éxito! " + nombre + " ha sido registrado/a con el ID " + doc.getCodigo() + "."); 
        vista.pausar(); 
    } 
    private void eliminarMedico() { 
        vista.mostrarEncabezado("Gestión de Médicos > Eliminar Médico"); 
        if (perfilActual.getListaDoctores().isEmpty()) { 
            System.out.println("No hay doctores para eliminar."); 
            vista.pausar(); 
            return; 
        } 
        String id = vista.leerTexto("Ingrese el ID del médico que desea eliminar (ej: M-01) o 0 para cancelar"); 
        if (id.equals("0")) return; 
        Doctor doc = perfilActual.buscarDoctor(id); 
        if (doc != null) { 
            String conf = vista.leerTexto("¿Está seguro de que desea eliminar a " + doc.getNombre() + "? (S/N)"); 
            if (conf.equalsIgnoreCase("S")) { 
                perfilActual.eliminarDoctor(id); System.out.println("¡Éxito! El registro del médico ha sido removido."); 
            } else { 
                System.out.println("Acción cancelada."); 
            } 
        } else { vista.mostrarError("Doctor no encontrado con ese ID."); 

        } vista.pausar();
    }



//------------------ MODULO 3: CITAS MEDICAS ----------------------

    private void menuCitas() { 
        while (true) { 
            vista.mostrarEncabezado("Gestión de Citas Médicas"); 
            vista.mostrarTablaCitas(perfilActual.getListaCitas()); 
            System.out.println("\n¿Qué acción desea realizar?"); 
            System.out.println("[1] Programar una nueva cita"); 
            System.out.println("[2] Cancelar/Eliminar una cita futura"); 
            System.out.println("[3] Regresar al Menú Principal");
            String op = vista.leerTexto("Seleccione una opción (1-3)"); 
            switch (op) { 
                case "1": programarCita(); break; 
                case "2": cancelarCitaFutura(); break; 
                case "3": return; 
                default: vista.mostrarError("Opción no válida."); break; 
            } 
        } 
    }

    private void programarCita() { 
        vista.mostrarEncabezado("Gestión de Citas > Programar Nueva Cita"); 
        if (perfilActual.getListaDoctores().isEmpty()) { 
            vista.mostrarError("Debe registrar al menos un médico primero en la opción 2 del menú principal."); 
            vista.pausar(); 
            return; 
        } 
        String titulo = vista.leerTexto("1. Título de la cita (ej: Consulta General)"); 
        System.out.println("\n2. Seleccione el médico para la cita de la lista disponible:"); 
        for (int i = 0; i < perfilActual.getListaDoctores().size(); i++) { 
            Doctor d = perfilActual.getListaDoctores().get(i); 
            System.out.println(" [" + (i + 1) + "] " + d.getNombre() + " (" + d.getEspecialidad() + ")"); 
        } 
        int idxDoc = vista.leerEntero("Seleccione el número de médico") - 1; 
        Doctor doc = perfilActual.buscarDoctorPorIndice(idxDoc); 
        if (doc == null) { 
            vista.mostrarError("Selección de doctor inválida."); 
            vista.pausar(); 
            return; 
        } 
        LocalDateTime fechaHoraCita; 
        while (true) {
            try { String fStr = vista.leerTexto("3. Ingrese la fecha de la cita (DD/MM/AAAA)"); 
            String hStr = vista.leerTexto("4. Ingrese la hora de la cita (HH:MM - Formato 24h)"); 
            fechaHoraCita = LocalDateTime.parse(fStr + " " + hStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); 
            break; 
            } 
            catch (DateTimeParseException e) { 
                vista.mostrarError("Formato incorrecto. Respete estrictamente DD/MM/AAAA y HH:MM."); 
            } 
        }
        System.out.println("\nPROCESANDO CITA..."); 
        Cita cita = new Cita(titulo, doc, fechaHoraCita); 
        perfilActual.agregarCita(cita); 
        System.out.println("\n¡Éxito! Cita programada correctamente:"); 
        System.out.println("-> ID: " + cita.getCodigo()); 
        System.out.println("-> Título: " + cita.getTitulo()); 
        System.out.println("-> Médico: " + doc.getNombre()); 
        System.out.println("-> Horario: " + cita.getFechaHoraFormateada()); 
        vista.pausar(); 
    }

    private void cancelarCitaFutura() { 
        vista.mostrarEncabezado("Gestión de Citas > Cancelar Cita Futura"); 
        if (perfilActual.getListaCitas().isEmpty()) { 
            System.out.println("No hay citas registradas."); 
            vista.pausar(); 
            return; 
        } 
        String id = vista.leerTexto("Ingrese el ID de la cita que desea eliminar (ej: C-01) o 0 para cancelar"); 
        if (id.equals("0")) return; 
        Cita cita = perfilActual.buscarCita(id); 
        if (cita != null) { 
            if (!cita.esFutura(LocalDateTime.now())) { 
                vista.mostrarError("¡REGRA DEL SISTEMA!: No se pueden eliminar citas pasadas o del instante actual. Solo se permite cancelar citas futuras."); 
                vista.pausar(); 
                return;
            }
            System.out.println("\n¡CONFIRMACIÓN DE CANCELACIÓN!"); 
            System.out.println("¿Está seguro de que desea eliminar la siguiente cita?"); 
            System.out.println("[Cita a eliminar]: " + cita.getCodigo() + " - " + cita.getTitulo() + " con " + 
            cita.getDoctor().getNombre() + " (" + cita.getFechaHoraFormateada() + ")"); 
            String conf = vista.leerTexto("¿Confirmar eliminación? (S/N)"); 
            if (conf.equalsIgnoreCase("S")) { 
                System.out.println("Eliminando registro de la agenda..."); 
                perfilActual.eliminarCita(id); 
                System.out.println("¡Éxito! La cita \"" + cita.getCodigo() + " - " + cita.getTitulo() + "\" ha sido cancelada."); 
            } else { 
                System.out.println("Acción cancelada."); 
            } 
        } else { 
            vista.mostrarError("Cita no encontrada."); 
        } 
        vista.pausar();   
    }

    // MODULO 4: ACTIVIDAD FISICA

    private void menuActividades() { 
        while (true) { 
            vista.mostrarEncabezado("Menú Principal > 4. Notas de Salud: Actividad Física"); 
            vista.mostrarTablaActividades(perfilActual.getListaActividades()); 
            System.out.println("\n¿Qué acción desea realizar?"); 
            System.out.println("[1] Registrar una nueva actividad física"); 
            System.out.println("[2] Regresar al Menú Principal");
            String op = vista.leerTexto("Seleccione una opción (1-2)"); 
            switch (op) { 
                case "1": registrarActividad(); break; 
                case "2": return; default: vista.mostrarError("Opción incorrecta."); break; 
            } 
        } 
    }

    private void registrarActividad() { 
        vista.mostrarEncabezado("Registrar Actividad Física"); 
        LocalDate fechaAct; while (true) { 
            try { String fStr = vista.leerTexto("1. Ingrese la fecha de la actividad (DD/MM/AAAA)"); 
            fechaAct = LocalDate.parse(fStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
            if (fechaAct.isAfter(LocalDate.now())) { 
                vista.mostrarError("La fecha no puede ser futura. Registre una actividad de hoy o de días anteriores."); 
                continue; 
            } 
            break; 
            } 
            catch (DateTimeParseException e) { vista.mostrarError("Formato de fecha inválido. Use DD/MM/AAAA."); } 
        }
        System.out.println("\n2. Seleccione el tipo de actividad física realizada:"); 
        TipoActividad[] tipos = TipoActividad.values(); 
        for (int i = 0; i < tipos.length; i++) { 
            String nom = tipos[i].name().replace("_", " ").toLowerCase(); 
            nom = nom.substring(0, 1).toUpperCase() + nom.substring(1); 
            System.out.println(" [" + (i + 1) + "] " + nom); 
        } int idxTipo = vista.leerEntero("Seleccione una opción (1-" + tipos.length + ")") - 1;

        TipoActividad tipo = (idxTipo >= 0 && idxTipo < tipos.length) ? tipos[idxTipo] : TipoActividad.CAMINAR; 
        int dur = vista.leerEntero("3. Ingrese la duración de la actividad física (en minutos)"); 
        System.out.println("\n4. Seleccione el horario en el que realizó la actividad:"); 
        Horario[] hors = Horario.values(); 
        for (int i = 0; i < hors.length; i++) { 
            String nom = hors[i].name().toLowerCase(); 
            nom = nom.substring(0, 1).toUpperCase() + nom.substring(1); 
            System.out.println(" [" + (i + 1) + "] " + nom); 
        } 
        int idxHor = vista.leerEntero("Seleccione una opción (1-3)") - 1; 
        Horario horario = (idxHor >= 0 && idxHor < hors.length) ? hors[idxHor] : Horario.MANANA; 
        System.out.println("\nPROCESANDO REGISTRO..."); 
        ActividadFisica act = new ActividadFisica(fechaAct, tipo, dur, horario); 
        perfilActual.agregarActividad(act); 
        System.out.println("\n¡Éxito! Actividad registrada correctamente en tus notas de salud:"); 
        System.out.println("-> Fecha: " + act.getFechaFormateada()); 
        System.out.println("-> Actividad: " + act.getNombreActividad()); 
        System.out.println("-> Duración: " + act.getDuracionMinutos() + " minutos"); 
        System.out.println("-> Horario: " + act.getHorarioFormateado()); 
        vista.pausar();
    }

    // MODULO 5: NOTAS DE SALUD

    private void menuNotas() { 
        while (true) { 
            vista.mostrarEncabezado("5. Notas de Salud");
            vista.mostrarTablaNotas(perfilActual.getListaNotas()); 
            System.out.println("\n¿Qué acción desea realizar?"); 
            System.out.println("[1] Añadir una nueva nota de salud"); 
            System.out.println("[2] Regresar al Menú Principal"); 
            String op = vista.leerTexto("Seleccione una opción (1-2)"); 
            switch (op) { 
                case "1": anadirNota(); break; 
                case "2": return; 
                default: vista.mostrarError("Opción incorrecta."); break; 
            } 
        } 
    }

    private void anadirNota() { 
        vista.mostrarEncabezado("Notas de Salud > Añadir Nueva Nota"); 
        LocalDateTime ahora = LocalDateTime.now(); 
        System.out.println("Fecha y hora actuales del registro: " + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))); 
        String desc = vista.leerTexto("Ingrese la descripción de su estado de salud o síntomas actuales"); 
        if (desc.isEmpty()) { 
            vista.mostrarError("La descripción no puede estar vacía."); 
            return; 
        } 
        System.out.println("\nGUARDANDO NOTA..."); 
        NotaSalud nota = new NotaSalud(desc); 
        perfilActual.agregarNota(nota); 
        System.out.println("\n¡Éxito! Tu nota de salud ha sido guardada:"); 
        System.out.println("-> Registro: " + nota.getFechaHoraFormateada()); 
        System.out.println("-> Nota: \"" + nota.getDescripcion() + "\""); 
        vista.pausar(); 
    } 




}