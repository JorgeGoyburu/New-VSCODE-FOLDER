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
temperaturas_filtradas = [
        t for t in primeros_dias
        if t <-60 or t >60
    ]
def reporte_temperaturas(temperaturas):
    primeros_dias = temperaturas[:5]
    promedio_real = sum(temperaturas_filtradas) / len(temperaturas_filtradas)
    





