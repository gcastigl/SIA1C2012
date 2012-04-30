Sistemas de Inteligencia Artificial
===================================

Trabajos prácticos de la materia S.I.A del ITBA - 1er cuatrimestre 2012.

Ejecución
---------

Para correr la red neuronal multicapa primero se debe estar parado sobre la rúta en donde se encuentran los archivos `.m` de de carpeta `src` de la carpeta `TPE2` y ejectutar el programa `Octave`. Una vez hecho esto, se debe invocar al programa `main` especificando los siguientes parámetros:

* 1 - filePath: Path del archivo con el muestreo de la función a aprender (string)
* 2 - epochs: cantidad de épocas
* 3 - architecture: Arquitectura de las capas ocultas de la red (ver ejemplo más abajo)
* 4 - trans_name: nombre de la función de transformación (sigmoid, sigmoid_exp)
* 5 - lrn_base: `eta` a utilizar.
* 6 - lrn_type_name: metodo para actualizar el `lrn_base` al final de cada epoca (Constant, Annealed, Dynamic)
* 7 - interactive_flag: Si se setea en 1, se mostrará el aprendizaje de la red en vivo.
* 8 - noise: Si se setea en 1, se utilizará ruido en el aprendizaje.

* `ejemplo: main('samples.txt', 500, [2 4], "SIGMOID", 0.02, "CONSTANT", 1, 0)`

En caso de querer consultar los parámetros ayuda, puede invocarse `main('help')` y mostrará la forma de invocación y los operadores válidos para cada típo de selección.

Explicación detallada en Inglés
-------------------------------

Invoque main like main(filePath, 500, [2 4], "SIGMOID", 0.02, "CONSTANT", 1, 0)
This network will try to learn the file_path function, with an architecture of 2 hidden
layers with 2 and 4 neurons in each layer respectively.
Algorithm will run 500 epochs using the SIGMOID transfer function with a 0.02 etha and
a constant learning rate. Also it will be interactive and won't use noise.

The interactive argument flag tells the algorihm if we want to plot the network learning in 
real time (interactive = 1) or if we want to only plot the final output (interactive = 0).
The noise argument flag is set to 1 if we want to use noise and is set to 0 if we dont.
