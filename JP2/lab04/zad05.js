"use strict";

function silnia(a) {
  function lepszasilnia(a, akumulator = 1) {
    if (a === 1) {
      return akumulator;
    } else {
      return lepszasilnia(a - 1, akumulator + a);
    }
  }
  return lepszasilnia(a);
}

console.log(silnia(7));
