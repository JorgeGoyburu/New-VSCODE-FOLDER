import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# ==========================================
# 1. EL ESCENARIO (VERSIÓN "CERCANA")
# ==========================================
R_TIERRA = 6371.0 

# Tu posición (sobre el eje X)
posicion_real_usuario = np.array([R_TIERRA, 0.0, 0.0])

# --- CAMBIO CLAVE: SATÉLITES MÁS CERCANOS ---
# En lugar de ponerlos a 20,000 km, los ponemos a unos 3,000 - 5,000 km de altura
# y más agrupados alrededor de tu posición (el lado positivo del eje X).
satelites = np.array([
    [9000,  3000,  3000],   # Satélite 1 (Arriba a la derecha)
    [9000, -3000,  2000],   # Satélite 2 (Arriba a la izquierda)
    [8000,  0,    -4000],   # Satélite 3 (Abajo)
    [10000, -1000, 1000]    # Satélite 4 (Casi encima)
], dtype=float)

# --- Simulación de Ruido ---
distancias_medidas_con_ruido = []
np.random.seed(10) # Nueva semilla

print("Simulando sistema de órbita baja...")

for sat in satelites:
    dist_exacta = np.linalg.norm(sat - posicion_real_usuario)
    # Reducimos un poco el error ya que estamos más cerca (proporcionalidad)
    error = 30.0 
    distancias_medidas_con_ruido.append(dist_exacta + error)

# ==========================================
# 2. CÁLCULO MATEMÁTICO (GRADIENTE)
# ==========================================

def calcular_gradiente_error(posicion_estimada):
    gradiente_total = np.array([0.0, 0.0, 0.0])
    error_cuadratico_total = 0.0
    
    for i, sat_pos in enumerate(satelites):
        vector_diferencia = posicion_estimada - sat_pos
        dist_est = np.linalg.norm(vector_diferencia)
        residuo = dist_est - distancias_medidas_con_ruido[i]
        error_cuadratico_total += residuo**2
        
        if dist_est > 0:
            gradiente_total += 2 * residuo * (vector_diferencia / dist_est)
            
    return gradiente_total, error_cuadratico_total

# ==========================================
# 3. OPTIMIZACIÓN
# ==========================================
# Empezamos la búsqueda desde el centro de la Tierra
posicion_actual = np.array([0.0, 0.0, 0.0])
historial_camino = [posicion_actual.copy()]

# Ajustamos parámetros para esta escala
Iteraciones = 4000      
Learning_rate = 0.01 # Un poco más rápido porque las distancias son menores

for k in range(Iteraciones):
    grad, error = calcular_gradiente_error(posicion_actual)
    posicion_actual = posicion_actual - (Learning_rate * grad)
    historial_camino.append(posicion_actual.copy())

posicion_final = posicion_actual
error_final = np.linalg.norm(posicion_final - posicion_real_usuario)
print(f"Posición Final Calculada con error de: {error_final:.2f} km")

# ==========================================
# 4. VISUALIZACIÓN "ZOOM IN"
# ==========================================
def crear_datos_esfera(centro, radio, resolucion=30):
    u = np.linspace(0, 2 * np.pi, resolucion)
    v = np.linspace(0, np.pi, resolucion)
    x = centro[0] + radio * np.outer(np.cos(u), np.sin(v))
    y = centro[1] + radio * np.outer(np.sin(u), np.sin(v))
    z = centro[2] + radio * np.outer(np.ones(np.size(u)), np.cos(v))
    return x, y, z

fig = plt.figure(figsize=(10, 8))
ax = fig.add_subplot(111, projection='3d')

# --- A. Tierra (Solo se verá una parte debido al zoom) ---
u, v = np.mgrid[0:2*np.pi:30j, 0:np.pi:15j]
x_tierra = R_TIERRA * np.cos(u)*np.sin(v)
y_tierra = R_TIERRA * np.sin(u)*np.sin(v)
z_tierra = R_TIERRA * np.cos(v)
ax.plot_wireframe(x_tierra, y_tierra, z_tierra, color="cyan", alpha=0.15)

# --- B. Elementos ---
ax.scatter(satelites[:,0], satelites[:,1], satelites[:,2], c='red', s=80, marker='^', label='Satélites (Low Orbit)')
ax.scatter(posicion_real_usuario[0], posicion_real_usuario[1], posicion_real_usuario[2], c='green', s=200, marker='*', zorder=10, label='TÚ (Objetivo)')

# --- C. Esferas de Intersección (Más pequeñas) ---
colores = ['red', 'orange', 'purple']
for i in range(3):
    # Distancia exacta para visualización perfecta
    radio_exacto = np.linalg.norm(satelites[i] - posicion_real_usuario)
    
    X_sph, Y_sph, Z_sph = crear_datos_esfera(satelites[i], radio_exacto)
    ax.plot_surface(X_sph, Y_sph, Z_sph, color=colores[i], alpha=0.1, rstride=2, cstride=2)

# --- D. Camino del Algoritmo ---
historial_camino = np.array(historial_camino)
ax.plot(historial_camino[:,0], historial_camino[:,1], historial_camino[:,2], c='blue', linewidth=2, label='Camino')

# --- E. LIMITES DEL GRÁFICO (ZOOM) ---
# Aquí es donde hacemos "Zoom" para que se vea más detallado
ax.set_xlabel('X')
ax.set_title('Trilateración Local (Satélites Cercanos)')

# Nos enfocamos en el cuadrante positivo donde está la acción
limit_min = -2000
limit_max = 12000
ax.scatter(historial_camino[0,0], historial_camino[0,1], historial_camino[0,2], c='black', s=50, label='Inicio Algoritmo')
ax.set_xlim([limit_min, limit_max])
ax.set_ylim([-6000, 6000])
ax.set_zlim([-6000, 6000])

plt.legend()
plt.show()