.class public Ex
.field public a I
.method static public main([Ljava/lang/String;)V
.limit locals 5
.end method
.method public lt()Z
.limit locals 6
ldc 2istore 1
ldc 3istore 2
ldc 2
ldc 3
if_icmpge LABEL1
iconst_1
goto LABEL2
LABEL1: iconst_0
LABEL2: istore 5
ldc 2
ldc 3
if_icmpge LABEL3
goto LABEL4
LABEL3:
iload 1
iload 2
if_icmpge LABEL5
ldc 2
ldc 3
imul
istore 1
ldc 3istore 2
goto LABEL6
LABEL5:
ldc 1istore 2
ldc 2
iload 1
isum
ldc 3
if_icmpge LABEL7
goto LABEL8
LABEL7:
iconst_ 1
ireturn
.end method
.method public oi(I)I
.limit locals 7
aload_0
ldc 3putfield a/1
ldc 1
ldc 2
invokevirtual Ex/hi(I)I
isum
aload_0
getfield a/1
if_icmpge LABEL9
iconst_1
goto LABEL10
LABEL9: iconst_0
LABEL10: istore 2
iload 2
iload 3
iload 4
iload 5
iload 6
ldc 2
ireturn
.end method
.method public test()Z
.limit locals 1
iconst_ 1
ireturn
.end method
.method public and(II)Z
.limit locals 6
iload 4
iload 5
ifeq LABEL13
iload 4
iload 5
ifeq LABEL13
iconst_1
goto LABEL14
LABEL13: iconst_0
LABEL14: istore 3
iload 4
ifeq LABEL15
if_icmpge LABEL15
iconst_1
goto LABEL16
LABEL15: iconst_0
LABEL16: istore 3
iconst_ 1
ireturn
.end method
.method public hi(I)I
.limit locals 2
ireturn
.end method
