"use strict";

const person = {
  person1: {
    name: "Agata",
    age: 21,
  },
  person2: {
    name: "Tomasz",
    age: 21,
  },
  person3: {
    name: "Alicja",
    age: 31,
  },
  person4: {
    name: "Jan",
    age: 20,
  },
};

let sumawieku = 0;
let size = 0;
for (let nazwa in person) {
  sumawieku += person[nazwa].age;
  size++;
}

const sredniawieku = sumawieku / size;

const ponizej = [];
const powyzej = [];

for (let osoba in person) {
  if (person[osoba].age > sredniawieku) {
    powyzej.push(person[osoba].name);
  } else {
    if (person[osoba].age < sredniawieku) {
      ponizej.push(person[osoba].name);
    }
  }
}

console.log(ponizej);
console.log(powyzej);
