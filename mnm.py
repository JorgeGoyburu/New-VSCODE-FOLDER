adolescentes= 0
edad= int(input("ingrese edad:"))
if 12 <= edad <=19:
    adolescentes +=1
print(adolescentes)


# If msj

mes = int(input("ingrese el # de mes (1-12):"))
if mes == 4 or 6 or 9 or 11:
    print("El mes tiene 30 dias")
else: print("El mes tiene 28 dias")


#if operaciones
x =2
y= 0
if x == 2:
    y += 5
    print(y)

# ejemplo if y else
nota = float(input("ingrese su calificacion:"))
if nota >= 60:
    print("Aprobado")
else: print("Reprobado")
print("fin")

#Ejercicio adivinar
import random
lista_1 = ["amarillo", "rojo","verde","azul"]
azar = random.choice(lista_1)
usuario = input(f"adivine el color {lista_1}:")
if azar == usuario:
    print("la palabra era",azar,"usted gana" )
else: 
    print("la palabra era", azar, "usted pierde")

