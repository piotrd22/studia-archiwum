// ZADANIE 4

function counter(x, y) {
  let n = 0;
  function helper() {
    const c = setInterval(() => {
      n++;
      console.log(n);
      if (n == x) {
        clearInterval(c);
      }
    }, y);
  }
  return helper;
}

const res = counter(5, 1000);
res();
