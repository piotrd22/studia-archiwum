"use strict";

const arr = [5, 3, 1];

const suma = arr.reduce((prev, curr) => {
  return prev + curr;
});
console.log(suma);
