#Cesta de compras
canasta={}
decision= input("¿Desea agregar elementos?:")
while decision.lower() == "si":
    producto = input("ingrese el nombre del producto:")
    precio = float(input("ingrese el precio:"))
    canasta[producto]= precio
    print()
    decision= input("¿Desea agregar elementos?")

print(canasta)
print()

contador = 0

for producto, precio in canasta.items():
    print(f"{producto}\t {precio}")
    contador += precio

print(f"total : {contador}")

###########################################################
################### Reporte de materias ###################
###########################################################

def promediar_por_materia(dic):
    suma_por_materia = {}
    conteo_notas_suamadas = {}

    for estudiante in dic:
        for materia in dic[estudiante]:
            nota = dic[estudiante][materia]

            if materia in suma_por_materia:
                suma_por_materia[materia] += nota
                conteo_notas_suamadas[materia] += 1
            else:
                suma_por_materia[materia] = nota
                conteo_notas_suamadas[materia] = 1
    resultado = {}
    for materia in suma_por_materia:
        resultado[materia] = suma_por_materia[materia]/conteo_notas_suamadas[materia]
    
    return resultado

notas = {
"Ana": {"Matemáticas": 8, "Historia": 9},
"Luis": {"Matemáticas": 6, "Historia": 7, "Biología": 10},
"María": {"Matemáticas": 10, "Biología": 9}
}

print(promediar_por_materia(notas))
