"use strict";

const listaZakupow = [
  {
    produkt: "chleb",
    typ: "pieczywo",
    ilosc: 2,
    cena: 3.6,
    jednostka: "sztuk",
  },
  {
    produkt: "jabłka",
    typ: "owoce",
    ilosc: 6,
    cena: 2.5,
    jednostka: "kg",
  },
  {
    produkt: "mleko",
    typ: "nabiał",
    ilosc: 3,
    cena: 2.9,
    jednostka: "litry",
  },
  {
    produkt: "kawa",
    typ: "napoje",
    ilosc: 1,
    cena: 24,
    jednostka: "sztuk",
  },
  {
    produkt: "kefir",
    typ: "nabiał",
    ilosc: 2,
    cena: 2.4,
    jednostka: "sztuk",
  },
  {
    produkt: "woda",
    typ: "napoje",
    ilosc: 6,
    cena: 1.9,
    jednostka: "sztuk",
  },
  {
    produkt: "marchewka",
    typ: "warzywa",
    ilosc: 2,
    cena: 4,
    jednostka: "kg",
  },
  {
    produkt: "banan",
    typ: "owoce",
    ilosc: 1,
    cena: 4.6,
    jednostka: "kg",
  },
  {
    produkt: "herbata",
    typ: "napoje",
    ilosc: 2,
    cena: 8,
    jednostka: "sztuk",
  },
  {
    produkt: "ziemniaki",
    typ: "warzywa",
    ilosc: 5,
    cena: 3.5,
    jednostka: "kg",
  },
  {
    produkt: "jogurt",
    typ: "nabiał",
    ilosc: 8,
    cena: 1.4,
    jednostka: "sztuk",
  },
];

// 1

const res = listaZakupow.reduce((acc, curr) => {
  return {
    ...acc,
    [curr.produkt]: {
      ...curr,
    },
  };
}, {});

// console.log(res);

// 2

const res2 = listaZakupow.reduce((acc, curr) => {
  return acc + curr.cena;
}, 0);

// console.log(res2);

// 3

const res3 = listaZakupow.reduce((acc, curr) => {
  if (curr.jednostka === "kg") {
    return [...acc, curr.produkt].sort();
  } else {
    return acc;
  }
}, []);

// console.log(res3);

// 4

function typ(x) {
  return listaZakupow.reduce((acc, curr) => {
    if (curr.typ === x && curr.cena * curr.ilosc < 10) {
      return [...acc, curr].sort(function (a, b) {
        return a.cena - b.cena;
      });
    } else {
      return acc;
    }
  }, []);
}

// 5

const res5 = listaZakupow.reduce((acc, curr) => {
  if (curr.jednostka === "sztuk") {
    return [...acc, curr.produkt];
  } else {
    return acc;
  }
}, []);

// 7

const res7 = () => {
  listaZakupow.sort((a, b) => (a.cena > b.cena ? 1 : -1));
  return listaZakupow.reduce((acc, curr) => {
    if (curr.cena * curr.ilosc >= listaZakupow[listaZakupow.length - 1].cena)
      return acc;
    return { ...acc, [curr.produkt]: { cena: curr.cena * curr.ilosc } };
  }, {});
};
// console.log(res7());

// 6

const res6 = () => {
  let string = "";

  listaZakupow.sort((a, b) => (a.typ > b.typ ? 1 : -1));

  const printThis = listaZakupow.reduce((acc, curr) => {
    if (acc[curr.typ] == null) acc[curr.typ] = [];
    acc[curr.typ].push(`${curr.produkt} - sztuk: ${curr.ilosc}`);
    acc[curr.typ].sort((a, b) => (a.charAt(0) > b.charAt(0) ? 1 : -1));
    return acc;
  }, {});

  for (const property in printThis) {
    string += `${property}: \n`;
    for (let i = 1; i < printThis[property].length + 1; i++) {
      string += `${i}. ${printThis[property][i - 1]} \n`;
    }
  }
  return string;
};

console.log(res6());
