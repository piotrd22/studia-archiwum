"use strict";

function toArray(x, y) {
  let array = [];
  if (Array.isArray(x) && Array.isArray(y)) {
    array = [...x, ...y];
  } else if (Array.isArray(x) || Array.isArray(y)) {
    if (Array.isArray(x)) {
      array = [...x, y];
    } else {
      array = [x, ...y];
    }
  } else {
    array = [x, y];
  }
  return array;
}

console.log(toArray(1, [1, 2, 3]));
