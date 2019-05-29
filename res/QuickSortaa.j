.source QuickSort.jmm
.class public Quicksort
.super java/lang/Object

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 4
bipush 10
newarray int
astore 1
iconst_0
istore 2
WHILE0:
iload 2
aload 1
arraylength
if_icmpge LT0
iconst_1
goto LT1
LT0:
iconst_0
LT1:
ifeq ENDWHILE0
aload 1
iload 2
aload 1
arraylength
iload 2
isub
iastore
iload 2
iconst_1
iadd
istore 2
goto WHILE0
ENDWHILE0:
new Quicksort
dup
invokespecial Quicksort/<init>()V
astore 3
aload 3
aload 1
invokevirtual Quicksort.quicksort([I)Z
pop
aload 3
aload 1
invokevirtual Quicksort.printL([I)Z
pop
return
.end method

.method public printL([I)Z
.limit stack 100
.limit locals 3
iconst_0
istore 2
WHILE1:
iload 2
aload 1
arraylength
if_icmpge LT2
iconst_1
goto LT3
LT2:
iconst_0
LT3:
ifeq ENDWHILE1
aload 1
iload 2
iaload
invokestatic io.println(I)V
iload 2
iconst_1
iadd
istore 2
goto WHILE1
ENDWHILE1:
iconst_1
ireturn
.end method

.method public quicksort([I)Z
.limit stack 100
.limit locals 2
aload_0
aload 1
iconst_0
aload 1
arraylength
iconst_1
isub
invokevirtual Quicksort.quicksort2([III)Z
ireturn
.end method

.method public quicksort2([III)Z
.limit stack 100
.limit locals 5
iload 2
iload 3
if_icmpge LT4
iconst_1
goto LT5
LT4:
iconst_0
LT5:
ifeq ELSE2
aload_0
aload 1
iload 2
iload 3
invokevirtual Quicksort.partition([III)I
istore 4
aload_0
aload 1
iload 2
iload 4
iconst_1
isub
invokevirtual Quicksort.quicksort2([III)Z
pop
aload_0
aload 1
iload 4
iconst_1
iadd
iload 3
invokevirtual Quicksort.quicksort2([III)Z
pop
goto ENDIF2
ELSE2:
ENDIF2:
iconst_1
ireturn
.end method

.method public partition([III)I
.limit stack 100
.limit locals 8
aload 1
iload 3
iaload
istore 4
iload 2
istore 5
iload 2
istore 6
WHILE3:
iload 6
iload 3
if_icmpge LT6
iconst_1
goto LT7
LT6:
iconst_0
LT7:
ifeq ENDWHILE3
aload 1
iload 6
iaload
iload 4
if_icmpge LT8
iconst_1
goto LT9
LT8:
iconst_0
LT9:
ifeq ELSE4
aload 1
iload 5
iaload
istore 7
aload 1
iload 5
aload 1
iload 6
iaload
iastore
aload 1
iload 6
iload 7
iastore
iload 5
iconst_1
iadd
istore 5
goto ENDIF4
ELSE4:
ENDIF4:
iload 6
iconst_1
iadd
istore 6
goto WHILE3
ENDWHILE3:
aload 1
iload 5
iaload
istore 7
aload 1
iload 5
aload 1
iload 3
iaload
iastore
aload 1
iload 3
iload 7
iastore
iload 5
ireturn
.end method

