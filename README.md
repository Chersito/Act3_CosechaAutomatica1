# Programación Concurrente

## Tercer Práctica - Eficiencia de cosecha automatizada (1ª. Parte)

- Se utilizó el lenguaje de JAVA para realizar el siguiente código

---

### Acerca del Proyecto
- La condición de carrera ocurre cuando el resultado de una operación depende del tiempo relativo de ejecución de las operaciones concurrentes, lo que puede llevar a resultados inesperados y potencialmente incorrectos.
- El interbloqueo se produce cuando dos o más procesos están esperando indefinidamente por recursos que están siendo utilizados por otros procesos, lo que resulta en un estancamiento del sistema.
- Por otra parte, la lógica temporal es fundamental para especificar el comportamiento temporal de sistemas concurrentes y garantizar su corrección mediante técnicas de verificación formal.
1. Desarrollar un programa que valide la eficiencia de los robots cosechadores en dos invernaderos, el primer invernadero está ubicado en la región de Chiapas (arreglo A) y el segundo ubicado en Cuernavaca (arreglo B).

2.	El programa deberá presentar la eficiencia y efectividad de la cosecha automatizada a través de:
    - Totalizar el número de frutas y/o verduras maduras que fueron cosechados por los diferentes robots industriales (estos robots son monitoreados por los hilos del programa).
    - Presentar el porcentaje de eficiencia y error en la cosecha por producto y por invernadero.
    - Presentar un mensaje indicando si se llegó o no al 100% de eficiencia.


### **Ejercicio:**
A. Hilo del main
1. El programa principal es el encargado de crear los arreglos A y B, ejecutar los hilos correspondientes asociados a los robots que realizan la cosecha (llenado de las cajas) en cada invernadero, ejecutar el hilo encargado de analizar (totalizar) la cosecha de ambos invernaderos y presentar las estadísticas y recomendaciones.
Datos de entrada:
Entero n: es el tamaño del arreglo y representa el número de productos (fruta o verdura) distintos a cosechar en ambos invernaderos.
Ejemplo:
    - En el renglón 0 se almacenará manzanas cosechadas en estado óptimo
    - En el renglón 1 se almacenará pepinillos cosechados en estado óptimo
    - …
    - En el renglón n-1 se almacenará fresas cosechadas en estado óptimo

B. Hilo Cosechador

Existe un único robot (hilo) cosechador por invernadero, encargado de realizar toda la cosecha automatizada (número de renglones del arreglo).
1. Realizar la cosecha y llenar el arreglo con números aleatorios que representan el número de fruta o verdura cosechada exitosamente, es decir, madura y en perfectas condiciones. Este número varía entre 90 y 100 por caja y representa el total de productos cosechados con éxito por día.
2. Cada casilla del arreglo representa una caja de fruta o verdura diferente, por ejemplo, la casilla 0 puede representar una caja de tomates, la casilla 1 una caja de pepinos, la casilla 2 una caja de fresa y así sucesivamente.
```
main: ¿Cuántos productos diferentes se cosecharán?: 3
Inicia cosecha Robot_Chiapas
Inicia cosecha Robot_Cuernavaca
---------------------------------------------------------------------
        [Robot_Cuernavaca]:        Cosechando ... Manzana
        [Robot_Cuernavaca]:        Cosechando ... Plátano
        [Robot_Chiapas]:        Cosechando ... Manzana
        [Robot_Cuernavaca]:        Cosechando ... Fresa
        [Robot_Chiapas]:        Cosechando ... Plátano
        [Robot_Chiapas]:        Cosechando ... Fresa

Robot_Chiapas terminó la cosecha del día
Robot_Cuernavaca terminó la cosecha del día
```
_Nota: El output puede variar dependiendo de la condición de competencia entre hilos y procesos_

C. Hilo Analizador del producto
1. El hilo analizador del producto es el encargado de realizar el proceso de totalizar los diferentes productos cosechados con las características optimas en los invernaderos y generar las estadísticas de la cosecha automatizada, donde 200 productos cosechados con éxito representarán el 100%
```
Inicia análisis de cosecha Analizador
---------------------------------------------------------------------
        [AnalizadorProducto]: Analizando ... Manzana
        [AnalizadorProducto]: Analizando ... Plátano
        [AnalizadorProducto]: Analizando ... Fresa

                ESTADÍSTICAS DE LOS INVERNADEROS
---------------------------------------------------------------------
Producto   Chiapas   Cuernavaca     Total   %Exito   %Error
---------------------------------------------------------------------
Manzana   99         99                198         99.0   1.0
Analizador ---> Aún se debe calibrar los sistemas de IA para obtener el 100%
Plátano   95         90                185         92.5   7.5
Analizador ---> Aún se debe calibrar los sistemas de IA para obtener el 100%
Fresa     94         100                       194         97.0   3.0
Analizador ---> Aún se debe calibrar los sistemas de IA para obtener el 100%
---------------------------------------------------------------------
```


_Nota: El output puede variar dependiendo de la condición de competencia entre hilos y procesos_


### Para abrir el código :
Opción 1 :
1. Abrir la carpeta de [src](https://github.com/Chersito/Act3_CosechaAutomatica1/tree/main/src)

2. Código en  [CosechaAutomática1.java](https://github.com/Chersito/Act3_CosechaAutomatica1/blob/main/src/CosechaAutomatica1.java)

Opción 2 :

Utiliza `git clone https://github.com/Chersito/Act3_CosechaAutomatica1.git` para obtener el repositorio por medio de tu terminal