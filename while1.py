datos = ["Ana,00A1,ciudad,90,70,80,edad,faltas,horas,carrera"]
dic = {}
for registro in datos:
    lista = registro.split(",")
    par, fin, mejo = float(lista[3]), float(lista[4]), float(lista[5])
    dic[lista[1]]= (par+fin+mejo)/3
print(dic)


datos = ["Ana,00A1,ciudad,90,70,80,edad,faltas,horas,ing"]
dic2={}
for registro in datos:
    carrera = registro.split(",")[9]
    if carrera not in dic2:
        dic2[carrera] = 0
    dic2[carrera] += 1
print(dic2)


datos = ["Ana,00A1,ciudades,90,70,80,edad,faltas,horas,ing", 
         "Jorge,00A1,guayaquil,90,70,80,12,faltas,horas,ing",
         "Luis,00A1,quito,90,70,80,16,faltas,horas,ing",
         "Joel,00A1,guayaquil,90,70,80,20,faltas,horas,ing"]
dic = {}
for registro in datos:
    ciudad, edades = registro.split(",")[2], registro.split(",")[6]
    if ciudad not in dic:
        dic[ciudad]=[]
    dic[ciudad].append(edades)
print(dic)

datos = ["Ana,00A1,ciudades,90,70,80,edad,faltas,horas,ing,email,fecha,genero,estado,tipo,beca,si",
         "Ana,00A1,ciudades,90,70,80,edad,faltas,horas,ing,email,fecha,genero,estado,tipo,beca,no",
         "Ana,00A1,ciudades,90,70,80,edad,faltas,horas,ing,email,fecha,genero,estado,tipo,beca,no",
         "Ana,00A1,ciudades,90,70,80,edad,faltas,horas,ing,email,fecha,genero,estado,tipo,beca,si"]
dic = {"si": 0, "no" :0}
for club in datos:
    respuesta = club.split(",")[-1]
    dic[respuesta]+=1
total = sum(list(dic.values()))     #esta sumando todos los valores que hay entre si o no que serian las cantidad de personas que hay en la lista para que se divida con la cantidad de si y no
porcentaje_si = (dic["si"]*100)/total
procentaje_no = (dic["no"]*100)/total
print(dic)
print(porcentaje_si, procentaje_no)




#########    virtual     ###################

chanchito = [1,5,10,10,10,25,50,50,1,10,25,50,50,50,50,25,25,10,5,5,10,50,1,5,10,10,10,25,50,50,1,10,25,50,50,50,50,25,25,10,5,5,10,50,1,5,10,10,10,25,50,50,1,10,25,50,50,50,50,25,25,10,5,5,10,50]


def contadorMonedas(lista):
    conteo={}
    for moneda in lista:
        if moneda in conteo:
            conteo[moneda] += 1
        else:
            conteo[moneda] = 1
    return conteo
print(contadorMonedas(chanchito))

def contarDolares(lista):
    dolares={}
    for moneda in lista:
        valor = moneda / 100
        if moneda in dolares:
            dolares[moneda]+= round(valor, 2)
        else:
            dolares[moneda] = round(valor, 2)
    return dolares
print(contarDolares(chanchito))

resultadoConteo = contadorMonedas(chanchito)
for denom, cant in resultadoConteo.items():
    print(f"Moneda de {denom} centavos: {cant} monedas")

resultadoDolares = contarDolares(chanchito)
for denom, cant in resultadoDolares.items():
    print(f"Moneda de {denom} centavos: ${round(cant, 2)}")

total = sum(list(resultadoDolares.values()))
print()
print(f"Total ahorrado en la alcancia: ${round(total, 2)}")





##########################      Cesta de compras        ##########################
canasta={}
decision= input("¿Desea agregar elementos?")
while decision.lower() == "si":
    producto = input("ingrese el nombre del producto:")
    precio = input("ingrese el precio:")
    canasta[producto]= precio
    print()
    decision= input("¿Desea agregar elementos?")

print(canasta)
print()

contador = 0

for producto, precio in canasta.items():
    print(f"{producto}\t {precio}")
    contador += float(precio)

print(f"total: {contador}")




###########     Alcancia Virtual        ###############

ahorrar = input('Cuanto desea ahorra?: ') #40

while not ahorrar.isdigit():
    print('el valor debe ser un numero')
    ahorrar = input('Cuanto desea ahorra?: ') #40
ahorrar = int(ahorrar)

monedero = 0 #

while monedero < ahorrar: #truen
    #solicita dinero
    pregunta = int(input('cuanto desea almacenar?: ')) #10
    monedero += pregunta
print(f"Objetivo conseguido haz ahorrado {monedero}")


import random as rd
l_premios = [ 'Carro', 'Moto', 'Laptop', 'Chocolate', 'Reloj', 'Teclado', 'Celular' ]
vida = 2
contador = 0
adivinar = rd.sample(l_premios, k=3)

    #condicion 1    #condicion2
while vida >0 and contador<3:
            #se ingresa el premio que escoja el usuario
    texto= input(f"vidas: {vida}, ingrese un premio: ")
    if texto in adivinar: #condicional
        print('adivino')
        contador += 1
    else:
        print('no adivino')
        vida -= 1

if contador <3: #condicion final del resultado
    print('perdio')
else:
    print(f'gano {adivinar}')


datos = {}
Estudiante = input('Ingrese nombre del estudiante: ').title()
while Estudiante != 'Salir':
    if Estudiante not in datos:
        datos[Estudiante] = 1
    else:
        datos[Estudiante]+= 1
    Estudiante = input('Ingrese nombre del estudiante: ').title()

nombres = list(datos.keys())
nombres.sort()
print (nombres)

for nombre in nombres:
    valor = datos[nombre]
    print(f'* {nombre}: {valor}')

valores = datos.values()
promedio = (sum(valores)/len(valores))
print('estudiante con cant de inasistencias superior al promedio: ')
for nombre, valor in datos.items():
    if valor > promedio:
        print(f'*{nombre}: {valor}')









