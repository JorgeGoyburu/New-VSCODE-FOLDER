def cambiar_repetidos(texto, caracter_remplazo = '*'):
    palabra_nueva = ''
    vistas = []
    for letra in texto:
        if letra.lower() in vistas:
            palabra_nueva += caracter_remplazo
        else:
            palabra_nueva += letra
            vistas.append(letra.lower())
    return palabra_nueva

print(cambiar_repetidos("ejercicioc", caracter_remplazo="-"))

    
lista = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

for indice, valor in enumerate(lista):
    print(f"{valor}, tiene el indice, {indice}")


