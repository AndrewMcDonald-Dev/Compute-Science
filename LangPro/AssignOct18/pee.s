@Q2:
			.text
			.global _start
			.global f
			.global g
_start:
			mov r0, #1
			bl f
f:
			mov r1, #1
			add r0, r0, r1
			bl g
g:
			mov r1, #2
			add, r0, r0, r1
			mov r7, #1
			svc 0
