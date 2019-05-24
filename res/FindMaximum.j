.class public FindMaximum
.super java/lang/Object
.field public test_arr [I
; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method public find_maximum([I)I
.limit stack 100
.limit locals 5
ldc 1
istore 2
aload 1
ldc 0
iaload
istore 3
LABEL1:
iload 2
aload 1
arraylength
if_icmpge LABEL2
aload 1
iload 2
iaload
istore 4
iload 3
iload 4
if_icmpge LABEL3
iload 4
istore 3
goto LABEL4
LABEL3:
LABEL4:
iload 2
ldc 1
isum
istore 2
goto LABEL1
LABEL2:
iload 3
iload 3
ireturn
.end method
.method public build_test_arr()I
.limit stack 100
.limit locals 1
aload_0
ldc 5
newarray int
putfield FindMaximum/test_arr [I
aload_0
ldc 14
ldc 14
putfield FindMaximum/test_arr [I
aload_0
ldc 0
putfield FindMaximum/test_arr [I
aload_0
ldc 14
putfield FindMaximum/test_arr [I
aload_0
ldc 28
ldc 28
putfield FindMaximum/test_arr [I
aload_0
ldc 1
putfield FindMaximum/test_arr [I
aload_0
ldc 28
putfield FindMaximum/test_arr [I
aload_0
ldc 0
ldc 0
putfield FindMaximum/test_arr [I
aload_0
ldc 2
putfield FindMaximum/test_arr [I
aload_0
ldc 0
putfield FindMaximum/test_arr [I
aload_0
ldc 0
ldc 5
isub
ldc 0
ldc 5
isub
putfield FindMaximum/test_arr [I
aload_0
ldc 3
putfield FindMaximum/test_arr [I
aload_0
ldc 0
ldc 5
isub
putfield FindMaximum/test_arr [I
aload_0
ldc 12
ldc 12
putfield FindMaximum/test_arr [I
aload_0
ldc 4
putfield FindMaximum/test_arr [I
aload_0
ldc 12
putfield FindMaximum/test_arr [I
ldc 0
ireturn
.end method
.method public get_array()[I
.limit stack 100
.limit locals 1
getfield FindMaximum/test_arr [I
aload_0
getfield FindMaximum/test_arr [I
areturn
.end method
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 2
new FindMaximum
dup
invokespecial FindMaximum/<init>()V
astore 1
aload 1
invokevirtual FindMaximum/build_test_arr()I
iload 1
invokestatic ioPlus/printResult()V
return
.end method
