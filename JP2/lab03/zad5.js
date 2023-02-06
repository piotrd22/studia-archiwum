"use strict";

function zad5(a, b, c) {
  if (a != b && a != c && b != c) {
    if ((typeof a == typeof b) & (typeof a == typeof c)) {
      return true;
    }
  } else {
    return false;
  }
}

console.log(zad5(1, 1, 3));
