@ Ch11Q1.s
			.text
			.global _start
_start:		
			mov r1, #1		@ move 1 into r1
			mov r2, #1		@ move 1 into r2
			mov r3, #1		@ move 1 into r3
			add r4, r1, r2	@ adds r1 and r2 and places it in r4
			add r4, r4, r3	@ add r4 and r3 and places it in r4
			mov r7, #1		@ termination	
			svc 0