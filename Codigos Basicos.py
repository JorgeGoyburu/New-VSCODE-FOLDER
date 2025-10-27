


#Indexacion con datos compuestos
nombre_usuario = input('Ingrese su nombre:')
print('la ultima letra de su nombre es:', nombre_usuario[-1]) #imprime la ultima letra del nombre ingresado


#slicing
nombre_usuario2 = input('Ingrese su nombre:')
print(nombre_usuario2[0:-1]) #De caracter 0 hasta 1 antes del -1, osea no se imprime el ultimo caracter
print(len(nombre_usuario2))
print(nombre_usuario2[0:len(nombre_usuario2)]) #De caracter 0 hasta len(variable) que es el ultimo caracter


# uso de "in"
x = 'hola mundo'
print('m'in x)  # true
print('z'in x)  # false


#operadores de concatenacion
saludo = 'hola'
nombre_usuario3 = input('ingrese su nombre:')
primer_parcial = int(input('ingrese su nota del primer parcial:')) #se espera un entero
segundo_parcial = int(input('ingrese su nota del segundo parcial:')) 
promedio = (primer_parcial + segundo_parcial)/2 #operacion aritmetica
mensaje = f"{saludo} {nombre_usuario3}, tu proemdio es: {promedio}"
print(mensaje)



#####################################################################################################################
################################# FUNCIONES CON CADENA DE CARACTERES ################################################
#####################################################################################################################



cadena = input('ingrese la palabra que quiera analizar:')
# Comprueba si la subcadena de índice 1 a 4 ("bcd") termina con "cd"
if cadena.endswith("cd"): #si el rango que damos no incluye el final, no se toma en cuenta (se toma como un false)
    print("La subcadena dentro del rango termina con 'cd'")
else: 
    print("La subcadena dentro del rango no termina con 'cd'")
# Salida: La subcadena dentro del rango termina con 'cd'

palabras_usuario = input('ingrese la lista de la palbras que desea analizar(separadas por comas):')
palabras = [palabra.strip() for palabra in palabras_usuario.split(',')]
resultados = [palabra.isalpha() for palabra in palabras]
print('resultados:')
for palabra, resultados in zip(palabras, resultados):
    print(f"{palabra}, {resultados}")  # Imprime: [True, False, True]

