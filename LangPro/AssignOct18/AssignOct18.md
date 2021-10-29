# Andrew McDonald

### Chapter 9

```
Ch9Q2:
	co_names = ['a','b','c']
	co_consts = [None, None, None]
	co_values = [1,2,3,4]
	co_code = [
		# a = 1 + 2
		100,0,100,1,23,100,2,23,90,0,
		# b = a + 4
		101, 0, 100, 3, 20, 90, 1
		# c = a + b
		101, 0, 101, 1, 90, 2
	]
```

```
Ch9Q3:
	x = 2 * 3 + 4
```

```
Ch9Q4:
	x = 2 * 3 + 4
	print(x)
```

---

### Chapter 10

```
Ch10Q2:
	The order of testing the opcodes does not matter when it comes to if out program will produce the expected output but for efficiency sake it is important to put the most used opcodes towards the beginning as to reduce of the number of checks.
```

```
Ch10Q3:
	co_values is not global because it is only used in the interpereter section of the code while co_names is used throughout both the interpreter and many other function in the program.
```

```
Ch10Q5:
	BINARY_ADD does not call the exterior function for popping two operands and returning them instead it does this operation itself for the sake of simplicity in the code.
```

---

### Chapter 11

```

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
```

```

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
```

```
Ch11Q9:
	The reason for the program's failure would be the excessive reserved space. The 20,000 bytes reserved exceeded the amount the Raspberry Pi assembly language can handle.
```

---
