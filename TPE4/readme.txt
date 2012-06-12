
******* Uso del prgograma *******

Parámetros de invocacion: N G maxGen mp cp selectionType replacementType
	N	= Tamaño de la poblacion
	G	= Gap geenracional [0 - 1]
	maxGen	= Cantidad máxima de generaciones [disponible solamente con break criteria = BreakCriteriaType.MAX_GEN]
	mp	= pobabilidad de mutacion [0 - 1]
	cp	= probabilidad de cruce [0 - 1]
	selectionType	= ELITE - BOLTZMAN - UNIVERSAL - RULETA - MIXED_BOLTZMAN - MIXED_RULETA - TURNAMENT
	replacementType = ELITE - BOLTZMAN - UNIVERSAL - RULETA - MIXED_BOLTZMAN - MIXED_RULETA - TURNAMENT
	
**************************

==> Llamar con un parámetro "default" para empezar una simulación con sólo parámetros default.
==> Para una configuración avanzada de los parámetros, ver clase GeneticAlgorithmTest(paquete Main) y configurar todos los parámetros deseados en el método createConfiguration().
	La clase Configuration tiene todas las configuraciones de parámetros disponibles junto a la documentation explicando para que sirven.

	
Ejemplos de invocacion:

java -jar geneticAlgorithmTest.jar default
java -jar geneticAlgorithmTest.jar 20 0.6 200 0.01 0.75 RULETA ELITE

Nota: 
	El programa busca el archivo de ejemplos a partir del path: "./TPE4/res/examples.txt". Si esta ruta no existe o el arhcivo no existe el programa no correra y tirar una excepcion de archivo no encontrado!