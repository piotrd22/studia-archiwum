"use strict";

function howmany(arr) {
  for (let i = 0; i < arr.length; i++) {
    let suma = 0;
    for (let j = 0; j < arr.length; j++) {
      if (arr[j] === arr[i]) {
        suma++;
      }
    }
    if (suma === 1) {
      console.log(arr[i], "występuje", suma, "raz");
    } else {
      console.log(arr[i], "występuje", suma, "razy");
    }
  }
}

howmany(["js", "js", "react", "angular", 1]);
