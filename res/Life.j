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
astore_1
aload_1
invokevirtual Life/init()Z
pop
LABEL1:
iconst_1
ifeq LABEL2
aload_1
invokevirtual Life/printField()Z
pop
aload_1
invokevirtual Life/update()Z
pop
invokestatic io/read()I
istore_2
goto LABEL1
LABEL2:
return
.end method

.method public init()Z
.limit stack 100
.limit locals 3
iconst_1
newarray int
astore_1
aload_0
iconst_2
putfield Life/UNDERPOP_LIM I
aload_0
iconst_3
putfield Life/OVERPOP_LIM I
aload_0
iconst_3
putfield Life/REPRODUCE_NUM I
aload_0
ldc 225000
putfield Life/LOOPS_PER_MS I
aload_0
aload_0
aload_1
invokevirtual Life/field([I)[I
putfield Life/field [I
aload_1
iconst_0
iaload
istore_2
aload_0
iload_2
iconst_1
isub
putfield Life/xMax I
aload_0
aload_0
getfield Life/field [I
arraylength
iload_2
idiv
iconst_1
isub
putfield Life/yMax I
iconst_1
ireturn
.end method

.method public field([I)[I
.limit stack 100
.limit locals 3
bipush 100
newarray int
astore_2
aload_1
iconst_0
bipush 10
iastore
aload_2
iconst_0
iconst_0
iastore
aload_2
iconst_1
iconst_0
iastore
aload_2
iconst_2
iconst_1
iastore
aload_2
iconst_3
iconst_0
iastore
aload_2
iconst_4
iconst_0
iastore
aload_2
iconst_5
iconst_0
iastore
aload_2
bipush 6
iconst_0
iastore
aload_2
bipush 7
iconst_0
iastore
aload_2
bipush 8
iconst_0
iastore
aload_2
bipush 9
iconst_0
iastore
aload_2
bipush 10
iconst_1
iastore
aload_2
bipush 11
iconst_0
iastore
aload_2
bipush 12
iconst_1
iastore
aload_2
bipush 13
iconst_0
iastore
aload_2
bipush 14
iconst_0
iastore
aload_2
bipush 15
iconst_0
iastore
aload_2
bipush 16
iconst_0
iastore
aload_2
bipush 17
iconst_0
iastore
aload_2
bipush 18
iconst_0
iastore
aload_2
bipush 19
iconst_0
iastore
aload_2
bipush 20
iconst_0
iastore
aload_2
bipush 21
iconst_1
iastore
aload_2
bipush 22
iconst_1
iastore
aload_2
bipush 23
iconst_0
iastore
aload_2
bipush 24
iconst_0
iastore
aload_2
bipush 25
iconst_0
iastore
aload_2
bipush 26
iconst_0
iastore
aload_2
bipush 27
iconst_0
iastore
aload_2
bipush 28
iconst_0
iastore
aload_2
bipush 29
iconst_0
iastore
aload_2
bipush 30
iconst_0
iastore
aload_2
bipush 31
iconst_0
iastore
aload_2
bipush 32
iconst_0
iastore
aload_2
bipush 33
iconst_0
iastore
aload_2
bipush 34
iconst_0
iastore
aload_2
bipush 35
iconst_0
iastore
aload_2
bipush 36
iconst_0
iastore
aload_2
bipush 37
iconst_0
iastore
aload_2
bipush 38
iconst_0
iastore
aload_2
bipush 39
iconst_0
iastore
aload_2
bipush 40
iconst_0
iastore
aload_2
bipush 41
iconst_0
iastore
aload_2
bipush 42
iconst_0
iastore
aload_2
bipush 43
iconst_0
iastore
aload_2
bipush 44
iconst_0
iastore
aload_2
bipush 45
iconst_0
iastore
aload_2
bipush 46
iconst_0
iastore
aload_2
bipush 47
iconst_0
iastore
aload_2
bipush 48
iconst_0
iastore
aload_2
bipush 49
iconst_0
iastore
aload_2
bipush 50
iconst_0
iastore
aload_2
bipush 51
iconst_0
iastore
aload_2
bipush 52
iconst_0
iastore
aload_2
bipush 53
iconst_0
iastore
aload_2
bipush 54
iconst_0
iastore
aload_2
bipush 55
iconst_0
iastore
aload_2
bipush 56
iconst_0
iastore
aload_2
bipush 57
iconst_0
iastore
aload_2
bipush 58
iconst_0
iastore
aload_2
bipush 59
iconst_0
iastore
aload_2
bipush 60
iconst_0
iastore
aload_2
bipush 61
iconst_0
iastore
aload_2
bipush 62
iconst_0
iastore
aload_2
bipush 63
iconst_0
iastore
aload_2
bipush 64
iconst_0
iastore
aload_2
bipush 65
iconst_0
iastore
aload_2
bipush 66
iconst_0
iastore
aload_2
bipush 67
iconst_0
iastore
aload_2
bipush 68
iconst_0
iastore
aload_2
bipush 69
iconst_0
iastore
aload_2
bipush 70
iconst_0
iastore
aload_2
bipush 71
iconst_0
iastore
aload_2
bipush 72
iconst_0
iastore
aload_2
bipush 73
iconst_0
iastore
aload_2
bipush 74
iconst_0
iastore
aload_2
bipush 75
iconst_0
iastore
aload_2
bipush 76
iconst_0
iastore
aload_2
bipush 77
iconst_0
iastore
aload_2
bipush 78
iconst_0
iastore
aload_2
bipush 79
iconst_0
iastore
aload_2
bipush 80
iconst_0
iastore
aload_2
bipush 81
iconst_0
iastore
aload_2
bipush 82
iconst_0
iastore
aload_2
bipush 83
iconst_0
iastore
aload_2
bipush 84
iconst_0
iastore
aload_2
bipush 85
iconst_0
iastore
aload_2
bipush 86
iconst_0
iastore
aload_2
bipush 87
iconst_0
iastore
aload_2
bipush 88
iconst_0
iastore
aload_2
bipush 89
iconst_0
iastore
aload_2
bipush 90
iconst_0
iastore
aload_2
bipush 91
iconst_0
iastore
aload_2
bipush 92
iconst_0
iastore
aload_2
bipush 93
iconst_0
iastore
aload_2
bipush 94
iconst_0
iastore
aload_2
bipush 95
iconst_0
iastore
aload_2
bipush 96
iconst_0
iastore
aload_2
bipush 97
iconst_0
iastore
aload_2
bipush 98
iconst_0
iastore
aload_2
bipush 99
iconst_0
iastore
aload_2
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
iconst_0
istore_1
LABEL3:
iload_1
aload_0
getfield Life/field [I
arraylength
if_icmpge LABEL4
aload_0
getfield Life/field [I
iload_1
iaload
istore_2
aload_0
iload_1
invokevirtual Life/getLiveNeighborN(I)I
istore_3
iload_2
iconst_1
if_icmplt LABEL5
aload_0
iload_3
aload_0
getfield Life/UNDERPOP_LIM I
invokevirtual Life/ge(II)Z
ifeq LABEL7
aload_0
iload_3
aload_0
getfield Life/OVERPOP_LIM I
invokevirtual Life/le(II)Z
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
iload_1
iconst_0
iastore
goto LABEL10
LABEL9:
aload 5
iload_1
aload_0
getfield Life/field [I
iload_1
iaload
iastore
LABEL10:
goto LABEL6
LABEL5:
aload_0
iload_3
aload_0
getfield Life/REPRODUCE_NUM I
invokevirtual Life/eq(II)Z
ifeq LABEL11
aload 5
iload_1
iconst_1
iastore
goto LABEL12
LABEL11:
aload 5
iload_1
aload_0
getfield Life/field [I
iload_1
iaload
iastore
LABEL12:
LABEL6:
iload_1
iconst_1
iadd
istore_1
goto LABEL3
LABEL4:
aload_0
aload 5
putfield Life/field [I
iconst_1
ireturn
.end method

.method public printField()Z
.limit stack 100
.limit locals 3
iconst_0
istore_1
iconst_0
istore_2
LABEL13:
iload_1
aload_0
getfield Life/field [I
arraylength
if_icmpge LABEL14
aload_0
iload_2
aload_0
getfield Life/xMax I
invokevirtual Life/gt(II)Z
ifeq LABEL15
invokestatic io/println()V
iconst_0
istore_2
goto LABEL16
LABEL15:
LABEL16:
aload_0
getfield Life/field [I
iload_1
iaload
invokestatic io/print(I)V
iload_1
iconst_1
iadd
istore_1
iload_2
iconst_1
iadd
istore_2
goto LABEL13
LABEL14:
invokestatic io/println()V
invokestatic io/println()V
iconst_1
ireturn
.end method

.method public trIdx(II)I
.limit stack 100
.limit locals 3
iload_1
aload_0
getfield Life/xMax I
iconst_1
iadd
iload_2
imul
iadd
ireturn
.end method

.method public cartIdx(I)[I
.limit stack 100
.limit locals 6
aload_0
getfield Life/xMax I
iconst_1
iadd
istore 4
iload_1
iload 4
idiv
istore_3
iload_1
iload_3
iload 4
imul
isub
istore_2
iconst_2
newarray int
astore 5
aload 5
iconst_0
iload_2
iastore
aload 5
iconst_1
iload_3
iastore
aload 5
areturn
.end method

.method public getNeighborCoords(I)[I
.limit stack 100
.limit locals 10
aload_0
iload_1
invokevirtual Life/cartIdx(I)[I
astore 8
aload 8
iconst_0
iaload
istore_2
aload 8
iconst_1
iaload
istore_3
iload_2
aload_0
getfield Life/xMax I
if_icmpge LABEL17
iload_2
iconst_1
iadd
istore 6
aload_0
iload_2
iconst_0
invokevirtual Life/gt(II)Z
ifeq LABEL19
iload_2
iconst_1
isub
istore 4
goto LABEL20
LABEL19:
aload_0
getfield Life/xMax I
istore 4
LABEL20:
goto LABEL18
LABEL17:
iconst_0
istore 6
iload_2
iconst_1
isub
istore 4
LABEL18:
iload_3
aload_0
getfield Life/yMax I
if_icmpge LABEL21
iload_3
iconst_1
iadd
istore 7
aload_0
iload_3
iconst_0
invokevirtual Life/gt(II)Z
ifeq LABEL23
iload_3
iconst_1
isub
istore 5
goto LABEL24
LABEL23:
aload_0
getfield Life/yMax I
istore 5
LABEL24:
goto LABEL22
LABEL21:
iconst_0
istore 7
iload_3
iconst_1
isub
istore 5
LABEL22:
bipush 8
newarray int
astore 9
aload 9
iconst_0
aload_0
iload_2
iload 5
invokevirtual Life/trIdx(II)I
iastore
aload 9
iconst_1
aload_0
iload 4
iload 5
invokevirtual Life/trIdx(II)I
iastore
aload 9
iconst_2
aload_0
iload 4
iload_3
invokevirtual Life/trIdx(II)I
iastore
aload 9
iconst_3
aload_0
iload 4
iload 7
invokevirtual Life/trIdx(II)I
iastore
aload 9
iconst_4
aload_0
iload_2
iload 7
invokevirtual Life/trIdx(II)I
iastore
aload 9
iconst_5
aload_0
iload 6
iload 7
invokevirtual Life/trIdx(II)I
iastore
aload 9
bipush 6
aload_0
iload 6
iload_3
invokevirtual Life/trIdx(II)I
iastore
aload 9
bipush 7
aload_0
iload 6
iload 5
invokevirtual Life/trIdx(II)I
iastore
aload 9
areturn
.end method

.method public getLiveNeighborN(I)I
.limit stack 100
.limit locals 5
iconst_0
istore 4
aload_0
iload_1
invokevirtual Life/getNeighborCoords(I)[I
astore_2
iconst_0
istore_3
LABEL25:
iload_3
aload_2
arraylength
if_icmpge LABEL26
aload_0
aload_0
getfield Life/field [I
aload_2
iload_3
iaload
iaload
iconst_0
invokevirtual Life/ne(II)Z
ifeq LABEL27
iload 4
iconst_1
iadd
istore 4
goto LABEL28
LABEL27:
LABEL28:
iload_3
iconst_1
iadd
istore_3
goto LABEL25
LABEL26:
iload 4
ireturn
.end method

.method public busyWait(I)Z
.limit stack 100
.limit locals 4
iload_1
aload_0
getfield Life/LOOPS_PER_MS I
imul
istore_3
iconst_0
istore_2
LABEL29:
iload_2
iload_3
if_icmpge LABEL30
iload_2
iconst_1
iadd
istore_2
goto LABEL29
LABEL30:
iconst_1
ireturn
.end method

.method public eq(II)Z
.limit stack 100
.limit locals 3
aload_0
iload_1
iload_2
invokevirtual Life/lt(II)Z
ifne LABEL31
aload_0
iload_2
iload_1
invokevirtual Life/lt(II)Z
ifne LABEL31
iconst_1
goto LABEL32
LABEL31:
iconst_0
LABEL32:
ireturn
.end method

.method public ne(II)Z
.limit stack 100
.limit locals 3
aload_0
iload_1
iload_2
invokevirtual Life/eq(II)Z
ifeq LABEL33
iconst_0
goto LABEL34
LABEL33:
iconst_1
LABEL34:
ireturn
.end method

.method public lt(II)Z
.limit stack 100
.limit locals 3
iload_1
iload_2
if_icmpge LABEL35
iconst_1
goto LABEL36
LABEL35:
iconst_0
LABEL36:
ireturn
.end method

.method public le(II)Z
.limit stack 100
.limit locals 3
aload_0
iload_1
iload_2
invokevirtual Life/lt(II)Z
ifne LABEL37
aload_0
iload_1
iload_2
invokevirtual Life/eq(II)Z
ifne LABEL37
iconst_1
goto LABEL38
LABEL37:
iconst_0
LABEL38:
ifeq LABEL39
iconst_0
goto LABEL40
LABEL39:
iconst_1
LABEL40:
ireturn
.end method

.method public gt(II)Z
.limit stack 100
.limit locals 3
aload_0
iload_1
iload_2
invokevirtual Life/le(II)Z
ifeq LABEL41
iconst_0
goto LABEL42
LABEL41:
iconst_1
LABEL42:
ireturn
.end method

.method public ge(II)Z
.limit stack 100
.limit locals 3
aload_0
iload_1
iload_2
invokevirtual Life/gt(II)Z
ifne LABEL43
aload_0
iload_1
iload_2
invokevirtual Life/eq(II)Z
ifne LABEL43
iconst_1
goto LABEL44
LABEL43:
iconst_0
LABEL44:
ifeq LABEL45
iconst_0
goto LABEL46
LABEL45:
iconst_1
LABEL46:
ireturn
.end method

; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

