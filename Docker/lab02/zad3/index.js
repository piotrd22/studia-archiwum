const express = require("express");
const mongoose = require("mongoose");

const app = express();

const PORT = 8080;
const HOST = "0.0.0.0";

mongoose.connect("mongodb://mongo:27017/", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const mySchema = new mongoose.Schema({
  name: String,
  age: Number,
});

const myModel = mongoose.model("mycollection", mySchema);

app.get("/", async (_, res) => {
  try {
    const data = await myModel.find();
    res.json(data);
  } catch (err) {
    console.error(err);
    res.status(500).send("Server Error");
  }
});

app.listen(PORT, HOST, () => {
  console.log(`Running on http://${HOST}:${PORT}`);
});
