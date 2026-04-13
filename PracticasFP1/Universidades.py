import random

#funciones

def get_info(direccion):
  lista=direccion.upper().split(".")
  i_uni=lista.index("EDU")-1
  universidad=lista[i_uni]
  condicion=lista[-1]=="EC"
  return universidad,condicion

# MAIN

print("\n")
print("Ejercicio 1: get_info")
url = "www.Stanford.edu"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)
url = "www.FIEC.espol.edu.ec"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)
url= "www.unam.edu.mx"
universidad,es_ecuatoriana = get_info(url)
texto=f"Universidad:{universidad:^10}es de Ecuador - {es_ecuatoriana}"
#texto="Universidad:{:^10}es de Ecuador – {}".format(universidad,es_ecuatoriana)
print(texto)