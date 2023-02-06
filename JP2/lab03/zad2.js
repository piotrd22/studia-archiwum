"use strict";

function isPositiveEven(number) {
  number > 0 ? console.log("Wieksza od zera") : console.log("Mniejsza od 0");
  number % 2 === 0 ? console.log("Parzysta") : console.log("Nieparyztsa");
}
isPositiveEven(-2);
