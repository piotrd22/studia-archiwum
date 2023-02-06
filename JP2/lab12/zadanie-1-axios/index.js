"use strict";

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
