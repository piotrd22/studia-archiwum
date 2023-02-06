"use strict";

const wishlist = [
  {
    name: "Czajnik",
    netto: 100,
  },
  {
    name: "Lodówka",
    netto: 1300,
  },
  {
    name: "Mikrofalówka",
    netto: 340,
  },
  {
    name: "Mikser",
    netto: 120,
  },
  {
    name: "Piekarnik",
    netto: 2100,
  },
];

//1

const suma = wishlist.reduce((prev, curr) => {
  return prev + curr.netto * 1.23;
}, 0);
// console.log(suma);

// 2

const ceny = wishlist.reduce((prev, curr) => {
  return [...prev, curr.netto];
}, []);
// console.log(ceny)

//3

const tablica = wishlist.reduce((prev, curr) => {
  return [...prev, curr.name + " " + curr.netto];
}, []);
// console.log(tablica);

function cos(list) {
  return list.reduce((prev, curr) => {
    return [...prev, curr.name + " " + curr.netto];
  }, []);
}

// console.log(cos(wishlist))

//4

const what = wishlist.map((x) => `${x.name} : ${x.netto}`);

// console.log(what);

// 5

const tanie = wishlist.reduce((prev, curr) => {
  if (curr.netto < 500) {
    return [...prev, `${curr.name} : ${curr.netto}`];
  } else {
    return [...prev];
  }
}, []);

// console.log(tanie);

// 6

function szukam(szukane, list) {
  return list.reduce((prev, curr) => {
    if (szukane === curr.name) {
      return [...prev, curr.name];
    } else {
      return prev;
    }
  }, undefined);
}

console.log(szukam("Czajnik", wishlist));
