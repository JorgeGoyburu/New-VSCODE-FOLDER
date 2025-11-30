import random
mensaje = "La inteligencia artificial transforma la forma en la que trabajamos, aprendemos y nos comunicamos en linea cada dia."
signos = ".,;:!?"
for signo in signos:
    mensaje = mensaje.replace(signo, "")

#Minusculas
mensaje = mensaje.lower()

#Separar palabras
palabras = mensaje.split(" ")

#################
# ELIMINAR REPETIDOS
palabras_unicas = list(set(palabras))
print(palabras_unicas)
#################

seleccionados = random.sample(palabras_unicas, 15)
seleccionados.sort()


############### 
ultima_palabra = seleccionados[-1]
if len(ultima_palabra) < 7:
    cantidad = 7- len(ultima_palabra)
    ultima_palabra = "_"*cantidad + ultima_palabra
    print(ultima_palabra)
seleccionados[-1] = ultima_palabra


################################
for indice in range(len(seleccionados)):
    if indice%2 == 0:
        seleccionados[indice] = seleccionados[indice].upper()

print(seleccionados)
print(seleccionados[0])