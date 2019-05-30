.class public Life
.super java/lang/Object
.field public 'UNDERPOP_LIM' I
.field public 'OVERPOP_LIM' I
.field public 'REPRODUCE_NUM' I
.field public 'LOOPS_PER_MS' I
.field public 'xMax' I
.field public 'yMax' I
.field public 'field' [I
.method static public main([Ljava/lang/String;)V
.limit stack 100
.limit locals 3
new Life
dup
invokespecial Life/<init>()V
astore 1
aload 1
invokevirtual Life/init()Z
LABEL1:
ifeq LABEL2
aload 1
invokevirtual Life/printField()V
aload 1
invokevirtual Life/update()Z
pop
invokestatic io/read()I
istore 2
goto LABEL1
LABEL2:
return
.end method

.method public init()Z
.limit stack 100
.limit locals 3
ldc 1
newarray int
astore 1
aload_0
ldc 2
putfield Life/UNDERPOP_LIM I
aload_0
ldc 3
putfield Life/OVERPOP_LIM I
aload_0
ldc 3
putfield Life/REPRODUCE_NUM I
aload_0
ldc 225000
putfield Life/LOOPS_PER_MS I
aload_0
aload 1
invokevirtual Life/field([I)[I
putfield Life/field [I
aload 1
ldc 0
iaload
istore 2
aload_0
iload 2
ldc 1
isub
putfield Life/xMax I
aload_0
aload_0
getfield Life/field [I
arraylength
iload 2
idiv
ldc 1
isub
putfield Life/yMax I
iconst_1
ireturn
.end method

.method public field([I)[I
.limit stack 100
.limit locals 3
ldc 100
newarray int
astore 2
aload 1
ldc 0
ldc 10
iastore
aload 2
ldc 0
ldc 0
iastore
aload 2
ldc 1
ldc 0
iastore
aload 2
ldc 2
ldc 1
iastore
aload 2
ldc 3
ldc 0
iastore
aload 2
ldc 4
ldc 0
iastore
aload 2
ldc 5
ldc 0
iastore
aload 2
ldc 6
ldc 0
iastore
aload 2
ldc 7
ldc 0
iastore
aload 2
ldc 8
ldc 0
iastore
aload 2
ldc 9
ldc 0
iastore
aload 2
ldc 10
ldc 1
iastore
aload 2
ldc 11
ldc 0
iastore
aload 2
ldc 12
ldc 1
iastore
aload 2
ldc 13
ldc 0
iastore
aload 2
ldc 14
ldc 0
iastore
aload 2
ldc 15
ldc 0
iastore
aload 2
ldc 16
ldc 0
iastore
aload 2
ldc 17
ldc 0
iastore
aload 2
ldc 18
ldc 0
iastore
aload 2
ldc 19
ldc 0
iastore
aload 2
ldc 20
ldc 0
iastore
aload 2
ldc 21
ldc 1
iastore
aload 2
ldc 22
ldc 1
iastore
aload 2
ldc 23
ldc 0
iastore
aload 2
ldc 24
ldc 0
iastore
aload 2
ldc 25
ldc 0
iastore
aload 2
ldc 26
ldc 0
iastore
aload 2
ldc 27
ldc 0
iastore
aload 2
ldc 28
ldc 0
iastore
aload 2
ldc 29
ldc 0
iastore
aload 2
ldc 30
ldc 0
iastore
aload 2
ldc 31
ldc 0
iastore
aload 2
ldc 32
ldc 0
iastore
aload 2
ldc 33
ldc 0
iastore
aload 2
ldc 34
ldc 0
iastore
aload 2
ldc 35
ldc 0
iastore
aload 2
ldc 36
ldc 0
iastore
aload 2
ldc 37
ldc 0
iastore
aload 2
ldc 38
ldc 0
iastore
aload 2
ldc 39
ldc 0
iastore
aload 2
ldc 40
ldc 0
iastore
aload 2
ldc 41
ldc 0
iastore
aload 2
ldc 42
ldc 0
iastore
aload 2
ldc 43
ldc 0
iastore
aload 2
ldc 44
ldc 0
iastore
aload 2
ldc 45
ldc 0
iastore
aload 2
ldc 46
ldc 0
iastore
aload 2
ldc 47
ldc 0
iastore
aload 2
ldc 48
ldc 0
iastore
aload 2
ldc 49
ldc 0
iastore
aload 2
ldc 50
ldc 0
iastore
aload 2
ldc 51
ldc 0
iastore
aload 2
ldc 52
ldc 0
iastore
aload 2
ldc 53
ldc 0
iastore
aload 2
ldc 54
ldc 0
iastore
aload 2
ldc 55
ldc 0
iastore
aload 2
ldc 56
ldc 0
iastore
aload 2
ldc 57
ldc 0
iastore
aload 2
ldc 58
ldc 0
iastore
aload 2
ldc 59
ldc 0
iastore
aload 2
ldc 60
ldc 0
iastore
aload 2
ldc 61
ldc 0
iastore
aload 2
ldc 62
ldc 0
iastore
aload 2
ldc 63
ldc 0
iastore
aload 2
ldc 64
ldc 0
iastore
aload 2
ldc 65
ldc 0
iastore
aload 2
ldc 66
ldc 0
iastore
aload 2
ldc 67
ldc 0
iastore
aload 2
ldc 68
ldc 0
iastore
aload 2
ldc 69
ldc 0
iastore
aload 2
ldc 70
ldc 0
iastore
aload 2
ldc 71
ldc 0
iastore
aload 2
ldc 72
ldc 0
iastore
aload 2
ldc 73
ldc 0
iastore
aload 2
ldc 74
ldc 0
iastore
aload 2
ldc 75
ldc 0
iastore
aload 2
ldc 76
ldc 0
iastore
aload 2
ldc 77
ldc 0
iastore
aload 2
ldc 78
ldc 0
iastore
aload 2
ldc 79
ldc 0
iastore
aload 2
ldc 80
ldc 0
iastore
aload 2
ldc 81
ldc 0
iastore
aload 2
ldc 82
ldc 0
iastore
aload 2
ldc 83
ldc 0
iastore
aload 2
ldc 84
ldc 0
iastore
aload 2
ldc 85
ldc 0
iastore
aload 2
ldc 86
ldc 0
iastore
aload 2
ldc 87
ldc 0
iastore
aload 2
ldc 88
ldc 0
iastore
aload 2
ldc 89
ldc 0
iastore
aload 2
ldc 90
ldc 0
iastore
aload 2
ldc 91
ldc 0
iastore
aload 2
ldc 92
ldc 0
iastore
aload 2
ldc 93
ldc 0
iastore
aload 2
ldc 94
ldc 0
iastore
aload 2
ldc 95
ldc 0
iastore
aload 2
ldc 96
ldc 0
iastore
aload 2
ldc 97
ldc 0
iastore
aload 2
ldc 98
ldc 0
iastore
aload 2
ldc 99
ldc 0
iastore
aload 2
areturn
.end method

.method public update()Z
.limit stack 100
.limit locals 6
aload_0
getfield Life/field [I
arraylength
newarray int
astore 5
ldc 0
istore 1
LABEL3:
iload 1
aload_0
getfield Life/field [I
arraylength
if_icmpge LABEL4
aload_0
getfield Life/field [I
iload 1
iaload
istore 2
aload_0
iload 1
invokevirtual Life/getLiveNeighborN(I)I
istore 3
iload 2
ldc 1
if_icmplt LABEL5
aload_0
iload 3
aload_0
getfield Life/UNDERPOP_LIM I
invokevirtual Life/ge(II)V
ifeq LABEL7
aload_0
iload 3
aload_0
getfield Life/OVERPOP_LIM I
invokevirtual Life/le(II)V
ifeq LABEL7
iconst_1
goto LABEL8
LABEL7:
iconst_0
LABEL8:
istore 4
iload 4
ifne LABEL9
aload 5
iload 1
ldc 0
iastore
goto LABEL10
LABEL9:
aload 5
iload 1
aload_0
getfield Life/field [I
iload 1
iaload
iastore
LABEL10:
goto LABEL6
LABEL5:
aload_0
iload 3
aload_0
getfield Life/REPRODUCE_NUM I
invokevirtual Life/eq(II)V
ifeq LABEL11
aload 5
iload 1
ldc 1
iastore
goto LABEL12
LABEL11:
aload 5
iload 1
aload_0
getfield Life/field [I
iload 1
iaload
iastore
LABEL12:
LABEL6:
iload 1
ldc 1
iadd
istore 1
goto LABEL3
LABEL4:
aload_0
aload 5
putfield Life/field [I
iconst_1
ireturn
.end method

.method public trIdx(II)I
.limit stack 100
.limit locals 3
iload 1
aload_0
getfield Life/xMax I
ldc 1
iadd
iload 2
imul
iadd
ireturn
.end method

.method public cartIdx(I)[I
.limit stack 100
.limit locals 6
aload_0
getfield Life/xMax I
ldc 1
iadd
istore 4
iload 1
iload 4
idiv
istore 3
iload 1
iload 3
iload 4
imul
isub
istore 2
ldc 2
newarray int
astore 5
aload 5
ldc 0
iload 2
iastore
aload 5
ldc 1
iload 3
iastore
aload 5
areturn
.end method

.method public busyWait(I)Z
.limit stack 100
.limit locals 4
iload 1
aload_0
getfield Life/LOOPS_PER_MS I
imul
istore 3
ldc 0
istore 2
LABEL13:
iload 2
iload 3
if_icmpge LABEL14
iload 2
ldc 1
iadd
istore 2
goto LABEL13
LABEL14:
iconst_1
ireturn
.end method

.method public lt(II)Z
.limit stack 100
.limit locals 3
iload 1
iload 2
if_icmpge LABEL15
iconst_1
goto LABEL16
LABEL15:
iconst_0
LABEL16:
ireturn
.end method

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

