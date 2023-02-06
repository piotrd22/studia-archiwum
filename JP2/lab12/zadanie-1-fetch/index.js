"use strict";

// npm install node-fetch@2

const fetch = require("node-fetch"); // importujemy node fetch

// 1 sposob

// fetch('https://jsonplaceholder.typicode.com/comments') //then jezeli request jest pozytywny a catch wyrzuca error jak zle
//     .then(response => {
//         console.log(response);
//         // console.log(response.json())
//         response.json().then(data => {
//             console.log(data);
//         })
//     })
//     .catch(err => {
//         console.log('error', err)
//     })

// console.log('Test');

// 2 sposob

// fetch('https://jsonplaceholder.typicode.com/comments')
//     .then(response => {
//         return response.json();
//     })
//     .then(data => {
//         console.log(data);
//     })

// wlasne promises

// const promise = new Promise((resolve, reject) => { // reesolve succ a reject err
//     const result = 2 + 2;
//     if (result > 2) {
//         resolve(result);
//     } else {
//         reject(result);
//     }
// })

// promise.then((response) => {
//     console.log(response);
// }).catch(err => {
//     console.log('err', err)
// })

// ZAD 1

fetch("https://jsonplaceholder.typicode.com/posts")
  .then((response) => {
    return response;
  })
  .catch((err) => {
    console.log("error", err);
  });

fetch("https://jsonplaceholder.typicode.com/posts")
  .then((response) => {
    console.log(response.headers);
    console.log(response.body);
  })
  .catch((err) => {
    console.log("error", err);
  });

// ZAD 2

const func = (user) => {
  fetch("https://jsonplaceholder.typicode.com/posts", {
    method: "POST",
    body: JSON.stringify(user),
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => response.json())
    .then((json) => console.log("dodano", json.id))
    .catch((err) => console.log("error", err));
};

func({
  idUser: 123,
  title: "tytul",
  completed: true,
});
