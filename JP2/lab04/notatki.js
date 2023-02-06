//funkcje anonimowe -- mam zdjecia bo nie zdazylem zanotowac,run and debug,
"use strict";

function func1() {
  let wynik = 10;
  wynik += 4;
  console.log(wynik);
}
func1();

// ta funkcja wywoluje sie sama w sobie
(function () {
  var zmienna = 6;
  function nazwa() {
    return 5;
  }
  console.log("Funkcja anonimowa");
})();

// przekazywanie f jako parametr

function dodaj() {
  console.log("f . dodajaca");
}
function f(func) {
  func();
}

f(dodaj);

const arrowFunction = (func2) => {
  func2();
};

arrowFunction(dodaj);

arrowFunction(() => {
  console.log("f stworzona w parametrze");
});

// tak samo mozna bez => ale ze slowem kluczowym function

function zwracaj() {
  return () => {
    console.log("Zwrocona funkcja");
  };
}

zwracaj()();
