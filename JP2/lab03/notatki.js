// Funkcje, dwa rodzaje =>, itp, operator spread ... tzn. wyciagaja kazdy element z tab array i wstawiaja go do array2
//const array = [1,2,3]
// const array2 = [...array] (te funkcje beda sie sobie rownac)

const object = {
  name: "Monika",
  surname: "Kowalska",
};

const object2 = {
  ...object,
  age: 31,
};

console.log(object2);

function func(param1, ...params) {}

func("Test", 3, 123, 144, 124); //one wszystkie zostana wrzucone tutaj do funkcji przez spread

if (object2.age > 22) {
  console.log("Tak");
} else {
  ocnsole.log("Nie");
}

// inaczej

object2.age > 22 ? console.log("Tak") : console.log("Nie");
