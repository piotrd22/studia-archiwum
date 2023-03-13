const express = require("express");

const app = express();

app.get("/", (_, res) => {
  return res.status(200).json("Hello World");
});

app.listen(3000, () => console.log('Server is up and running'));
