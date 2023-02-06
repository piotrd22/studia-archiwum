const lp3 = require("./toplist");
const _ = require("lodash");

// 1

const res1 = _.filter(lp3, function (o) {
  return o.author === "Queen";
});

// console.log(res1);

// 2

const res2 = _.filter(lp3, function (x) {
  return x.author === "Pink Floyd" && x.change > 9;
});

// console.log(res2);

// 3

const res3 = (n) => {
  return _.chain(lp3).sortBy("change").dropRight(n).value();
};

// console.log(res3(0));

// 4

const res4 = _.filter(lp3, function (p) {
  return p.place === 1;
});

// console.log(res4);

// 5

const res5 = (arr) => {
  if (_.every(arr, Number)) {
    return arr.map((x) =>
      _.filter(lp3, function (p) {
        return p.place === x;
      })
    );
  } else {
    return "błąd";
  }
};

// console.log(res5([1,2,5]))

// 6

const res6 = (n, min, max) => {
  const arr = _.filter(lp3, function (p) {
    return p.place >= min && p.place <= max;
  });
  for (let i = 0; i < n; i++) {
    let r = _.random(min - 1, max - 1);
    console.log(arr[r]);
  }
};

// res6(7,1,4)

// 7

// 8

const res8 = () => {
  return _.filter(lp3, function (p) {
    return p.change < 0;
  });
};

// console.log(res8())

// 9

const res9 = () => {
  return _.reduce(
    lp3,
    function (acc, curr) {
      return {
        ...acc,
        [curr.song]: {
          ...curr,
        },
      };
    },
    {}
  );
};

// console.log(res9());

// 10

const res10 = () => {
  return _.reduce(
    lp3,
    function (acc, curr) {
      return {
        ...acc,
        [curr.author]: _.filter(lp3, function (p) {
          return p.author === curr.author;
        }),
      };
    },
    []
  );
};

// console.log(res10());

// 11

const res11 = () => {
  const c = res10();
  for (let i in c) {
    c[i] = _.size(c[i]);
  }
  return c;
};
// console.log(res11());

// 12

const res12 = () => {
  const change = _.reduce(
    lp3,
    function (acc, curr) {
      return [...acc, curr.change];
    },
    []
  );
  const max = _.max(change);
  const min = _.min(change);
  return _.filter(lp3, function (p) {
    return p.change === max || p.change === min;
  });
};

// console.log(res12());

// ZADANIA NR 3

// 6

const res6v2 = (n, min, max) => {
  const arr = lp3.filter((p) => p.place >= min && p.place <= max);
  for (let i = 0; i < n; i++) {
    let d = Math.random(min - 1, max - 1);
    let r = Math.round(d);
    console.log(arr[r]);
  }
};

// 7

// 10

const res10v2 = () => {
  return lp3.reduce((acc, curr) => {
    return {
      ...acc,
      [curr.author]: lp3.filter((p) => p.author === curr.author),
    };
  }, []);
};

// console.log(res10v2());
