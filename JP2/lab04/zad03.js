"use strict";

function zwracaf(func) {
  func();
}

function sumuj() {
  console.log("hahah");
}

zwracaf(sumuj);
