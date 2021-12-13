const n = 8;
let countK = 0;
let countD = 0;
let countI = 0;
for (let d = 2; d < n; d++) {
    countD++;
    for (let i = 1; i < n - d + 1; i++) {
        countI++;
        for (let k = i; k < i + d - 1; k++) {
            countK++;
        }
    }
}

console.log(`D = ${countD}`);
console.log(`I = ${countI}`);
console.log(`K = ${countK}`);
