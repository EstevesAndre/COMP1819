.class public Ex
.field public a I
.method static public main([Ljava/lang/String;)V
.limit locals 2
.end method
.method public oi(I)I
.limit locals 3
ldc 1
ldc 2
invokevirtual Ex/hi(I)I
isum
aload_0
getfield a/1
idiv
if_icmpge LABEL1
iconst_1
goto LABEL2
LABEL1: iconst_0
LABEL2: istore 2
aload_0
getfield a/1
ldc 20
idiv
if_icmpge LABEL3
iconst_1
goto LABEL4
LABEL3: iconst_0
.end method
.method public hi(I)I
.limit locals 2
.end method
