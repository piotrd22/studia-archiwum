"use strict";

const array = [4, 2, "a", "b", 3, "aa", "ww", 2, "ab", -2];
for (let i = 0; i < array.length; i++) {
  if (typeof array[i] === "number") {
    let tmp = array[i];
    array.splice(i, 1);
    array.unshift(tmp);
  }
}

console.log(array);
