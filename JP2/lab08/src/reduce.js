"use strict";

const arr = [
  { key: 0, x: [4, 5, 6], y: [1, 2, 3, 4] },
  { key: 0, x: [1], y: [] },
];

const res1 = arr.reduce((acc, curr) => {
  return Object.values(curr).reduce((prev, curr2) => {
    if (typeof curr2 === "object") {
      return prev + curr2.length;
    } else {
      return acc;
    }
  }, 0);
}, 0);

console.log(res1);
