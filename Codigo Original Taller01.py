import random

def juego_pesca():
    buenas = 0
    letales = 0

    print('Expedicion en el Mar')

    for i in range(1, 6):
        decision = input('desea recolectar esta especie si|no? ').lower()

        if decision == 'si':
            probabilidad = random.random()

            if probabilidad <= 0.3:
                buenas += 1
                print('*Especie buena')
            else:
                letales += 1
                print('*Especie letal')

                if buenas >= 2:
                    return 'Gana!!!', buenas
                elif letales >= 2:
                    return 'Pierde!!!', letales
                
resultado, contador = juego_pesca()

print(f"Fin del juego, {contador} especies {'buenas'if resultado == 'Gana!!!' else 'letales'}, {resultado}")               
        
print("tu mama")
            
        