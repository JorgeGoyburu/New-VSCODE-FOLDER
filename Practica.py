#CALCULAR NOTAS FINALES DE ES ESTUDIANTES
def calcular_promedio(notas):
    suma = 0
    for x in notas:
        suma += x
    promedio = round(suma / len(notas), 2)
# Determinar si aprueba o reprueba
    if promedio >= 7:
        estado = "APROBADO"
    else:
        estado = "REPROBADO"
    return promedio, estado

#programa principal
est1=[8,7.5,9]
est2=[6,5,4]
est3=[10,9,9.5]

for estudiante in [est1,est2,est3]:
    prom, est =calcular_promedio(estudiante)
    print(f"Promedio:{prom} → Estado:{est}")





#REPORTE DE TEMPERATURAS
datos = [43, 46, 68, 42, 47, 48, 40, 39, 38, 35]
primero_dias = datos[:5]
temperaturas_filtradas = [
        t for t in primero_dias
        if t >-60 and t <60
            ]
contador_45 = [k for k in temperaturas_filtradas if k > 45]

def calcular_promedio(temperaturas):
    suma = sum(temperaturas)
    promedio = round(suma / len(temperaturas), 2)
    return promedio

promedio_temp = calcular_promedio(temperaturas_filtradas)
contador = len(contador_45)
print('Las temperaturas registradas en los primeros 5 dias son:', primero_dias)
print(f'los datos procesdados de los primeros 5 dias son: {temperaturas_filtradas}')
print(f'la cantidad de dias con temperatura mayor a 45°C son: {contador}')
print(f"Temperatura promedio real: {promedio_temp}°C")





################3

def cambiar_repetidos (texto, caracter_reemplazo= '*'):
    palabra_nueva = ''
    vistas= [] 
    for letra in texto:
        if letra.lower() in vistas:
            palabra_nueva += caracter_reemplazo
        else:
            palabra_nueva += letra
            vistas.append(letra.lower())	            
            return palabra_nueva
                    
texto= "Ejercicios"
texto_editado=cambiar_repetidos(texto,"-")
print(texto_editado)
    
######a####
mensajexd = "Hola mundo"
vocales = "aeiou"
contador = mensajexd[1]
print(contador)
mensajexd = mensajexd.lower()
palabras_separadas = mensajexd.split(" ")
print(palabras_separadas)
print(palabras_separadas[1])
for vocal in vocales:
    mensajexd = mensajexd.replace(vocal, "")
print(mensajexd)























    





