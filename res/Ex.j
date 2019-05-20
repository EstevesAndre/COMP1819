.class public Ex
.field public a I
.method static public main([Ljava/lang/String;)V
.limit locals 5
.end method
.method public lt()Z
.limit locals 6
ldc 2
ldc 3
if_icmpge LABEL1
iconst_1
goto LABEL2
LABEL1: iconst_0
LABEL2: istore 5
ireturn
.end method
.method public oi(I)I
.limit locals 7
ldc 1
ldc 2
invokevirtual Ex/hi(I)I
isum
aload_0
getfield a/1
if_icmpge LABEL3
iconst_1
goto LABEL4
LABEL3: iconst_0
LABEL4: istore 2
iload 2
iload 3
iload 4
iload 5
ifeq LABEL5
iload 6
ifeq LABEL5
iconst_1
goto LABEL6
LABEL5: iconst_0
ireturn
.end method
.method public test()Z
.limit locals 1
ireturn
.end method
.method public and(II)Z
.limit locals 6
iload 4
iload 5
ifeq LABEL7
iload 4
iload 5
ifeq LABEL7
iconst_1
goto LABEL8
LABEL7: iconst_0
LABEL8: istore 3
iload 4
ifeq LABEL9
if_icmpge LABEL9
iconst_1
goto LABEL10
LABEL9: iconst_0
LABEL10: istore 3
ireturn
.end method
.method public hi(I)I
.limit locals 2
ireturn
.end method
