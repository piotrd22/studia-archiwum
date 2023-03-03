const express = require("express");
const moment = require("moment");

const PORT = 8080;
const HOST = "0.0.0.0";

const app = express();

app.get("/", (_, res) => {
  res.send(moment().format("MMMM Do YYYY, h:mm:ss a"));
});

app.listen(PORT, HOST, () => {
  console.log(`Running on http://${HOST}:${PORT}`);
});
