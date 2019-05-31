.class public Lazysort
.super Quicksort
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 5
bipush 10
newarray int
astore_1
iconst_0
istore_2
LABEL1:
iload_2
aload_1
arraylength
if_icmpge LABEL2
aload_1
iload_2
aload_1
arraylength
iload_2
isub
iastore
iload_2
iconst_1
iadd
istore_2
goto LABEL1
LABEL2:
new Lazysort
dup
invokespecial Lazysort/<init>()V
astore_3
aload_3
aload_1
invokevirtual Quicksort/quicksort([I)Z
istore 4
aload_3
aload_1
invokevirtual Quicksort/printL([I)Z
istore 4
return
.end method

.method public quicksort([I)Z
.limit stack 100
.limit locals 3
iconst_0
iconst_5
invokestatic MathUtils/random(II)I
iconst_4
if_icmpge LABEL3
aload_0
aload_1
invokevirtual Lazysort/beLazy([I)Z
pop
iconst_1
istore_2
goto LABEL4
LABEL3:
iconst_0
istore_2
LABEL4:
iload_2
ifeq LABEL5
iload_2
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
aload_1
iconst_0
aload_1
arraylength
iconst_1
isub
invokevirtual Lazysort/quicksort([III)Z
istore_2
LABEL6:
iload_2
ireturn
.end method

.method public beLazy([I)Z
.limit stack 100
.limit locals 4
aload_1
arraylength
istore_2
iconst_0
istore_3
LABEL9:
iload_3
iload_2
iconst_2
idiv
if_icmpge LABEL10
aload_1
iload_3
iconst_0
bipush 10
invokestatic MathUtils/random(II)I
iastore
iload_3
iconst_1
iadd
istore_3
goto LABEL9
LABEL10:
LABEL11:
iload_3
iload_2
if_icmpge LABEL12
aload_1
iload_3
iconst_0
bipush 10
invokestatic MathUtils/random(II)I
iconst_1
iadd
iastore
iload_3
iconst_1
iadd
istore_3
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

