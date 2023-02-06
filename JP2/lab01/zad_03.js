let array = [1, 2, 3, "Arek", 122, "TTT"];

for (i = 0; i < array.length; i++) {
  console.log(array[i]);
}

let arr = [4, 5, 1, -4, -100, 35, 2, 1];

let min = arr[0];
let max = arr[0];

for (i = 0; i < arr.length; i++) {
  if (arr[i] > max) {
    max = arr[i];
  }
  if (arr[i] < min) {
    min = arr[i];
  }
}
console.log("Min:", min, "Max:", max);

let t = [1, 2, 3, 4, 5, 6, 7, 8, 9];
let a = [];
for (i = t.length - 1; i >= 0; i--) {
  a.push(t[i]);
}
t = a;
console.log(t);
