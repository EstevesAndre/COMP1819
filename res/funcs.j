.class public funcs
.super java/lang/Object
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 3
new test_class
dup
invokespecial test_class/<init>()V
astore 1
aload 1
invokevirtual test_class/yo()V
iconst_1
istore 2
invokestatic ioPlus/printHelloWorld()V
return
.end method
.method public oi()I
.limit stack 100
.limit locals 1
aload_0
ldc 1
ldc 2
invokevirtual funcs/ya(II)I
ireturn
.end method
.method public ya(II)I
.limit stack 100
.limit locals 3
iload 1
ireturn
.end method
