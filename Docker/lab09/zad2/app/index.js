const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const morgan = require("morgan");

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use(morgan("dev"));

mongoose
  .connect("mongodb://db:27017", {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("DB is ready :)"))
  .catch((err) => console.error("Error with DB connection: ", err));

const userSchema = new mongoose.Schema({
  firstName: { type: String, required: true },
  lastName: { type: String, required: true },
  createdAt: { type: Date, default: Date.now },
});

const User = mongoose.model("User", userSchema);

app.get("/health", (_, res) => {
  try {
    res.status(200).send("API works correctly");
  } catch (err) {
    res.status(500).send("Server error: ", err);
  }
});

app.get("/users", async (_, res) => {
  try {
    const users = await User.find({});
    res.status(200).json(users);
  } catch (err) {
    res.status(500).send("Server error: ", err);
  }
});

app.post("/users", async (req, res) => {
  try {
    const newUser = new User(req.body);
    await newUser.save();
    res.status(201).send("User has been added");
  } catch (err) {
    res.status(500).send("Server error: ", err);
  }
});

app.delete("/users/:userId", async (req, res) => {
  try {
    const userId = req.params.userId;
    await User.findByIdAndDelete(userId);
    res.status(204).send();
  } catch (err) {
    res.status(500).send("Server error: ", err);
  }
});

app.listen(PORT, () => {
  console.log(`Server is available on ${PORT}`);
});
