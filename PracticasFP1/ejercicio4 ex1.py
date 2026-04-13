def comparar_cadenas(cadena_a,cadena_b):
    cadena_a = cadena_a.lower()
    cadena_b = cadena_b.lower()
    cadena_c = ""
    for indice in range(len(cadena_a)):
        caracter_a = cadena_a[indice]
        caracter_b = cadena_b[indice]
        caracter_c = ""
        if caracter_b not in cadena_a:
            caracter_c = "!"
        elif caracter_b == caracter_a:
            caracter_c = "@"
        elif caracter_c in caracter_a:
            caracter_c = "#"
        cadena_c += caracter_c
    return cadena_c
print("asdfrt")
print("adsdwe")
print(comparar_cadenas("asdfrt","adsdwe"))