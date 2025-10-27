texto = input("Ingrese un texto: ")

#formato del texto : 'materia: programacion, calificacion: 90'

materia = texto[8:texto.index(',')] # se pone la ubibacion de donde se va encontrar la materia en este caso [desde el 8vo caracter : ',']
calificaion = float(texto[texto.index(':',8)+1 : ]) # [en este caso se busca ':' a partir del 8vo caracter mas 1 caracter despues : hasta el final de la cadena]
porcentaje = 80
mensaje =print(f"calificacion de {materia} es: {calificaion*porcentaje/100}")
