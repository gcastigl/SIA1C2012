Sistemas de Inteligencia Artificial
===================================

## Trabajo Práctico #1 - Entrega preliminar
## Métodos de búsqueda no informados

#### Problema asignado al azar: Ejercicio 9

Ejecución
---------

Para correr el General Problem Solver usted debe declarar qué estrategia de búsqueda no informada utilizará, como así también el nivel de LOG que se desea obtener (este último parámetro puede ser omitido). Ejemplos:

* `java -jar Edificios.jar DFS 2` -- Si desea utilizar Depth-first search como estrategia, cargar el tablero 2 y no logguear nada.
* `java -jar Edificios.jar BFS 3 MAX` -- Si desea utilizar Breadth-first search como estrategia, cargar el tablero 3 y tener un nivel máximo de logueo.

Generalizando

* `java -jar Edificios.jar [DFS BFS IDFS] problemN [MIN MED MAX]` -- El parámetro que indica la estrategia de búsqueda no puede ser omitido como así tampoco el número de problema a ejecutar; en caso de omitir el segundo parámetro (nivel de log), el programa se ejecutará sin log alguno.

Los problemas disponibles son:

* `1` -- Tablero de 2x2 
* `2` -- Tablero de 3x3 
* `3` -- Tablero de 4x4 
* `4` -- Tablero de 4x4 
* `5` -- Tablero de 5x5 
* `6` -- Tablero de 5x5 

Los niveles de log son:

* `MIN` -- 
* `MED` -- 
* `MAX` -- 



El programa creará un archivo de texto `log.txt` con la salida del mismo pero también irá mostrando su avance por la salida estándar. 
Nota: Mientras mayor sea el nivel de log deseado, más demorará la ejecución del programa.

Si así lo desea, puede cambiar el tablero desde el código, en la clase BuldingParser del archivo `BuildingParser.java`. Sólo debe setear los vectores con los números borde del tablero a crear. Luejo ejecute `ant` en el directorio que contiene al archivo `build.xml` para así generar un nuevo `Edificios.jar` y correrlo como se explicó anteriormente.

____________________________
____________________________

Objetivo
--------

Se debe crear un Sistema de producción que será usado para resolver el problema asignado a cada grupo.
Se les entregará un motor de inferencia reducido programado en Java. Cada grupo puede decidir entre utilizar este motor y hacerle las modificaciones que sean necesarias para completar el trabajo o realizar dicho motor en el lenguaje que deseen.

Descripción del trabajo
-----------------------

Se deberá realizar lo siguiente:

* Implementación de las estrategias de búsqueda no informadas: depth first, breadth first y profundización iterativa.
* Heurísticas: Presentación de al menos dos (2) heurísticas. La/las heurísticas deben ser no triviales. Enumerar las diferencias entre ellas. No es necesaria la implementación de las heurísticas, pero sí la explicación de cada una y sus diferencias.
* Función de costo. Presentación de la/las funciones de costo. Si hay más de una, enumerar las diferencias entre ellas. No es necesaria la implementación de las funciones de costo pero sí la explicación de cada una y sus diferencias.
* De cada corrida se deberá analizar al menos: la profundidad de la solución, cantidad total de estados generados, número de nodos frontera, número de nodos expandidos y tiempo de procesamiento.

El informe deberá describir el trabajo realizado, el análisis de los resultados obtenidos, las heurísticas, las funciones de costo y las conclusiones. La longitud del mismo no puede ser mayor a 2 carillas (se penalizará con 1 punto por cada carilla excedida). Las imágenes explicativas y tablas pueden estar en una sección anexa y no cuentan en el total de hojas.

No está permitido el uso de librerías o toolkits.

Problema 9: Edificios
---------------------

+ [Ver PDF con enunciados](./TPE_pre1/doc/TPE1%20-%20Entrega%20preliminar%201.pdf?raw=true)






