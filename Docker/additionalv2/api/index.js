const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const morgan = require("morgan");
const redis = require("redis");

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.use(morgan("dev"));

const redisClient = redis.createClient({ url: process.env.REDIS_URL });
redisClient.on("connect", () => console.log("Connected to Redis"));
redisClient.on("error", (error) =>
  console.error("Error connecting to Redis:", error)
);
redisClient.on("end", () => console.log("Disconnected from Redis"));

mongoose
  .connect(process.env.DATABASE_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("DB is ready :)"))
  .catch((err) => console.error("Error with DB connection: ", err));

const taskSchema = new mongoose.Schema({
  description: { type: String, required: true },
});

const Task = mongoose.model("Task", taskSchema);

app.get("/tasks", async (_, res) => {
  try {
    const tasks = await Task.find({});
    res.status(200).json(tasks);
  } catch (err) {
    res.status(500).json(err);
  }
});

app.post("/tasks", async (req, res) => {
  try {
    const newTask = new Task(req.body);
    await newTask.save();
    res.status(201).json(newTask);
  } catch (err) {
    res.status(500).json(err);
  }
});

app.put("/tasks/:id", async (req, res) => {
  try {
    const id = req.params.id;
    const task = await Task.findByIdAndUpdate(id, req.body, { new: true });
    redisClient.connect();
    const count = await redisClient.incr("updatedTasks");
    redisClient.disconnect();
    res.status(200).json({ task, updatedTasksCount: count });
  } catch (err) {
    res.status(500).json(err);
  }
});

app.delete("/tasks/:id", async (req, res) => {
  try {
    await Task.findByIdAndDelete(req.params.id);
    res.status(204).json("Task has been deleted");
  } catch (err) {
    res.status(500).json(err);
  }
});

app.listen(PORT, () => {
  console.log(`Server is available on ${PORT}`);
});
