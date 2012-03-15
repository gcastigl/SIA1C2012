Sistemas de Inteligencia Artificial
===================================

## Trabajo Práctico #1 - Entrega preliminar
## Métodos de búsqueda no informados

#### Problema asignado al azar: Ejercicio 9

Ejecución
---------

Para correr el General Problem Solver lo primero que debe hacer es correr el comando `ant` lo que creará el archivo `Edificios.jar`. Una vez hecho esto, usted debe declarar qué estrategia de búsqueda utilizará, qué archivo de nivel correrá el algoritmo (tableros) luego qué conjunto de reglas utilizar y por último, el nivel de LOG que se desea obtener (opcional). Ejemplos:

* `java -jar Edificios.jar DFS res/boards/board1 STD` -- Si desea utilizar Depth-first search como estrategia, cargar el tablero 'board1', no logguear nada y usar el conjunto de reglas STD (standard).
* `java -jar Edificios.jar BFS res/boards/board3 RED MAX` -- Si desea utilizar Breadth-first search como estrategia, cargar el tablero 'board3', utilizar el conjunto de reglas RED (reduced) y tener un nivel máximo de logueo (el logging puede afectar la performance).

Generalizando

* `java -jar Edificios.jar [DFS BFS IDFS] fileName [STD RED] [MIN MED MAX]` -- El parámetro que indica la estrategia de búsqueda no puede ser omitido como así tampoco el archivo con el tablero a resolver; en caso de omitir el último parámetro (nivel de log), el programa se ejecutará con el nivel mínimo de log. 

Los algoritmos disponibles:

* `DFS` -- Depth-first search 
* `BFS` -- Breadth-first search
* `IDFS` --  Iterated DFS

Los problemas disponibles están dentro de la carpeta `/res/boards/` y tienen las siguientes características:

* `board1` -- Tablero de 2x2 
* `board2` -- Tablero de 3x3 
* `board3` -- Tablero de 4x4 
* `board4` -- Tablero de 5x5 
* `board5` -- Tablero de 5x5 

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

* Implementación de las estrategias de búsqueda no informadas: depth first, breadth first y profundización iterativa.
* Heurísticas: Presentación de al menos dos (2) heurísticas. La/las heurísticas deben ser no triviales. Enumerar las diferencias entre ellas. No es necesaria la implementación de las heurísticas, pero sí la explicación de cada una y sus diferencias.
* Función de costo. Presentación de la/las funciones de costo. Si hay más de una, enumerar las diferencias entre ellas. No es necesaria la implementación de las funciones de costo pero sí la explicación de cada una y sus diferencias.
* De cada corrida se deberá analizar al menos: la profundidad de la solución, cantidad total de estados generados, número de nodos frontera, número de nodos expandidos y tiempo de procesamiento.

El informe deberá describir el trabajo realizado, el análisis de los resultados obtenidos, las heurísticas, las funciones de costo y las conclusiones. La longitud del mismo no puede ser mayor a 2 carillas (se penalizará con 1 punto por cada carilla excedida). Las imágenes explicativas y tablas pueden estar en una sección anexa y no cuentan en el total de hojas.

No está permitido el uso de librerías o toolkits.

Problema 9: Edificios
---------------------

+ [Ver PDF con enunciados](./TPE_pre1/doc/TPE1%20-%20Entrega%20preliminar%201.pdf?raw=true)






