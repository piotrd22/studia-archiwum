// 'use strict';

// ZAD 1

// const promise = new Promise((resolve, reject) => { // reesolve succ a reject err
//     setTimeout(() => {
//         resolve("UDAŁO SIE")
//     }, 5000)})

// promise.then((response) => {
//     console.log(response);
// }).catch(err => {
//     console.log('err', err)
// })

// ZAD 2,3

// const promise = new Promise((resolve, reject) => { // reesolve succ a reject err
//     setTimeout(() => {
//         reject("PORAŻKA")
//     }, 5000)})

// promise.then((response) => {
//     console.log(response);
// }).catch(err => {
//     console.log('err', err)
// })

// ZAD 4

const multiplyAsync = (x, y) => {
  return new Promise((resolve, reject) => {
    if (typeof x === "number" && typeof y === "number") {
      resolve(x * y);
    } else {
      reject("PORAŻKA");
    }
  })
    .then((resp) => console.log(resp))
    .catch((err) => console.log("err", err));
};

// multiplyAsync(3,"a")

// AXIOS

const axios = require("axios");

// axios.get("https://jsonplaceholder.typicode.com/posts")
//     .then(resp => {
//         // console.log(resp.data);
//         console.log(resp);
//     })
//     .then(resp => {
//         console.log(resp.data);
//         console.log(resp.headers);
//     })
//     .catch(err => {
//         console.log('error', err)
//     })

// axios.get("https://jsonplaceholder.typicode.com/posts")
//     .then(resp => {
//         console.log(resp.data);
//         console.log(resp.headers);
//     })

const func = (user) => {
  axios
    .post("https://jsonplaceholder.typicode.com/todos", user)
    .then((response) => console.log("dodano", response.data.id))
    // .then(json => console.log("dodano", json.id))
    .catch((err) => console.log("error", err));
};

func({
  idUser: 123,
  title: "tytul",
  completed: true,
});
