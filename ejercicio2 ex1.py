l_serv = [
    "|NS001etflix|9.99|Activo",
    "S002|Spotify|6.50|Inactivo",
    "S003|Disney+|8.75|Activo"]

def servicios_activos(l_serv):
    activos =[]
    for elemento in l_serv:
        categorias = elemento.split("|")
        print(categorias)
        nombre = categorias[1]
        tarifa = categorias[2]
        estado = categorias[3]
        if estado == "Activo":
            activos.append(nombre+"|"+tarifa)
    print(activos)

















servicios_activos(l_serv)