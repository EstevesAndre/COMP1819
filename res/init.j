.class public init
.field public aaa I
.method static public main([Ljava/lang/String;)V
.limit locals 2
iconst_1
ifeq LABEL1
iconst_0
ifeq LABEL1
iconst_1
goto LABEL2
LABEL1: iconst_0
LABEL2: istore 1
.end method
.method public hi(I)Z
.limit locals 2
invokevirtual init/fds()Z
ireturn
.end method
.method public fds()Z
.limit locals 1
iconst_ 1
ireturn
.end method
