.class public init
.super java/lang/Object
.field public aaa I
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 2
return
.end method
.method public hi(I)Z
.limit stack 100
.limit locals 2
invokestatic init/fds()Z
ireturn
.end method
.method public not()Z
.limit stack 100
.limit locals 3
iconst_1
istore 1
iconst_1
istore 2
iload 2
ifne LABEL1
iconst_0
istore 1
goto LABEL2
LABEL1:
LABEL2:
iload 1
ifne LABEL3
goto LABEL4
LABEL3:
LABEL4:
iconst_1
ireturn
.end method
.method public fds()Z
.limit stack 100
.limit locals 1
iconst_1
ireturn
.end method
