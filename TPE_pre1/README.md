Sistemas de Inteligencia Artificial
===================================

## Trabajo Práctico #1 - Entrega preliminar
## Métodos de búsqueda no informados

#### Problema asignado al azar: Ejercicio 9

Ejecución
---------

Para correr el General Problem Solver lo primero que debe hacer es correr el comando `ant` lo que creará el archivo `Edificios.jar`. Una vez hecho esto, usted debe declarar qué estrategia de búsqueda utilizará, qué archivo de nivel correrá el algoritmo (tableros), qué forma de interar sobre el tablero se usará, la heurística deseada y el nivel de LOG que se desea obtener (opcional). Ejemplos:

* `java -jar Edificios.jar Greedy res/boards/board5x5 MRV Simple MED` -- Si desea utilizar Greedy search como estrategia, cargar el tablero 'board5x5', utilizar método de iteración MRV con heurística simple y nivel de log mediano.

Generalizando

* `java -jar Edificios.jar [DFS BFS IDFS AStar Greedy] filePath [OutSpiral InSpiral MRV Sequential] [Simple HighRes LowRes No] [MIN MED MAX]` -- Todos los parámetros son obligatorios y en caso de faltar alguno se mostrará por salida estándar los comandos de uso. 

Los algoritmos disponibles:

* `DFS` -- Depth-first search 
* `BFS` -- Breadth-first search
* `IDFS` --  Iterated DFS
* `Greedy` --  Greedy search
* `AStar` --  A Star

Los problemas disponibles están dentro de la carpeta `/res/boards/` y tienen las siguientes características:

* `board2x3` -- Tablero de 3x3 
* `board3x3` -- Tablero de 4x4 
* `board4x4` -- Tablero de 5x5 
* `board5x5` -- Tablero de 5x5 
* `board6x6` -- Tablero de 6x6 
* `board7x7` -- Tablero de 7x7 
* `no_solution` -- Tablero de 4x4 sin solución

Formas de recorrer el tablero:

* `OutSpiral` --  Se recorre el tablero en forma de espiral empezando desde el centro
* `InSpiral` --  Se recorre el tablero en forma de espiral empezando desde una esquina hacia el centro
* `MRV` -- Próximo nodo con menor cantidad de valores legales (fail first). 
* `Sequential` -- De izquierda a derecha de arriba hacia abajo. 


Heurísticas:

* `Simple` -- Chequea tableros irresolubles y devuelve cantidad de ceros. 
* `HighRes` -- Premia a los tableros con altas restricciones. 
* `LowRes` --  Premia a los tableros con bajas restricciones.
* `No` --  Retorna sólo cantidad de ceros.

Los niveles de log son:

* `MIN`  
* `MED`  
* `MAX`  

Los conjuntos de reglas disponibles:

* `STD` -- Conjunto de reglas standard: Poner un edificio de altura "k" en la posición (i,j), siendo "k" un número perteneciente a [1,n]. Esto genera un factor de ramificación igual a n**3. 
* `RED` -- Conjunto de reglas reducido: "Poner un edificio de altura 1 en el próximo lugar vacío de izquierda a derecha, de arriba a abajo", ... , "Poner un edificio de altura n en el próximo lugar vacío de izquierda a derecha, de arriba a abajo". Este conjunto de reglas tiene sólo n reglas, siendo n la dimensión del tablero. Esto produce un factor de ramificación igual a n lo que agiliza mucho el tiempo de procesamiento de los algoritmos.


El programa creará un archivo de texto `log.txt` con la salida del mismo pero también irá mostrando su avance por la salida estándar. 
Nota: Mientras mayor sea el nivel de log deseado, más demorará la ejecución del programa.


____________________________
____________________________

Objetivo
--------

Se debe crear un Sistema de producción que será usado para resolver el problema asignado a cada grupo.
Se les entregará un motor de inferencia reducido programado en Java. Cada grupo puede decidir entre utilizar este motor y hacerle las modificaciones que sean necesarias para completar el trabajo o realizar dicho motor en el lenguaje que deseen.

Descripción del trabajo
-----------------------

Se deberá realizar lo siguiente:

* Implementación de las estrategias de búsqueda no informadas: depth first, breadth first, profundización iterativa, Greedy search y A Star.
* Heurísticas: Presentación de al menos dos (2) heurísticas. La/las heurísticas deben ser no triviales. Enumerar las diferencias entre ellas. No es necesaria la implementación de las heurísticas, pero sí la explicación de cada una y sus diferencias.
* Función de costo. Presentación de la/las funciones de costo. Si hay más de una, enumerar las diferencias entre ellas. No es necesaria la implementación de las funciones de costo pero sí la explicación de cada una y sus diferencias.
* De cada corrida se deberá analizar al menos: la profundidad de la solución, cantidad total de estados generados, número de nodos frontera, número de nodos expandidos y tiempo de procesamiento.

El informe deberá describir el trabajo realizado, el análisis de los resultados obtenidos, las heurísticas, las funciones de costo y las conclusiones.

No está permitido el uso de librerías o toolkits.

Problema 9: Edificios
---------------------

+ [Ver PDF con enunciados](./TPE_pre1/doc/TPE1%20-%20Entrega%20preliminar%201.pdf?raw=true)






