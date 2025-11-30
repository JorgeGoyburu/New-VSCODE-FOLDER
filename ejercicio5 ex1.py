def es_cuadrado_perfecto(numero):
    if numero >= 1:
        raiz = int(numero**0.5)
        print(f"Raiz es {numero**0.5}")
        print(f"Raiz sin decimales es es {raiz}")
        if raiz*raiz == numero:
            return True
        else:
            return False
print(es_cuadrado_perfecto(10))