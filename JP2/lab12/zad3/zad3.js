// Konstruktor klasy `Vector2` posiadajÄcy:
// - pola x i y
// - funkcje
//     - diff(vector) - odejmuje aktualny wektor od wektora podanego w argumencie
//     - multiplyBy(number) - mnoĹźy x i y wektora razy liczbÄ podanÄ w argumencie
//     - toString()  - zwraca tekst w formacie: "x: <wartoĹÄ> y: <wartoĹÄ>

// Konstruktor klasy `Ship` o nastÄpujÄcych:
// - polach:
//     - faction
//     - position - typu Vector2
//     - strength
//     - health
// - funkcjach:
//     - getDistance(enemyShip) - jeĹli to moĹźliwe, oblicza dystans dzielÄcy dwa statki (rĂłĹźnicÄ wektorĂłw). W przeciwnym wypadku wypisuje komunikat bĹÄdu
//     - checkHealth() - sprawdza, czy zdrowie statku spadĹo poniĹźej 0. JeĹli tak, wypisuje w konsoli odpowiedni komunikat
//     - getDamage(amount) - obniĹźa liczbÄ punktĂłw zdrowia o podanÄ liczbÄ i sprawdza, czy statek zostaĹ zniszczony
//     - makeDamage(enemyShip) - zadaje obraĹźenia statkowi podanemu w argumencie (o wartoĹÄ zmiennej strength)
// KlasÄ `RebelShip` dziedziczÄcÄ po `Ship`:
// - niech konstruktor przyjmuje parametry: position, strength, health
// - wartoĹÄ faction powinna byÄ na staĹe ustawiona jako "Rebel Alliance"
// - zdefiniuj funkcjÄ dla klasy RebelShip
//     - hyperspeed() - ustawia wartoĹÄ position na undefined
// KlasÄ `DeathStar` dziedziczÄcÄ po Ship
// - niech konstruktor przyjmuje parametr: position
// - niech faction bÄdzie ustawiony na staĹÄ na "Empire"
// - niech klasa zawiera nastÄpujÄce pola: deathRayAvailable
// - zawierajÄcÄ funkcje:
//     - makeDamage(enemyShip) - jeĹli deathRayAvailable jest ustawione na true, to wywoĹuje funkcje odziedziczonÄ po Ship, a nastÄpnie ustawia deathRay na niedostÄpny na ustalonÄ liczbÄ sekund. JeĹli deathRayAvailable jest rĂłwne false, drukuje komunikat.

// W razie potrzeby moĹźna zadeklarowaÄ zmienne i funkcje pomocnicze w klasie DeathStar.

// PrzykĹadowy output:

// utworzonyVector.toString();
// // x: 3 y: 4
// utworzonyVector.add(drugiVector);
// utworzonyVector.toString();
// // x: 5 y: 7

// gwiazdaSmierci.makeDamage(statekRebeli);
// // Statek Rebelii otrzymaĹ obraĹźenia rĂłwne: 10
// // Statek Rebelii zostaĹ zniszczony

// gwiazdaSmierci.makeDamage(statekRebeli).catch(error => console.log(error));
// // PromieĹ gwiazdy Ĺmierci jest niedostepny

// // Powinna byÄ rĂłwnieĹź moĹźliwoĹÄ wywoĹania gwiazdaSmierci.makeDamage() dwa razy

"use strict";

class Vector2 {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
  diff(vector) {
    this.x -= vector.x;
    this.y -= vector.y;
  }
  multilyBy(number) {
    this.x *= number;
    this.y *= number;
  }
  toString() {
    return `x: ${this.x}, y: ${this.y}`;
  }
}
class Ship {
  constructor(faction, position, strength, health) {
    this.faction = faction;
    this.position = position;
    this.strength = strength;
    this.health = health;
  }
  getDistance(enemyShip) {
    return new Promise((resolve, reject) => {
      if (
        enemyShip.position.x < 0 ||
        enemyShip.position.y < 0 ||
        !Number.isInteger(enemyShip.position.x) ||
        !Number.isInteger(enemyShip.position.y)
      ) {
        reject("Blad");
      } else {
        resolve([
          this.position.x - enemyShip.position.x,
          this.position.y - enemyShip.position.y,
        ]);
      }
    });
  }
  checkHealth() {
    return new Promise((resolve, reject) => {
      if (this.health > 0) {
        resolve(this.health);
      } else {
        reject("STATEK NIE MA ZYCIA");
      }
    });
  }
  getDamage(amount) {
    return new Promise((resolve, reject) => {
      this.health -= amount;
      if (this.health <= 0) {
        resolve(this.health);
      } else {
        reject("STATEK ZOSTAL ZNISZONY");
      }
    });
  }
  makeDamage(enemyShip) {
    return new Promise((resolve, reject) => {
      enemyShip.health -= this.strength;
      if (enemyShip.health <= 0) {
        resolve(enemyShip.health);
      } else {
        reject("Statek wroga zostal zniszczony!");
      }
    });
  }
}
class RebelShip extends Ship {
  constructor(position, strength, health) {
    super("Rebel Alliance", position, strength, health);
  }
  hyperspeed() {
    this.position = undefined;
  }
}
class DeathStar extends Ship {
  constructor(position) {
    super("Empire", position);
    this.deathRayAvailable = true;
  }
  makeDamage(enemyShip) {
    return new Promise(() => {
      if (this.deathRayAvailable == false) throw "deathrayAvailable === false";
      super.makeDamage(enemyShip);
    });
  }
}
let vec = new Vector2(5, 4);
let vec2 = new Vector2(1, 2);
console.log(vec.toString());
let ship1 = new Ship(1, vec, 2, 5);
let ship2 = new Ship(1, vec2, 2, 5);
let ship3 = new DeathStar(vec);
let ship4 = new RebelShip(vec, 2, 5);
ship1
  .getDistance(ship2)
  .then((value) => console.log(value))
  .catch((error) => console.log(error));
ship1
  .checkHealth()
  .then((value) => console.log(value))
  .catch((error) => console.log(error));
ship1
  .getDamage(6)
  .then((value) => console.log(value))
  .catch((error) => console.log(error));
// ship3.makeDamage(ship2).then(value=>console.log(value)).catch(error=>console.log(error));
