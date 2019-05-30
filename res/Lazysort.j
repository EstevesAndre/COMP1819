.class public Lazysort
.super Quicksort
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 5
ldc 10
newarray int
astore 1
ldc 0
istore 2
LABEL1:
iload 2
aload 1
arraylength
if_icmpge LABEL2
aload 1
iload 2
aload 1
arraylength
iload 2
isub
iastore
iload 2
ldc 1
iadd
istore 2
goto LABEL1
LABEL2:
new Lazysort
dup
invokespecial Lazysort/<init>()V
astore 3
aload 3
aload 1
invokevirtual Quicksort/quicksort([I)Z
istore 4
aload 3
aload 1
invokevirtual Quicksort/printL([I)Z
istore 4
return
.end method

.method public quicksort([I)Z
.limit stack 100
.limit locals 3
ldc 0
ldc 5
invokestatic MathUtils/random(II)I
ldc 4
if_icmpge LABEL3
aload_0
aload 1
invokevirtual Lazysort/beLazy([I)Z
pop
iconst_1
istore 2
goto LABEL4
LABEL3:
iconst_0
istore 2
LABEL4:
iload 2
ifeq LABEL5
iload 2
ifeq LABEL7
iconst_0
goto LABEL8
LABEL7:
iconst_1
LABEL8:
istore 2
goto LABEL6
LABEL5:
aload_0
aload 1
ldc 0
aload 1
arraylength
ldc 1
isub
invokevirtual Lazysort/quicksort([III)Z
istore 2
LABEL6:
iload 2
ireturn
.end method

.method public beLazy([I)Z
.limit stack 100
.limit locals 4
aload 1
arraylength
istore 2
ldc 0
istore 3
LABEL9:
iload 3
iload 2
ldc 2
idiv
if_icmpge LABEL10
aload 1
iload 3
ldc 0
ldc 10
invokestatic MathUtils/random(II)I
iastore
iload 3
ldc 1
iadd
istore 3
goto LABEL9
LABEL10:
LABEL11:
iload 3
iload 2
if_icmpge LABEL12
aload 1
iload 3
ldc 0
ldc 10
invokestatic MathUtils/random(II)I
ldc 1
iadd
iastore
iload 3
ldc 1
iadd
istore 3
goto LABEL11
LABEL12:
iconst_1
ireturn
.end method

; standard initializer
.method public <init>()V
aload_0
invokespecial Quicksort/<init>()V
return
.end method

