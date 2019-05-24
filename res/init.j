.class public init
.super java/lang/Object
.field public aaa [I
.method public hi(I)I
.limit stack 100
.limit locals 7
ldc 2
istore 5
ldc 1
istore 6
iconst_1
istore 2
iconst_0
istore 4
iconst_0
istore 3
ifeq LABEL1
iload 4
ifeq LABEL1
iconst_1
goto LABEL2
LABEL1: iconst_0
LABEL2: istore 4
ldc 0
ireturn
.end method
