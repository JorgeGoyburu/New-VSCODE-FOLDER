import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# ==========================================
# 1. EL ESCENARIO (LA VERDAD DEL SISTEMA)
# ==========================================
# Usaremos kilómetros como unidad.
R_TIERRA = 6371.0 

# --- Definir dónde estás REALMENTE ---
# Pongamos al usuario en un punto sobre el ecuador.
posicion_real_usuario = np.array([R_TIERRA, 0.0, 0.0])

# --- Definir los Satélites ---
# Colocamos 4 satélites en órbitas lejanas (aprox 20,000 km más arriba)
# Deben estar bien distribuidos en el cielo para que funcione bien.
satelites = np.array([
    [15000, 15000, 15000],
    [-15000, 15000, -15000],
    [15000, -15000, 10000],
    [-10000, -10000, 20000]
], dtype=float)

print("Calculando distancias y simulando errores atmosféricos...")

# --- Simular las Señales (Mediciones con Ruido) ---
distancias_medidas = []
np.random.seed(42) # Semilla fija para que el resultado sea reproducible

for sat in satelites:
    # Distancia geométrica perfecta
    dist_exacta = np.linalg.norm(sat - posicion_real_usuario)
    
    # AGREGAMOS RUIDO: Simulamos error atmosférico/reloj
    # Un error aleatorio de hasta +/- 50 km (exagerado para visualizar)
    error = 0
    distancias_medidas.append(dist_exacta + error)

# ==========================================
# 2. EL CEREBRO MATEMÁTICO (CÁLCULO VECTORIAL)
# ==========================================

def calcular_gradiente_error(posicion_estimada):
    """
    Esta función es el núcleo del cálculo.
    Calcula qué tan lejos estamos (la función de Costo/Error)
    y Hacia dónde movernos para mejorar (el Gradiente).
    """
    gradiente_total = np.array([0.0, 0.0, 0.0])
    error_cuadratico_total = 0.0
    
    for i, sat_pos in enumerate(satelites):
        # Vector desde mi estimación actual al satélite
        vector_diferencia = posicion_estimada - sat_pos
        
        # Distancia estimada actual
        dist_est = np.linalg.norm(vector_diferencia)
        
        # El "residuo": diferencia entre lo que mido y lo que estimo
        # Si esto es 0, la esfera pasa exactamente por mi punto.
        residuo = dist_est - distancias_medidas[i]
        
        # Sumamos al error total (para monitorear)
        error_cuadratico_total += residuo**2
        
        # --- CÁLCULO DEL GRADIENTE ---
        # Esta es la derivada vectorial de la función de error.
        # Nos dice la dirección en la que el error crece.
        # Fórmula: 2 * (dist_est - dist_medida) * (vector_unitario_hacia_satelite)
        if dist_est > 0:
            gradiente_parcial = 2 * residuo * (vector_diferencia / dist_est)
            gradiente_total += gradiente_parcial
            
    return gradiente_total, error_cuadratico_total

# ==========================================
# 3. EL BUCLE DE SIMULACIÓN (OPTIMIZACIÓN)
# ==========================================

# A. Adivinanza Inicial (Centro de la Tierra)
posicion_actual = np.array([0.0, 0.0, 0.0])

historial_camino = [posicion_actual.copy()]

# --- PARÁMETROS CORREGIDOS ---
Iteraciones = 5000      # Damos 6000 pasos para asegurar que llegue
Learning_rate = 0.0005  # Velocidad de avance

print(f"Iniciando simulación... Distancia a recorrer: {R_TIERRA} km")

for k in range(Iteraciones):
    grad, error = calcular_gradiente_error(posicion_actual)
    
    # Descenso de Gradiente
    posicion_actual = posicion_actual - (Learning_rate * grad)
    
    # Guardamos cada paso
    historial_camino.append(posicion_actual.copy())
    
    # Monitoreo
    if (k+1) % 1000 == 0:
        dist = np.linalg.norm(posicion_actual - posicion_real_usuario)
        print(f"Paso {k+1}: Distancia restante al objetivo = {dist:.2f} km")

# Al final debería estar extremadamente cerca


posicion_final = posicion_actual
print("\n--- Simulación terminada ---")
print(f"Posición Real Objetivo: {posicion_real_usuario}")
print(f"Posición Final Calculada: {np.round(posicion_final, 2)}")
error_final = np.linalg.norm(posicion_final - posicion_real_usuario)
print(f"Error final de posición: {error_final:.2f} km (debido al ruido introducido)")


# ==========================================
# 4. VISUALIZACIÓN 3D
# ==========================================
print("Generando gráfico 3D...")
fig = plt.figure(figsize=(10, 8))
ax = fig.add_subplot(111, projection='3d')

# --- A. Dibujar la Tierra (Esfera de alambre) ---
u, v = np.mgrid[0:2*np.pi:20j, 0:np.pi:10j]
x_tierra = R_TIERRA * np.cos(u)*np.sin(v)
y_tierra = R_TIERRA * np.sin(u)*np.sin(v)
z_tierra = R_TIERRA * np.cos(v)
ax.plot_wireframe(x_tierra, y_tierra, z_tierra, color="cyan", alpha=0.3, label="Tierra")

# --- B. Dibujar Elementos ---
# Satélites
ax.scatter(satelites[:,0], satelites[:,1], satelites[:,2], c='red', s=100, marker='^', label='Satélites')

# Posición Real (Objetivo)
ax.scatter(posicion_real_usuario[0], posicion_real_usuario[1], posicion_real_usuario[2], c='green', s=150, marker='*', label='Tu Posición Real')

# --- C. Dibujar el CAMINO DE LA SIMULACIÓN ---
historial_camino = np.array(historial_camino)
# El camino (línea azul)
ax.plot(historial_camino[:,0], historial_camino[:,1], historial_camino[:,2], c='blue', linewidth=2, label='Camino de Optimización (Gradiente)')
# Punto de inicio (centro)
ax.scatter(historial_camino[0,0], historial_camino[0,1], historial_camino[0,2], c='black', s=50, label='Inicio Algoritmo')
# Punto final (calculado)
ax.scatter(posicion_final[0], posicion_final[1], posicion_final[2], c='blue', s=100, label='Posición Calculada Final')


# --- D. Configuración del Gráfico ---
ax.set_xlabel('X (km)')
ax.set_ylabel('Y (km)')
ax.set_zlabel('Z (km)')
ax.set_title('Simulación de GPS: Optimización por Descenso de Gradiente')
# Ajustar límites para que se vea todo
ax.set_xlim([-20000, 20000])
ax.set_ylim([-20000, 20000])
ax.set_zlim([-20000, 20000])
plt.legend()

print("¡Gráfico listo! Interactúa con él usando el mouse.")
plt.show()