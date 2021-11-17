@ Tue Nov 16 15:43:13 2021                  Andrew McDonald
@ Compiler    = c2.py
@ Input file  = c2.in
@ Output file = c2.s
@------------------------------------------- Assembler code
          .global main
          .text
main:
          push {lr}

@ print(-59 + 20*3)
      ldr r0, =.fmt0
      ldr r1, .i1
      bl printf

@ a = 2
      ldr r4, .i2

@ bb_1 = -(a) + 12
      mov r0, r4
      neg r0, r0
      mov r6, r0
       ldr r1, .i12
      add r6, r6, r1
      mov r5, r6

@ print(a*bb_1 + a*3*(-1 + -1 + -1))
      mul r6, r4, r5
       ldr r1, .i3
      mul r7, r4, r1
       ldr r1, .i_3
      mul r7, r7, r1
      add r6, r6, r7
      ldr r0, =.fmt0
      mov r1, r6
      bl printf

          mov r0, #0
          pop {pc}

.fmt0:      .asciz "%d\n"
.i_59:    .word -59
.i20:     .word 20
.i3:      .word 3
.i60:     .word 60
.i1:      .word 1
a:        .word 0
.i2:      .word 2
bb_1:     .word 0
.t0:      .word 0
.i12:     .word 12
.t1:      .word 0
.i_1:     .word -1
.i_2:     .word -2
.i_3:     .word -3
