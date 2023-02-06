"use strict";

const poKolei = (fun1, fun2, cb) => {
  fun1(3, (x) => {
    fun2(x, cb);
  });
};

function fun1(x, cb) {
  setTimeout(() => {
    x++;
    cb(x, cb);
  }, 2000);
}

function fun2(x, cb) {
  setTimeout(() => {
    x++;
    cb(x);
  }, 2000);
}

function printer(x) {
  console.log(x);
}

poKolei(fun1, fun2, printer);
