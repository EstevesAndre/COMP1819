.class public Quicksort
.super java/lang/Object
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 5
bipush 10
newarray int
astore_1
iconst_0
istore_2
LABEL1:
iload_2
aload_1
arraylength
if_icmpge LABEL2
aload_1
iload_2
aload_1
arraylength
iload_2
isub
iastore
iload_2
iconst_1
iadd
istore_2
goto LABEL1
LABEL2:
new Quicksort
dup
invokespecial Quicksort/<init>()V
astore_3
aload_3
aload_1
invokevirtual Quicksort/quicksort([I)Z
istore 4
aload_3
aload_1
invokevirtual Quicksort/printL([I)Z
istore 4
return
.end method

.method public printL([I)Z
.limit stack 100
.limit locals 3
iconst_0
istore_2
LABEL3:
iload_2
aload_1
arraylength
if_icmpge LABEL4
aload_1
iload_2
iaload
invokestatic io/println(I)V
iload_2
iconst_1
iadd
istore_2
goto LABEL3
LABEL4:
iconst_1
ireturn
.end method

.method public quicksort([I)Z
.limit stack 100
.limit locals 2
aload_0
aload_1
iconst_0
aload_1
arraylength
iconst_1
isub
invokevirtual Quicksort/quicksort([III)Z
ireturn
.end method

.method public quicksort([III)Z
.limit stack 100
.limit locals 5
iload_2
iload_3
if_icmpge LABEL5
aload_0
aload_1
iload_2
iload_3
invokevirtual Quicksort/partition([III)I
istore 4
aload_0
aload_1
iload_2
iload 4
iconst_1
isub
invokevirtual Quicksort/quicksort([III)Z
pop
aload_0
aload_1
iload 4
iconst_1
iadd
iload_3
invokevirtual Quicksort/quicksort([III)Z
pop
goto LABEL6
LABEL5:
LABEL6:
iconst_1
ireturn
.end method

.method public partition([III)I
.limit stack 100
.limit locals 8
aload_1
iload_3
iaload
istore 4
iload_2
istore 5
iload_2
istore 6
LABEL7:
iload 6
iload_3
if_icmpge LABEL8
aload_1
iload 6
iaload
iload 4
if_icmpge LABEL9
aload_1
iload 5
iaload
istore 7
aload_1
iload 5
aload_1
iload 6
iaload
iastore
aload_1
iload 6
iload 7
iastore
iload 5
iconst_1
iadd
istore 5
goto LABEL10
LABEL9:
LABEL10:
iload 6
iconst_1
iadd
istore 6
goto LABEL7
LABEL8:
aload_1
iload 5
iaload
istore 7
aload_1
iload 5
aload_1
iload_3
iaload
iastore
aload_1
iload_3
iload 7
iastore
iload 5
ireturn
.end method

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

