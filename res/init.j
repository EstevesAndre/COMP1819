.class public init
.field public aaa I
.method static public main([Ljava/lang/String;)V
.limit locals 1
.end method
.method public hi(I)Z
.limit locals 1
invokevirtual init/fds()Z
ireturn
.end method
.method public whilef()Z
.limit locals 1
iconst_true
istore 1
iconst_false
istore 2
iconst_true
istore 3
LABEL1:
iload 1
iload 2
iload 3
iconst_ 1
ireturn
.end method
.method public classs()I
.limit locals 1
ldc 0
ireturn
.end method
.method public arrays()I
.limit locals 1
ldc 1
istore 1
ldc 2
ldc 3
imul
istore 1
ldc 2
ldc 0
ireturn
.end method
.method public not()Z
.limit locals 1
iconst_true
istore 1
iconst_false
istore 2
iconst_ 1
ireturn
.end method
.method public fds()Z
.limit locals 1
iconst_ 1
ireturn
.end method
