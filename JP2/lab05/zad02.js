"use strict";

const arr = [-1, -2, 4, -4, 3];

// const res = arr.reduce((prev, curr, currI) => {
//     if (curr < 0){
//         console.log(curr**2);
//         arr.splice(currI,currI);
//     };
// }, arr[0]);

//lub

const res2 = arr.reduce((prev, curr) => {
  // if (curr < 0) {
  //     return [...prev, curr]
  // }
  // return prev;
  return curr < 0 ? [...prev, curr ** 2] : prev;
}, []);

console.log(res2);
