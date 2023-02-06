"use strict";

function zwracaj(c) {
  const a = 10;
  function dodaj(x) {
    return x + a;
  }
  return dodaj(c);
}

console.log(zwracaj(10));
