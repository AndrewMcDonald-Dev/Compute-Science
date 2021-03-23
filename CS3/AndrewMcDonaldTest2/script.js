function constructor(id, nick, place) {
  this.name = nick;
  this.value = Number(document.getElementById(id).value);
  this.max_range = document.getElementById(id).max;
  this.places = place;
}

document.getElementById('cal').addEventListener('click', () => {
  const val = [
    new constructor('tip', 'tip percent', 0),
    new constructor('price', 'price', 2),
    new constructor('tax', 'tax percent', 2),
  ];

  let problem = false;
  for (let x of val) {
    if (!checkDeci(String(x.value), x.places)) {
      reset();
      alert(`Invalid entry for the ${x.name}. Too many decimal places`);
      problem = true;
    }
    if (x.value < 0 || x.value > x.max_range) {
      reset();
      alert(
        `Invalid entry for ${x.name}. Must be a number between 0 and ${x.max_range}`
      );
      problem = true;
    }
  }
  if (problem) return;

  const tax$ = val[1].value * (val[2].value / 100);
  const tip$ = (tax$ + val[1].value) * (val[0].value / 100);
  const total$ = val[1].value + tax$ + tip$;

  document.getElementById('outP').innerHTML = `Price: $${val[1].value.toFixed(
    2
  )}`;
  document.getElementById('outTA').innerHTML = `Tax: $${tax$.toFixed(2)}`;
  document.getElementById('outTI').innerHTML = `Tip: $${tip$.toFixed(2)}`;
  document.getElementById('outTO').innerHTML = `Total: $${total$.toFixed(2)}`;
});

const reset = function () {
  document.getElementById('outP').innerHTML = ``;
  document.getElementById('outTA').innerHTML = ``;
  document.getElementById('outTI').innerHTML = ``;
  document.getElementById('outTO').innerHTML = ``;
};

const checkDeci = function (num, place) {
  if (num.includes('.')) {
    const arr = num.split('.');
    if (arr[1].length > place) return false;
    else return true;
  } else return true;
};
