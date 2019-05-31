java -cp bin/ parser.jmm res/Life.java > life.txt
java -cp bin/ parser.jmm res/FindMaximum.java > findMaximum.txt
java -cp bin/ parser.jmm res/QuickSort.java > quicksort.txt
java -cp bin/ parser.jmm res/Lazysort.java > lazysort.txt
java -cp bin/ parser.jmm res/HelloWorld.jmm > helloworld.txt
java -cp bin/ parser.jmm res/MonteCarloPi.java > montecarlopi.txt

cd res

java -jar jasmin.jar Life.j
java -jar jasmin.jar FindMaximum.j
java -jar jasmin.jar QuickSort.j
java -jar jasmin.jar Lazysort.j
java -jar jasmin.jar HelloWorld.j
java -jar jasmin.jar MonteCarloPi.j

