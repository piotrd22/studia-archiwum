"use strict";

const array = [2, 3, 4, 5, 1, 2];

array.reduce((prev, curr) => {
  // console.log(prev, curr)
  return curr;
});

//kopiowanie tablicy

const haha = array.reduce((prev, curr) => {
  // console.log(prev, curr);
  return [...prev, curr];
}, []); // co to robi ? jako pierwszy element daje [] i jest to kolejny argument reduce

console.log(haha);

// sumowaine

const res = array.reduce((prev, curr) => {
  return prev + curr;
});
console.log(res);

//kolejny arg currentIndex, current array

// const res2 = array.reduce((prev , curr, currI, currA) => {
// });

// //reduce nie modyfikuje tablicy

// console.log(res2);

const objects = [
  { firstname: "Tomek", age: 23 },
  { firstname: "Ala", age: 20 },
];

const res3 = objects.reduce((prev, curr) => {
  return {
    ...prev,
    [curr.age]: curr.firstname,
  };
}, {});

console.log(res3);
