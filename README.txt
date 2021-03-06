COMP1819: Java-- Parser

** Group G11

name, number, grade, contribution (%)

André Esteves, 201606673, 15, 25
Luís Silva, 201503730, 15, 25
Pedro Fernandes, 201603846, 15, 25
Pedro Silva, 201604470, 15, 25

** Global grade: 15

** Summary
The Java-- Parser allows the user to submit a file in Java--, a subset of the Java language (which means that everything that runs in Java-- must run in Java).
The tool analyses the code's syntatic and semantic and generates the binary file accordingly or reports errors in case of bad syntatic/semantic in the user file.

** Execute

Compile:
./scripts/compile.sh

Run:
java -jar jmm.jar res/<filename>

** Dealing with syntatic errors
The program analyzis the whole file. Whenever it finds an error, it displays it for the user. 

** Semantic analysis (Rules implemented)
 - Not allowed two variables with same identifier in the same scope
 - The types on each side of an assigment must be the same
 - A variable must be declared before being used
 - A function must return the type that is designated in its signature
 - A call of a non static function must be called from a declared instance of the class
 - The 'not' and 'and' operations must have boolean operands
 - Both arithmetic expressions and '<' must have an integer operands
 - The length property can only be called on arrays
 - Both 'if' and 'while' conditions must be of type boolean
 - An array must be indexed by an 'int'
 - A function from a known class must have a corresponding function definition

** Code generation:
Each class derived from SimpleNode is responsible for the generation of its own code, 
if any. The AST is visited recursively, appending the jasmin code of each node to the return file.

Whenever possible, lower cost instructions were used. This includes small index load
instructions (iload_x), small constant load instructions (iconst_x) and  bipush for
integers up to 127.

Also, the code generated by "and" and "less than" nodes does not load a variable to
the stack, ready to be used by its parent's code. Instead, it leaves the operands in
the stack, allowing possible "if" and "while" loops to save a jump instruction.

** Overview

The tool has the following flow:

- Syntatic analysis
The parser package, generated by javacc, is responsible for making sure the input program
is according to the grammar.

- Semantic analysis
The semanticAnalysis package checks various problems in Expressions and reports them to the console.
This is done by revursively visiting the AST.

- Code generation
The codeGeneration package is responsible for visiting the tree and generating the respective jvm code.
Here the main approaches are similar to the ones seen by using the command **javap -c "Class"**.

- Optimizations
The opmizations package is incomplete. Its purpose would be to implement the graph coloring algorithm for register allocation, but 
we did not complete it. There's however, partial versions of the main steps:
 - Control-Graph-Flow generation
 - Def and Use sets computation
 - Liveness analysis
 - Interference graph generation
 - Graph coloring algorithm

** Task Distribution
Every member contributed equally in every stage of development.


** Pros:
 - Function overloading. Instead of just saving the function identifier in the symbol table, we save its signatura which allows to support overloading


** Cons:
 - Both static calls and calls from unknown (external) classes had no predefined return types. Therefore, we had to assume their types.
