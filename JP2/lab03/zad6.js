"use strict";

function liczenie(arr) {
  let rzad = 0;
  for (let i = 0; i < arr.length; i++) {
    console.log("Rząd", rzad);
    rzad++;
    for (let j = 0; j < arr[i].length; j++) {
      console.log(arr[i][j]);
    }
  }
}

liczenie([
  [1, 2, 1, 24],
  [8, 11, 9, 4],
  [7, 0, 7, 27],
  [7, 4, 28, 14],
  [3, 10, 26, 7],
]);
