"use strict";

const cat = {
  name: "Filemon",
  age: 6,
};

cat.description = `Kot ma na imię ${cat.name} i ma ${cat.age} lat.`;

cat.breed = "Brytjczyk";
cat.colour = "czarny";

cat.description = cat.description.concat(
  ` Jego rasa to ${cat.breed}, a kolor to ${cat.colour}`
);

console.log(cat.description);
