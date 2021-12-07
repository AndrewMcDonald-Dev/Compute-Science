const ackermann = (m: number, n: number): number => {
    if (m == 0) return n + 1;
    else if (m > 0 && n == 0) return ackermann(m - 1, 1);
    else if (m > 0 && n > 0) return ackermann(m - 1, ackermann(m, n - 1));
    else throw new Error('Something went terribly wrong');
};

console.log(`M = 0; N = 0: ${ackermann(0, 0)}`);
console.log(`M = 1; N = 1: ${ackermann(1, 1)}`);
console.log(`M = 2; N = 2: ${ackermann(2, 2)}`);
console.log(`M = 3; N = 3: ${ackermann(3, 3)}`);
