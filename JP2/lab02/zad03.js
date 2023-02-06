"use strict";

const testArray = [
  1,
  2,
  null,
  [4, undefined, 11, 10],
  6,
  [7, null, 0],
  null,
  9,
];

console.log(typeof testArray);

for (let i = 0; i < testArray.length; i++) {
  if (typeof testArray[i] === "object" && testArray[i] != null) {
    let tmp = testArray[i];
    testArray.splice(i, 1);
    for (let j = 0; j < tmp.length; j++) {
      testArray.splice(i + j, 0, tmp[j]);
    }
  }
}

console.log(testArray);
