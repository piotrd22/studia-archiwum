"use strict";

const arr = [
  { id: "abc", name: "Ala" },
  { id: "def", name: "Tomek" },
  { id: "ghi", name: "Jan" },
];

const res = arr.reduce((prev, curr) => {
  return {
    ...prev,
    [curr.id]: {
      id: curr.id,
      name: curr.name,
    },
  };
}, {});

console.log(res);
