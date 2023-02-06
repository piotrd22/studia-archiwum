"use strict";

const grupuj = (funTab1, funTab2, cb) => {
  const zip = (t1, t2) => {
    return Array.from(Array(Math.max(t1.length, t2.length)), (_, i) => [
      t1[i],
      t2[i],
    ]);
  };
  Promise.all([...funTab1, ...funTab2]).then((resp) => {
    if (funTab1.length === funTab2.lenght) {
      const t1 = resp.slice(0, funTab1.lenght);
      const t2 = resp.slice(funTab1.lenght, funTab1.lenght + funTab2.lenght);
      cb(zip(t1, t2));
    } else {
      const t1 = resp.slice(0, funTab1.length);
      const t2 = resp.slice(funTab1.length, funTab1.length + funTab2.length);
      const result = zip(t1, t2);
      const res = result.map((x) => x.map((y) => (y === undefined ? 0 : y)));
      cb(res);
    }
  });
};

function wypisz(cos) {
  console.log(cos);
}

const a = [
  new Promise((resolve) => resolve(1)),
  new Promise((resolve) => resolve(8)),
  new Promise((resolve) => resolve(9)),
  new Promise((resolve) => resolve(12)),
];
const b = [
  new Promise((resolve) => resolve(4)),
  new Promise((resolve) => resolve(99)),
  new Promise((resolve) => resolve(9)),
];

grupuj(a, b, wypisz);

// const a = [1,2,3]
// const b = [4,5,7,8]
// const zip = (t1, t2) => {
//     return Array.from(Array(Math.max(t1.length, t2.length)),
//     (_, i) => [t1[i], t2[i]]);
// }
// console.log([...a, ...b].slice(a.length, a.length + b.length))
