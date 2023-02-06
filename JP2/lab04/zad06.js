"use strict";

//euklidesa
function nwd(a, b) {
  if (a != b) {
    if (a > b) {
      return nwd(a - b, b);
    } else {
      return nwd(a, b - a);
    }
  }
  return a;
}

console.log(nwd(18, 24));
