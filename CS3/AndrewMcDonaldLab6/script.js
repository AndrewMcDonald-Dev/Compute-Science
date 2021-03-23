document.getElementById('cal').addEventListener('click', () => {
  const val = [
    Number(document.getElementById('numA').value),
    Number(document.getElementById('numB').value),
    Number(document.getElementById('numC').value),
  ];

  if (val[0] === 0) {
    alert('a cannont be 0');
    return;
  }
  
  for (let x of val) {
    if (!Number.isInteger(x)) {
      alert('Values must be integers');
      return;
    }
    if (x > 99 || x < -99) {
      alert('Value is out of range');
      return;
    }
  }

  let squareB = val[1] * val[1];
  let ac4 = 4 * val[0] * val[2];

  let answer = 'Solution: ';

  if (squareB < ac4) {
    answer += "x's roots are imaginary.";
    document.getElementById('out').innerHTML = answer;
    return;
  }
  answer +=
    'x = ' + ((-val[1] + Math.sqrt(squareB - ac4)) / (2 * val[0])).toFixed(3);

  if (squareB > ac4)
    answer +=
      ', x = ' +
      ((-val[1] - Math.sqrt(squareB - ac4)) / (2 * val[0])).toFixed(3);

  document.getElementById('out').innerHTML = answer;
});
