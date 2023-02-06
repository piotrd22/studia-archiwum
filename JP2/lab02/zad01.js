"use strict";

const numbers = [4, 3, 5, 1, 3, 2, 21, 1, 65, -43, 9];
let numbersv2 = [];
let suma = 0;

for (let i = 0; i < numbers.length; i++) {
  for (let j = 0; j < numbers.length; j++) {
    if (numbers[i] === numbers[j]) {
      suma++;
    }
  }
  if (suma === 1) {
    numbersv2.push(numbers[i]);
  }
  suma = 0;
}

console.log(numbersv2);
