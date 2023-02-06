// Brawo, na poprzednich laboratoriach dowiedziaĹeĹ/aĹ siÄ jak dziaĹa sĹowo kluczowe new :)
// 1. UproĹÄ funkcjÄ BookCreator tak, aby zawieraĹa tylko nadawanie wartoĹci polom. (uĹźyj operatora this)
// Dodaj wywoĹanie sĹowa kluczowego new, przy wywoĹaniu BookCreator().

// BookCreator jest konstruktorem, a je zawsze (ZAWSZE) deklarujemy zaczynajÄc nazwÄ od wielkiej litery

// 1.1. UĹźyj zmiennej prototype, aby dodaÄ funkcje print() i addReader() do tworzonych obiektĂłw.

const BookCreator = function (name, author) {
  this.name = name;
  this.author = author;
};

const book1 = new BookCreator("Dziady", "Adam Mickiewicz");
// console.log(book1);

BookCreator.prototype.print = function () {
  return this.name + " " + this.author;
};

// console.log(book1.print())

BookCreator.prototype.addReader = function (reader) {
  this.reader = reader;
};

book1.addReader("Marek");
// console.log(book1)

// 2. Tworzymy alternatywnÄ wersjÄ powyĹźszego kodu. UĹźyj sĹĂłw kluczwych class i constructor, aby osiÄgnÄÄ powyĹźszy efekt.

class BookCreator2 {
  constructor(name, author) {
    this.name = name;
    this.author = author;
  }
  print() {
    return this.name + " " + this.author;
  }
  addReader(reader) {
    this.reader = reader;
  }
}

const book2 = new BookCreator2("Boleslaw Prus", "Lalka");

// 3. Znasz juĹź wiele sposĂłb na stworzenie obiektu. Dlaczego wiÄc nie uĹźyÄ arrow function?
// UzupeĹnij poniĹźszy kod o inicjalizacjÄ pola name i age. Dodaj wewnÄrz funkcjÄ addAge, ktĂłra inkrementuje wiek.

const Person = (name, age) => ({
  // inicjalizacja
  name,
  age,
  addAge: function () {
    this.age++;
  },
});

// console.log(new Person("Marek", 12))

// Przetestuj dziaĹanie tak stworzonego obiektu, korzystajÄc z wiedzy, ktĂłrÄ juĹź masz. Jakie sÄ rĂłznice pomiÄdzy stworzeniem obiektu za pomocÄ poprzednich metod?
// (przetestuj prototype, new itd.)
