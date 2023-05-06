const express = require("express");
const Redis = require("ioredis");
const { Client } = require("pg");

const app = express();
app.use(express.json());

const clientPG = new Client({
  host: "db",
  port: 5432,
  database: "db",
  user: "postgres",
  password: "password",
});
clientPG.connect();

clientPG.query(
  "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username VARCHAR(50) NOT NULL);"
);

const client = new Redis({
  host: "redis",
  port: 6379,
});
client.on("error", (err) => console.error(err));

app.get("/", (_, res) => {
  res.send("Cool");
});

app.get("/messages", async (_, res) => {
  const messages = await client.lrange("messages", 0, -1);
  res.send(messages);
});

app.post("/messages", async (req, res) => {
  await client.rpush("messages", req.body.message);
  res.send("Message added");
});

app.get("/users", async (_, res) => {
  const users = await clientPG.query("SELECT * FROM users");
  res.send(users.rows);
});

app.post("/users", async (req, res) => {
  await clientPG.query("INSERT INTO users (username) VALUES ($1)", [
    req.body.user,
  ]);
  res.send("User added");
});

const PORT = 3000;

app.listen(PORT, () => {
  console.log(`Server listening on localhost:${PORT}`);
});
