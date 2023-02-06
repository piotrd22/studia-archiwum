// //  1. Zdefiniuj pola 'title' i 'author' i funkcjÄ print(), ktĂłra wypisuje author - title

const book0 = {
  title: "Dziady",
  author: "Adam Mickiewicz",
  print() {
    console.log(this.title, "-", this.author);
  },
};

// book0.print();

const book = {};
//properties
book.title = "Lalka";
book.author = "Boleslaw Prus";
book.print = function () {
  console.log(this.title, "-", this.author);
};

// book.print();

const book2 = Object.create({});
//properties
book2.title = "Moja Spowiedz";
book2.author = "Dawid Janczyk";
book2.print = function () {
  console.log(this.title, "-", this.author);
};

// book2.print();

function BookCreator(title, author) {
  const b = {};
  //properties
  b.title = title;
  b.author = author;
  b.print = function () {
    console.log(this.author, "-", this.title);
  };
  return b;
}

const book3 = BookCreator("Cie wiatru", "Carlos Ruiz Zafon");
const book4 = BookCreator("Ojciech Chrzestny", "Mario Puzo");

book3.print();
book4.print();

// 2. Przetestuj poniĹźszy kod i odpowiedz na pytania

function testThis() {
  console.log(this);
}

// testThis();

function testThis2() {
  "use strict";
  console.log(this);
}

// testThis2();

// 2.1. Czym jest this? Wskazuje na obiekt będący kontekstem wykonania.
// 2.2. Do czego odwoĹuje siÄ this w obu przypadkach -- w pierwszym odwoluje sie do globalnego obiektu, natomiast w drugim strict mode bedzie undefined bo nie jest ustawiona

const person = {
  name: "Oscar Wilde",
  print() {
    console.log(this.name);

    function a(c) {
      console.log(c);
    }
    a(this.name);
  },
};
// person.print();

// 2.3. Jakie wartoĹci przyjmuje this w powyĹźszych przypadkach i dlaczego? -- w pierwszym przyjmuje wartosc name -- Oscar Wilde, natomiast w 2 nie jest ustawiona, więc będzie to undefined
// 2.4. Zmodyfikuj powyĹźszy kod w ten sposĂłb, aby funkcja a wyĹwietlaĹa w konsoli 'a: Oscar Wilde'. Nie uĹźywaj arrow function.

const printName = function () {
  console.log(this.name);
};

const person1 = {
  name: "Aaron Towels",
  printName,
};

const person2 = {
  name: "Tom Clancy",
  printName,
};

// person2.printName();
// person1.printName();

// 2.5. Za pomocÄ funkcji printName wypisz 'name' obu autorĂłw. Nie zmieniaj implementacji funkcji printName!

const person3 = {
  name: "Arthur Conan Doyle",
  print() {
    const a = () => {
      console.log(this);
    };
    a();
  },
};
// person3.print();

// 2.6. Co wydrukuje w konsoli powyĹźszy kod? Jaki scope ma arrow function?

// 3. owrĂłÄmy do zadania 1.
// Dlaczego nasza funkcja BookCreator nie jest najlepszym rozwiÄzaniem do tworzenia obiektĂłw?

// Zmodyfikuj funkcjÄ BookCreator tak, aby inicjalizowaĹa pola author i title.
// FunkcjÄ print zadeklaruj jako wspĂłlnÄ dla wszystkich obiektĂłw tworzonych przez BookCreator.
// Dopisz do tworzonych obiektĂłw pole readers, ktĂłre bÄdzie zawieraĹo liczbÄ czytelnikĂłw.
// Zadeklaruj funkcjÄ addReader, ktĂłra inkrementuje pole readers. addReader powinna byÄ funkcjÄ wspĂłlnÄ, tak jak print.

// 4. Na stworzonym obiekcie wywoĹaj funkcjÄ hasOwnProperty('isBestseller').
// Napisz dlaczego nasz obiekt ma do niej dostÄp. (jeĹli wyskakuje bĹÄd - powrĂłÄ do poprzedniego zadania lub spytaj prowadzÄcego)

// 5. OdwoĹaj siÄ do zmiennej __proto__ w stworzonym obiekcie, co zawiera ta zmienna i do czego sĹuĹźy?
