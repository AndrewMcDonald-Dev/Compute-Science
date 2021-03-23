cells = ['north', 'west', 'east', 'south'];

inputs = ['top', 'left', 'right', 'bottom'];

const swap = (id, id1) => {
  let temp = document.getElementById(id).textContent;
  document.getElementById(id).innerHTML = document.getElementById(
    id1
  ).textContent;
  document.getElementById(id1).innerHTML = temp;
};

const swapLeft = () => {
  return swap('north', 'west');
};

const swapMiddle = () => {
  return swap('west', 'east');
};

const swapRight = () => {
  return swap('east', 'south');
};

const restart = () => {
  for (let i = 0; i < 4; i++) {
    document.getElementById(cells[i]).innerHTML = ' ';
  }
};

const update = () => {
  for (let i = 0; i < 4; i++) {
    document.getElementById(cells[i]).innerHTML = document
      .getElementById(inputs[i])
      .value.toUpperCase();
  }
};
