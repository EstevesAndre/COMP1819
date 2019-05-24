.class public Quicksort
.super java/lang/Object
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 4
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
iload 1
iload 2
isub
iastore
iload 2
ldc 1
iadd
istore 2
goto LABEL1
LABEL2:
new Quicksort
dup
invokespecial Quicksort/<init>()V
astore 3
aload 3
iload 1
invokevirtual Quicksort/quicksort([I)Z
aload 3
iload 1
invokevirtual Quicksort/printL([I)Z
return
.end method
.method public printL([I)Z
.limit stack 100
.limit locals 3
ldc 0
istore 2
LABEL3:
iload 2
aload 1
arraylength
if_icmpge LABEL4
iload 1
invokestatic io/println()V
iload 2
ldc 1
iadd
istore 2
goto LABEL3
LABEL4:
iconst_1
ireturn
.end method
.method public quicksort([I)Z
.limit stack 100
.limit locals 2
aload_0
iload 1
ldc 0
iload 1
ldc 1
isub
invokevirtual Quicksort/quicksort([III)Z
ireturn
.end method
.method public quicksort([III)Z
.limit stack 100
.limit locals 5
iload 2
iload 3
if_icmpge LABEL5
aload_0
iload 1
iload 2
iload 3
invokevirtual Quicksort/partition([III)I
aload_0
iload 1
iload 2
iload 4
ldc 1
isub
invokevirtual Quicksort/quicksort([III)Z
aload_0
iload 1
iload 4
ldc 1
iadd
iload 3
invokevirtual Quicksort/quicksort([III)Z
goto LABEL6
LABEL5:
LABEL6:
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
LABEL7:
iload 6
iload 3
if_icmpge LABEL8
aload 1
iload 6
iaload
iload 4
if_icmpge LABEL9
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
ldc 1
iadd
istore 5
goto LABEL10
LABEL9:
LABEL10:
iload 6
ldc 1
iadd
istore 6
goto LABEL7
LABEL8:
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
