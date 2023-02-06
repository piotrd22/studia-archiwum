"use strict";

function zwracan(func) {
  console.log(func.name);
}

function sumuj() {
  console.log("hahah");
}

zwracan(sumuj);
