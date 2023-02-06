"use strict";
// ZADANIE 3

let n = 0;
let zmienna;
function startCounter() {
  zmienna = setInterval(() => {
    n++;
    console.log(`${n}`);
  }, 1000);
}

function stopCounter() {
  clearInterval(zmienna);
}

function clearCounter() {
  n = 0;
}
