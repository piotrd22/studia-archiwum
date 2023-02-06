"use strict";

// Poniższe fragmenty kodu zostały zakomentowane w celu utrzymania porządku.
// Odkomentowuj je na bieżąco i zapisuj odpowiedzi w komentarzu.
// Ostatecznie przed wrzuceniem pliku na repozytorium zakomentowane powinny być tylko dodane odpowiedzi i fragmenty kodu powodujące ewentualne błędy.

// ========================= Zadanie 1 =========================
// Co zwróci funkcja poniższa funkcja w każdym z poniższych przypadków?
// Wyjaśnij, dlaczego w niektórych przypadkach wyniki różnią się.

// ========================== UWAGA =============================
// Zapis
// (impression) ? console.log('A') : console.log('B');
// Jest skróconą wersją:
// if (impression) {
//     console.log('A');
// } else {
//     console.log('B');
// }
// ==============================================================

function isEquals(val_1, val_2) {
  val_1 == val_2 ? console.log("A") : console.log("B");
  val_1 === val_2 ? console.log("C") : console.log("D");
}

// isEquals(2, '2');     // Tutaj w pierwszym będzie A, ponieważ 2 i '2' są sobie równe przy uzyciu operatora ==, który nie porównuje typów, ale dla === nie będą sobie równe bo mają inne typy
// isEquals(null, undefined); // Podobnie jak wyżej oba null oraz undefined znaczą "nic", więc == będą sobie równe, ale dla === nie, bo mają różne typy
// isEquals(undefined, NaN); // NaN znaczy NotANumber, więc tutaj w obu przypadkach zgadza się, że nie są sobie równe
// isEquals(['a', 'b', 'c'], ['b', 'c', 'd']); // Te listy nie są sobie równe bo maja inne elementy w obu przypadkach
// isEquals(0, ''); // analogicznie do pierwszej odpowiedzi
// isEquals('0', ''); //  nie są sobie równe bo str od 0 to cos innego niż 0int i nie jest to równe ""
// isEquals(+0, -0); // myślę, że 0 nie ma znaczenia znaku wieć oba są sobie równe
// isEquals(0, false); // te dwa przyklady sa nalaogiczne do '' i 0
// isEquals(0, 'false');
// isEquals([1, 2], '1,2');  // tak samo mają rózne typy, ale składniki te same, więc == true, ale === false

!!false;
!!true;
!!undefined;
!!null;

// ========================= Zadanie 2 =========================
// Jaki będzie efekt działania poniższego fragmentu kodu?
// Wyjaśnij wynik

const person = {
  firstName: "Jan",
  lastName: "Kowalski",
};

// console.log(person);
// person = {};
// console.log(person);

// Będzie błąd, ponieważ przypisujemy nowa wartość do stałej, gdyby był let nie byłoby problemu

// ========================= Zadanie 3 =========================
// Co zostanie wyświetlone na ekranie?
// Wyjaśnij wynik

// let number = 3;
// console.log(number); {
//     let number = 4;
//     console.log(number);
// }
// console.log(number);

// ponieważ jest to przypisane lokalnie, pomiędzy {} poleceinem let

// ========================= Zadanie 4 =========================
// Czym się różnią poniższe dwa fragmenty kodu?
// Jak działa operator '...'?

// const arr = [1, 2];
// const newArr1 = [arr, 3, 4];
// console.log(newArr1);
// const newArr2 = [...arr, 3, 4];
// console.log(newArr2);

// dołącza po kolei elementy do innego elementu, przez co nie będziemy miec dwuwymiarowej tablicy, a po prostu kopie.

// Co zostanie wyświetlone na ekranie?
// Wyjaśnij wynik

// const word = 'react';
// const arrWord = [...word];
// console.log(arrWord);

// na ekranie wyświetli si≥ę lista ['r','e','a','c','t'], ponieważ to polecenie dołącza do listy po kolei kolejne wartości elemetu (utaj stringa)

// ========================= Zadanie 5 =========================
// Zapoznaj się z kodem poniżej. Jaki będzie jego wynik i dlaczego?

// var hello = 'Hello world!';
// var result = hello / 2;

// result;

// Number.isNaN(result);
// Number.isNaN(hello);

// console.log(Number.isNaN(result));
// console.log(Number.isNaN(hello));

// odp do 1 true, 2 false, ponieważ 1 to nie jest liczba,a powinna byc (nie dzielimy stringów), natomiast hello world to false, bo nie jest to liczba, ale nie miała nią być
// ========================= Zadanie 6 =========================
// Zapoznaj się z przykładami poniżej. Jaka jest różnica między var a let/const?

// var car = 'BMW';

// function showCar() {
//     car = 'Audi';
//     model = 'A5';
//     console.log('Great car!');
// }

// showCar();

// car;
// model;

// -------

// if (true) {
//     var a = 2;
// }
// console.log(a);

// if (true) {
//     const b = 2;
// }
// console.log(b);

// // -------

// for (var i = 0; i < 10; i++) {
//     console.log(i);
// }
// console.log(i);

// for (let i = 0; i < 10; i++) {
//     console.log(i);
// }
// console.log(i);

// -------

// var test = "var1";
// var test = "var2";

// let test2 = "let1";
// let test2 = "let2";

//var jest globalny, const nie jest globalny, ale deklaruje stała, a let pozawala na zmianę wartości tej zmiennej w przyszłości

// ========================= Zadanie 7 =========================
// Do czego używany jest 'use strict' w pierwszej linijce skryptu?

// Używamy go, aby używać 'poprawnego js'a' i nie móc uzyważ 'dziwnych' rozwiązań, np. a=5 bez słowa klucowego let, czy const, aby kod był zrozumiały oraz czytelny
