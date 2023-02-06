const arr = [1, 5, 6, 5, 5, 1, 5];
let suma = 0;

for (i = 0; i < arr.length; i++) {
  suma = 0;
  for (j = 0; j < arr.length; j++) {
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
