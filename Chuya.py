#Indexacion con datos compuestos
nombre_usuario = input('Ingrese su nombre:')
print('la ultima letra de su nombre es:', nombre_usuario[-1])

#slicing
nombre_usuario2 = 'Jorge Goyburu'
print(nombre_usuario2[0:-1])

#get info
direccion = 'www.Stanford.edu'
def get_info(direccion):
  lista=direccion.upper().split(".")
  i_uni=lista.index("EDU")-1
  universidad=lista[i_uni]
  condicion=lista[-1]=="EC"
  return universidad,condicion



