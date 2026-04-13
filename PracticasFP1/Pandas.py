import pandas as pd

datos = ["a", "b", "b", "c", "d"]
indices = [0, 20, "hola", 40, 50]
serie2 = pd.Series(datos, indices)
print(serie2)
no_repetidos = serie2.unique()
print(no_repetidos)


serie3 = pd.Series([1,2,3])
serie3.name = "aura"
print(serie3)
print(serie3[2:4])
print(serie3[[0,2]])
df = pd.DataFrame({"columna1": [1,2,3,4], "columna2":['a', 'b', 'c', 'd']})
print(df)

sr1 = pd.Series(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'])
df1 = pd.DataFrame({'A': range(0,50,5),
                    'B': 10**2,
                    'C': sr1.values},
                    index= pd.date_range(start= '2019-01-01', freq='D', periods=10))

print(df1)
df2 = df1.iloc[2,2]
print(df2)


uno = pd.Series([12,1,4,7], dtype= float)
dos = pd.Series([10,6,4,9], dtype= float)
tres = uno[dos>5]
print(tres)

nombres = pd.Series(["Juan", "Maria", "Pedro", "Sara"])
leccion1= pd.Series([70, 62, 80, 45] )
leccion2= pd.Series([73, 52, 71, 70] )
diccionario= {'nombres': nombres,
              'leccion1': leccion1,
              'leccion2': leccion2}
df5 = pd.DataFrame(diccionario)
cant_mayores = len(leccion1[leccion1>65])
cant_mayores2 = sum(leccion1>65)
cant_mayores3= (leccion1>65).sum()
print(cant_mayores)
print(cant_mayores2)
print(cant_mayores3)
print(df5)
print('   ' \
'' \
'' \
'')
print((leccion2>leccion2.mean()).sum())

diccionario = {
"calificacion":[ 65, 50, 80 ],
"matricula": ["20231234", "20231111", "20230001" ],
"nombre": ["estudiante1", "estudiante2", "estudiante3"], 
"residencia": ["Guayaquil", "Guayaquil", "Duran" ] }
mi_data= pd.DataFrame(diccionario)
mi_data_con_indices= mi_data.set_index('matricula')
agrupacion12 = mi_data.groupby('residencia')
print(agrupacion12)
print(mi_data)
print('' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'')

print(mi_data_con_indices)
print(mi_data.loc[1,"matricula"])
print(mi_data.loc[1:,:])
print(mi_data.loc[1:])
print(mi_data.loc[1:,['matricula','residencia','calificacion']])
print('' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'' \
'')
top_2 = mi_data.sort_values('calificacion').tail(2)
print(top_2)