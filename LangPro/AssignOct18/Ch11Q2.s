@ Ch11Q2.s
			.text
			.global _start
g:			mov r2, #2
			add r0, r0, r2
			move r7, #1
			svc 0
f:			mov r1, #1
			add r0, r0, r1
			bl g
_start:		mov r0, #1
			bl f