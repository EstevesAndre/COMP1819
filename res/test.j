.source FindMaximum.jmm
.class public FindMaximum
.super java/lang/Object
.field private 'test_arr' [I

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

.method public find_maximum([I)I
.limit stack 100
.limit locals 5
iconst_1
istore 2
aload 1
iconst_0
iaload
istore 3
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
iaload
istore 4
iload 3
iload 4
if_icmpge LT2
iconst_1
goto LT3
LT2:
iconst_0
LT3:
ifeq ELSE1
iload 4
istore 3
goto ENDIF1
ELSE1:
ENDIF1:
iload 2
iconst_1
iadd
istore 2
goto WHILE0
ENDWHILE0:
iload 3
ireturn
.end method

.method public build_test_arr()I
.limit stack 100
.limit locals 1
aload_0
iconst_5
newarray int
putfield FindMaximum/test_arr [I
aload_0
getfield FindMaximum/test_arr [I
iconst_0
bipush 14
iastore
aload_0
getfield FindMaximum/test_arr [I
iconst_1
bipush 28
iastore
aload_0
getfield FindMaximum/test_arr [I
iconst_2
iconst_0
iastore
aload_0
getfield FindMaximum/test_arr [I
iconst_3
iconst_0
iconst_5
isub
iastore
aload_0
getfield FindMaximum/test_arr [I
iconst_4
bipush 12
iastore
iconst_0
ireturn
.end method

.method public get_array()[I
.limit stack 100
.limit locals 1
aload_0
getfield FindMaximum/test_arr [I
areturn
.end method

.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 2
new FindMaximum
dup
invokespecial FindMaximum/<init>()V
astore 1
aload 1
invokevirtual FindMaximum.build_test_arr()I
pop
aload 1
aload 1
invokevirtual FindMaximum.get_array()[I
invokevirtual FindMaximum.find_maximum([I)I
invokestatic ioPlus.printResult(I)V
return
.end method
