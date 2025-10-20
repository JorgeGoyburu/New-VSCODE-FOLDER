import random

################################################################
#                         Funciones                            #
################################################################
def duplicar_entero(n):
  doble = n*2
  return doble

def doble_triple(n):
  doble = n*2
  triple = n*3
  return doble,triple

def get_info(direccion):
  lista=direccion.upper().split(".")
  i_uni=lista.index("EDU")-1
  universidad=lista[i_uni]
  condicion=lista[-1]=="EC"
  return universidad,condicion

def adivinar_dia(dia):
  dias=["lunes","martes","miercoles","jueves","viernes","sabado","domingo"]
  dia_generado=random.choice(dias)
  condicion=dia.lower()==dia_generado
  return dia_generado,condicion

def generar_mensaje(nombre, id,  duracion):
  inicio=int(id[:4])
  fin=inicio+duracion
  mensaje=f"Hola {nombre}, se espera que te gradúes en {fin}."
  return mensaje

def total_segundos(hora):
  horas, minutos, segundos = hora.split(":")
  total_segundos = int(horas) *60*60 + int(minutos) * 60 + int(segundos)
  return total_segundos

def  info_estudiante(datos):
  lista=datos.split(",")
  nombre=lista[0].title()
  ciudad=lista[2].upper()
  final =float(lista[4])
  beca  =lista[-2]
  mensaje=f"{nombre} es de {ciudad}. Su calificación en final fue {final:.2f} y en beca registra: {beca}."
  return mensaje

def extraer_articulos(texto):
  texto_filtrado=texto.lower().replace(",","").replace(".","").replace(";","")
  lista=texto_filtrado.split()
  un = lista.count("un")
  uno= lista.count("uno")
  una= lista.count("una")
  la = lista.count("la")
  lo = lista.count("lo")
  return un,uno,una,la,lo


################################################################
#                            MAIN                              #
################################################################
print('Inicio del programa')
x=3
numero = duplicar_entero(x)
print(f'La funcion duplicar_entero({x}) devuelve: {numero}.')
y=10
numero = duplicar_entero(y)
print(f'La funcion duplicar_entero({y}) devuelve: {numero}.')

numero1,numero2 = doble_triple(numero)
print(f"El doble de {numero} es {numero1}, y el triple es {numero2}.")


print("\n")
print("Ejercicio 1: get_info")
url = "www.Stanford.edu"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)
url = "www.FIEC.espol.edu.ec"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)
url= "www.unam.edu.mx"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)


print("\n")
print("Ejercicio 2: adivinar_dia")
dia= input("Estoy pensando en un día de la semana (sin tildes), trata de adivinarlo: ") 
generado,condicion = adivinar_dia(dia)
print(f"Estaba pensando en el día: {generado}, tu pensaste: {dia}.")
print(f"Has adivinado: {condicion}")


print("\n")
print("Ejercicio 3: generar_mensaje")
nombre=input("Ingresa nombre: ")
matricula=input("Ingresa Matricula: ")
tiempo=5
print(generar_mensaje(nombre,matricula,tiempo))


print("\n")
print("Ejercicio 4: total_segundos")
hora=input("Ingresa hora en formato hh:mm:ss ")
segundos=total_segundos(hora)
print(f"{hora} tiene {segundos} segundos en total")


print("\n")
print("Ejercicio 5: info_estudiante")
texto1="DIANA,estudiante025,Loja,66,64,67,21,3,9,Ingeniería Ambiental,diana@ejemplo.com,2002-02-28,F,activa,merito,SI"
texto2="GABRIEL,estudiante024,Milagro,48,52,60,22,5,6,Psicología,gabriel@ejemplo.com,2001-06-06,M,activa,ninguna,NO"
texto3="ESTEFANY,estudiante027,Ambato,59,62,65,19,2,12,Economía,estefany@ejemplo.com,2004-04-17,F,activa,merito,SI"
texto4="LUIS,estudiante005,Quito,55,50,58,23,4,9,Ingeniería Eléctrica,luis@ejemplo.com,2000-10-30,M,activa,economica,NO"
texto5="NATALIA,estudiante013,Ambato,67,65,66,21,3,7,Enfermería,natalia@ejemplo.com,2002-01-01,F,activa,merito,SI"
texto6="ISABEL,estudiante011,Loja,88,90,0,19,0,14,Biología,isabel@ejemplo.com,2004-03-17,F,activa,merito,SI"
mensaje=info_estudiante(texto1)
print(mensaje)
mensaje=info_estudiante(texto2)
print(mensaje)
mensaje=info_estudiante(texto4)
print(mensaje)


print("\n")
print("Ejercicio 6: extraer_articulos")
parrafo=input("Ingrese un parrafo: ")
un,uno,una,la,lo=extraer_articulos(parrafo)
print("El parrafo tiene:")
print(f"{un} artículos 'un'")
print(f"{uno} artículos 'uno'")
print(f"{una} artículos 'una'")
print(f"{la} artículos 'la'")
print(f"{lo} artículos 'lo'")