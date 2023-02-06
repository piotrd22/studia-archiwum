"use strict";

const arr = [1, 3, 6, 2, 9];

const res = arr.reduce((prev, curr, currI) => {
  console.log(currI, ":", curr);
}, 0);
