import random

def juego_pesca():
    buenas = 0
    malas = 0

    for i in range(1, 6):
        decision = input(f"Intento {i} de 5 – ¿Quieres pescar? (si/no): ").lower()

        if decision == "si":
            probabilidad = random.random()  # número entre 0.0 y 1.0

            if probabilidad <= 0.3:
                buenas += 1
            else:
                malas += 1

            if malas >= 2:
                return "Perdiste", malas
            elif buenas >= 2:
                return "Ganaste", buenas

    return "Juego terminado sin cumplir condiciones", max(buenas, malas)

# Llamar a la función y mostrar solo un print
resultado, cantidad = juego_pesca()

print(f"\nResultado final: {resultado} ({cantidad} especies {'buenas' if resultado == 'Ganaste' else 'malas'})")
