rm -rf bin
mkdir -p bin
javac -d bin/ src/*/*.java
cd bin
jar cfe jmm.jar parser.jmm */*.class
mv jmm.jar ../