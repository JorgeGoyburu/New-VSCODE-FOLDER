import random

def juego_pesca():
    buenas = 0
    letales = 0
    especies = ["pez dorado", "medusa", "pulpo", "cangrejo", "pez globo",
                "tiburón", "estrella de mar", "anguila", "calamar"]

    print("\n¡Bienvenido a la Expedición Marina!")
    print("Tu misión es recolectar especies marinas sin caer en las letales.")
    print("Necesitas al menos 2 especies buenas para ganar. ¡Buena suerte!\n")

    for i in range(1, 6):
        print(f"--- Ronda {i} ---")
        especie = random.choice(especies)
        decision = input(f"¿Deseas recolectar al {especie}? (si|no): ").lower()

        if decision == "si":
            probabilidad = random.random()
            if probabilidad <= 0.3:
                buenas += 1
                print("* Especie buena")
            else:
                letales += 1
                print("* Especie letal")

            # condición de victoria o derrota
            if buenas >= 2:
                return "Gana!!!", buenas
            elif letales >= 2:
                return "Pierde!!!", letales

        else:
            print("Decidiste no recolectar esta vez...\n")

    # si termina las rondas sin ganar ni perder
    if buenas > letales:
        return "Gana!!!", buenas
    else:
        return "Pierde!!!", letales


def main():
    continuar = "si"

    while continuar == "si":
        resultado, contador = juego_pesca()

        print("\n" + "-" * 40)
        if resultado == "Gana!!!":
            print(f"¡Felicidades! Recolectaste {contador} especies buenas.")
        else:
            print(f"Recolectaste {contador} especies letales...")
        print("-" * 40)

        continuar = input("¿Quieres intentar otra expedición? (si|no): ").lower()

    print("\nGracias por jugar.  ¡Hasta la próxima aventura!")


# Inicia el juego
main()
