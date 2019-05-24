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
iload 3
iload 3
ireturn
.end method
.method public build_test_arr()I
.limit stack 100
.limit locals 1
aload_0
ldc 0
ldc 14
putfield FindMaximum/test_arr [I
aload_0
ldc 1
ldc 28
putfield FindMaximum/test_arr [I
aload_0
ldc 2
ldc 0
putfield FindMaximum/test_arr [I
aload_0
ldc 3
ldc 0
ldc 5
isub
putfield FindMaximum/test_arr [I
aload_0
ldc 4
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
return
.end method
