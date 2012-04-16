Sistemas de Inteligencia Artificial
===================================

Trabajos prácticos de la materia S.I.A del ITBA - 1er cuatrimestre 2012.

Ejecución
---------

Para correr el Perceptrón Simple primero se debe estar parado sobre la rúta en donde se encuentran los archivos `.m` de de carpeta `src` de la carpeta `TPE_pre2` y ejectutar el programa `octave`. Una vez hecho esto, se debe invocar al programa `main` especificando los siguientes parámetros:

* 1 - operator_name: puede ser AND o OR
* 2 - N: cantidad de bits a utilizar con el operador elegido (2 - 5)
* 3 - epochs: cantidad de épocas
* 4 - trans_name: nombre de la función de transformación (Sg, Linear, Sigmoid)
* 5 - lrn_base: un decimal indicando el `eta` a utilizar.
* 6 - lrn_type_name: metodo para actualizar el `lrn_base` al final de cada epoca (Constant, Annealed, Dynamic)

* `ejemplo: main("AND", 4, 500, "SIGMOID", 0.02, "CONSTANT")`

Se entrenará a la red con un AND de 4 bits, 500 épocas, con una función de transformacion sigmoidea, con un eta contante con valor 0.02.

En caso de querer consultar los parámetros ayuda, puede invocarse main('help') y mostrará la forma de invocación y los operadores válidos para cada típo de selección.
