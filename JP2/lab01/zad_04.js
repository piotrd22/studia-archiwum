let arr = [3, 10, 6];
let c = 0;
for (i = 0; i < arr.length; i++) {
  if (arr[i] > 0) {
    c++;
  } else {
    c--;
  }
}

for (i = 0; i < arr.length; i++) {
  if (arr[i] > arr[i + 1]) {
    let x = arr[i];
    arr[i] = arr[i + 1];
    arr[i + 1] = x;
  }
}
console.log(arr);

if (c === 3) {
  if (arr[0] + arr[1] >= arr[2]) {
    console.log("Mozna zbudowac trojkat");
  } else {
    console.log("Nie da sie zbudowac trojkata");
  }
} else {
  console.log("Nie wszytskie sa dodatnie");
}
