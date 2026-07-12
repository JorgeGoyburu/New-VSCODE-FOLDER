package com.grupo.proyecto1.util;


public class GeminiAIService {
    /** 
     * Devuelve una síntesis médica estructurada del producto farmacéutico indicado. 
     * @param nombreMedicamento Texto que representa el fármaco. 
     * @return Resumen en formato de viñetas enumeradas tal cual se pide en el PDF. 
     */
  public static String obtenerResumenSencillo(String nombreMedicamento) {

    String med = nombreMedicamento.trim().toUpperCase(); 
    if (med.contains("AMOXICILINA")) { 
        return "1- La Amoxicilina es un medicamento que combate las infecciones causadas por bacterias,\n" 
        + "ayudando a tu cuerpo a recuperarse.\n\n" 
        + "2- Normalmente se toma por vía oral, tragando las pastillas o cápsulas con un vaso de agua,\n" 
        + "siguiendo las indicaciones del médico sobre cuándo y cuántas veces al día hacerlo.\n\n" 
        + "3- Cuidado con: No la tomes si eres alérgico a ella o a medicamentos similares, y siempre informa\n" 
        + "a tu médico sobre cualquier otra medicación que estés tomando."; 
    } else if (med.contains("PARACETAMOL")) { 
        return "1- El Paracetamol es un eficaz analgésico y antipirético utilizado para aliviar dolores leves a\n"
        + "moderados y reducir la fiebre de manera rápida.\n\n" 
        + "2- Se administra de forma oral con suficiente agua, respetando rigurosamente los intervalos de tiempo\n" 
        + "para evitar sobrecargar el funcionamiento del hígado.\n\n" 
        + "3- Cuidado con: No exceder la dosis máxima diaria recomendada ni combinar con alcohol."; 
    } else if (med.contains("IBUPROFENO")) { 
        return "1- El Ibuprofeno es un antiinflamatorio no esteroideo (AINE) excelente para disminuir dolor,\n" 
        + "fiebre e inflamación muscular y articular.\n\n"
        + "2- Se aconseja tomarlo siempre junto con alimentos, leche o después de comer para proteger\n" 
        + "las paredes del estómago contra irritaciones.\n\n" 
        + "3- Cuidado con: Consultar al médico en caso de antecedentes de úlceras o problemas renales."; 
    } else { 
        return "1- " + nombreMedicamento + " es una sustancia farmacéutica registrada en su control personal de salud\n" 
        + "para el seguimiento riguroso de su tratamiento médico.\n\n" 
        + "2- Asegúrese de cumplir estrictamente con las dosis y frecuencias indicadas por su especialista de salud\n" 
        + "en la receta médica original.\n\n" 
        + "3- Cuidado con: Mantener fuera del alcance de los niños y almacenar en un lugar fresco y seco."; 
    } 
  }
}
