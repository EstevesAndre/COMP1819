.class public arrays
.super java/lang/Object
.method public test()I
.limit stack 100
.limit locals 3
ldc 4
istore 2
ldc 4
ldc 2
isub
newarray int
astore 1
aload 1
ldc 2
ldc 3
imul
iaload
ldc 2
if_icmpge LABEL1
goto LABEL2
LABEL1:
LABEL2:
ldc 0
ireturn
.end method
.method public oi()I
.limit stack 100
.limit locals 1
ldc 2
ireturn
.end method
