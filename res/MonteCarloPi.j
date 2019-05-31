.class public MonteCarloPi
.super java/lang/Object
.method public performSingleEstimate()Z
.limit stack 100
.limit locals 5
iconst_0
bipush 100
isub
bipush 100
invokestatic MathUtils/random(II)I
istore_1
iconst_0
bipush 100
isub
bipush 100
invokestatic MathUtils/random(II)I
istore_2
iload_1
iload_1
imul
iload_2
iload_2
imul
iadd
bipush 100
idiv
istore 4
iload 4
bipush 100
if_icmpge LABEL1
iconst_1
istore_3
goto LABEL2
LABEL1:
iconst_0
istore_3
LABEL2:
iload_3
ireturn
.end method

.method public estimatePi100(I)I
.limit stack 100
.limit locals 5
iconst_0
istore_3
iconst_0
istore_2
LABEL3:
iload_3
iload_1
if_icmpge LABEL4
aload_0
invokevirtual MonteCarloPi/performSingleEstimate()Z
ifeq LABEL5
iload_2
iconst_1
iadd
istore_2
goto LABEL6
LABEL5:
LABEL6:
iload_3
iconst_1
iadd
istore_3
goto LABEL3
LABEL4:
sipush 400
iload_2
imul
iload_1
idiv
istore 4
iload 4
ireturn
.end method

.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 3
invokestatic ioPlus/requestNumber()I
istore_2
new MonteCarloPi
dup
invokespecial MonteCarloPi/<init>()V
iload_2
invokevirtual MonteCarloPi/estimatePi100(I)I
istore_1
iload_1
invokestatic ioPlus/printResult(I)V
return
.end method

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

