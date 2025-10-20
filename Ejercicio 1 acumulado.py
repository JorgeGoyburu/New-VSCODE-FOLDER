
# 1. Pedir los datos
a = int( input('Ingrese el valor de a: ') ) # 13
b = int( input('Ingrese el valor de b: ') ) # 3

# 2. Calcular C
num = a**2 + (b*a) ** (1/2)
den = a%b
c = num/den # 175.24499988374

# 3. Verif condiciones
entera = int(c) # 175
#             False  and   False
cond1 = entera%2==0  and entera<a # False

#           True    and   True
cond2 = entera%2 != 0 and entera>=b # True

result = cond1 or cond2 # True

# 4. Mostrar resultados
print( 'El valor de c:', c)
print( 'Resultado de condiciones:', result )














