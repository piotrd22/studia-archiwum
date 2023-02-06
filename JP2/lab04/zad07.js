"use strict";

function pali(arr) {
  let help = [];
  for (let j = arr.length - 1; j > -1; j--) {
    help.push(arr[j]);
  }
  let x = 0;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] === help[i]) {
      x++;
    }
  }
  if (x === arr.length) {
    return 1;
  } else {
    return 0;
  }
}

console.log(pali([1, 2, 1, 2, 1]));
