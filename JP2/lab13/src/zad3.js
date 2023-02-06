"use strict";

const axios = require("axios");

const randomNumbers = (x) => {
  const a = [];
  for (let i = 0; i < x; i++) {
    a.push(Math.round(Math.random() * 100));
  }
  return a;
};

const randoms = randomNumbers(5);

randoms.map(async (x) => {
  const res = await axios
    .get(`https://jsonplaceholder.typicode.com/posts/${x}`)
    .then((resp) => resp.data);
  const comments = await axios
    .get(`https://jsonplaceholder.typicode.com/posts/${x}/comments`)
    .then((resp) => resp.data);
  const res2 = comments.reduce((prev, curr) => {
    return [...prev, { name: curr.name, email: curr.email, body: curr.body }];
  }, []);
  console.log({
    entry: {
      title: res.title,
      body: res.body,
    },
    comments: res2,
  });
});
