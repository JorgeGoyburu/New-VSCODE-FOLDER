
def devolver_numeroo(dato):
    if dato.count('.') >1:
        return 0.0
    numero_sin_punto = dato.replace(".", "")
    
    if len(numero_sin_punto) > 0 and numero_sin_punto.isdigit():
        return float(dato)
    else:
        return 0.0
dato = "1.234,56,78,90,h.1,.2,3.,..4"
lista_datos = dato.split(',')
litada_fina = []

for elemento in lista_datos:
    valor = devolver_numeroo(elemento)
    litada_fina.append(valor)
print(litada_fina)