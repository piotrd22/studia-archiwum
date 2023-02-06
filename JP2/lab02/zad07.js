// 'use strict';

// class person{
//     constructor (name, age) {
//         this.name = name;
//         this.age = age;
//     }
// }

// const person1 = new person("Agata", 21);
// const person2 = new person("Tomasz", 25);
// const person3 = new person("Alicja", 31);
// const person4 = new person("Jan", 20);

// console.log(person3);

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

console.log(sumawieku);

let sredniawieku = sumawieku / size;

console.log(sredniawieku);
