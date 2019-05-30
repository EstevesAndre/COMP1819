.class public MonteCarloPi
.super java/lang/Object
.method public performSingleEstimate()Z
.limit stack 100
.limit locals 5
ldc 0
ldc 100
isub
ldc 100
invokestatic MathUtils/random(II)I
istore 1
ldc 0
ldc 100
isub
ldc 100
invokestatic MathUtils/random(II)I
istore 2
iload 1
iload 1
imul
iload 2
iload 2
imul
iadd
ldc 100
idiv
istore 4
iload 4
ldc 100
if_icmpge LABEL1
iconst_1
istore 3
goto LABEL2
LABEL1:
iconst_0
istore 3
LABEL2:
iload 3
ireturn
.end method
.method public estimatePi100(I)I
.limit stack 100
.limit locals 5
ldc 0
istore 3
ldc 0
istore 2
LABEL3:
iload 3
iload 1
if_icmpge LABEL4
aload_0
invokevirtual MonteCarloPi/performSingleEstimate()Z
ifeq LABEL5
iload 2
ldc 1
iadd
istore 2
goto LABEL6
LABEL5:
LABEL6:
iload 3
ldc 1
iadd
istore 3
goto LABEL3
LABEL4:
ldc 400
iload 2
iload 2
idiv
imul
istore 4
iload 4
ireturn
.end method
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 3
invokestatic ioPlus/requestNumber()I
istore 2
new MonteCarloPi
dup
invokespecial MonteCarloPi/<init>()V
iload 2
invokevirtual MonteCarloPi/estimatePi100(I)I
istore 1
iload 1
invokestatic ioPlus/printResult(I)V
return
.end method
; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
