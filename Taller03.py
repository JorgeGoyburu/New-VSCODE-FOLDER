dato1=  "R001,PO1,servidor caido,2025-06-15,juan,25,en progreso"
duracion = float(dato1.split(",")[5])
descripcion = dato1.split(",")[2]
estado = dato1.split(",")[-1]
if "caracter" in descripcion:
    print("no es valido")

if not duracion>0:
    print("el reporte no es valido")
if("critico" or "en espera" or "en progreso") not in estado:
    print("es invalido")

lista_valida = len(dato1.split(","))
print(lista_valida)



