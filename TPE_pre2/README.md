Sistemas de Inteligencia Artificial
===================================

Trabajos prácticos de la materia S.I.A del ITBA - 1er cuatrimestre 2012.

Ejecución
---------

Para correr el Perceptron Simple primero se debe estar parado sobre la ruta en donde se encuentran los archivos `.m` de de carpeta `src` de la carpeta `TPE_pre2` y ejectutar el programa `octave`. Una vez hecho esto, se debe invocar al programa `main` especificando los siguientes parametros:

* 1 - operator_name: puede ser AND o OR
* 2 - N: cantidad de bits a utilizar con el operador elejido (2 - 5)
* 3 - epochs: cantidad de epocas
* 4 - trans_name: nombre de la funcion de transformacion (Sg, Linear, Sigmoid)
* 5 - lrn_base: un decimal indicando el `eta` a utilizar.
* 6 - lrn_type_name: metodo para actualizar el `lrn_base` al final de cada epoca (Constant, Annealed, Dynamic)

* `ejemplo: main("AND", 4, 500, "SIGMOID", 0.02, "CONSTANT")`

Se entrenara a la redcon un AND de 4 bits, 500 epocas, con una funcionde transformacion sigmoidea, con un eta contante con valos 0.02.


En caso de querere consultar el menu de ayuda, puede invocarse main('help') y mostrara la forma de invocacion y los operadores validos para cada tipo de seleccion.
