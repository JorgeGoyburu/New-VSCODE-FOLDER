#EJERCICIO 1

texto = input("Ingrese un texto: ")

#formato del texto : 'materia: programacion, calificacion: 90'

materia = texto[8:texto.index(',')] # se pone la ubibacion de donde se va encontrar la materia en este caso [desde el 8vo caracter : ',']
calificaion = float(texto[texto.index(':',8)+1 : ]) # [en este caso se busca ':' a partir del 8vo caracter mas 1 caracter despues : hasta el final de la cadena]
porcentaje = 80
mensaje =print(f"calificacion de {materia} es: {calificaion*porcentaje/100}")



#EJERCICIO 2
#Escribe un programa en Python que determine si una palabra o frase es un palíndromo, es decir, que se lee igual de izquierda a derecha yde derecha a izquierda. El programa debe 
# ignorar los espacios y no considerar las diferencias entre mayúsculas y minúsculas


palabra = input('ingrese una palabra:')
procesar = palabra.lower().replace(" ", "") #en el "lower" convierte la palababra/frase ingresada en minusculas, y el "replace" esta remplazando los espacios de la frase a que no tenga espacios
condicion = procesar == procesar[::-1]  #la condicion es que la frase/plabara ya procesada sea igual a su inversa "[::-1]"
print(f"la palabra {palabra} es polindroma? {condicion}") #imprime la palabra y si es true o false la condicion


#EJERCICIO 3
#Escribe un programa que solicite al usuario su nombre, apellido y año de nacimiento. Con esa información, el programa debe calcular suedad y luego generar un correo electrónico usando el siguiente formato:
# nombre.apellido{edad}@gmail.com
# Todo debe ir en minúsculas, sin espacios y sin acentos. Ejemplo:danilo.herrera20@gmail.com

#Ejemplo de entrada:
#Ingrese su nombre: Danilo
#Ingrese su apellido: Herrera
#Ingrese su año de nacimiento: 2005
#Salida esperada:
#Su correo es:
#danilo.herrera20@gmail.com

nombre = input('ingrese su nombre:')
apellido = input('ingrese su apellido:')
birth = int(input('ingrese su año de nacimiento:'))
edad = 2025-birth
correo = f'{nombre.lower()}.{apellido.lower()}{str(edad)}@gmail.com'
print(f'su correo es:{correo}')



#EJERCICIO 5  Recortar una frase larga a solo 20 caracteres y agregar "..."

frase = input('ingrese una frase larga:')
new_frase = frase[:20] + "..."
print(new_frase)

#EJERCICIO 6 Cifrar una palabra con numeros

pal = input('ingrese una palabra:')
pal = pal.upper()
new_pal = pal.replace('A','4').replace('B', '8').replace('O','0').replace('I','1').replace('E','3')
new_pal = new_pal[:2] + new_pal
print(new_pal)




#EJERCICIO 7

url = input('ingrese un URL: ')
condition = (
    url.startswith('https://') and
    url.count('.') >=1 and
    (url.endswith('.com') or url.endswith('.org') or url.endswith('.edu'))
)
print(f'El URL ingresado es valido? {condition}')




#EJERCICIO 8

inversion = float(input('ingrese la cantidad que va a invertir $: '))
















