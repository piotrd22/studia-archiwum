"use strict";

(function (string) {
  const napis = string.split(" ");
  let odp = napis[0];
  for (let i = 0; i < napis.length; i++) {
    if (napis[i].length > odp.length) {
      odp = napis[i];
    }
  }
  console.log(odp);
})("ala ma kota");
