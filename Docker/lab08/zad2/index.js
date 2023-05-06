const express = require("express");
const Redis = require("ioredis");

const app = express();
app.use(express.json());

const client = new Redis({
  host: "db",
  port: 6379,
});
client.on("error", (err) => console.error(err));

app.get("/", (_, res) => {
  res.send("Cool");
});

app.get("/user", async (_, res) => {
  const users = await client.lrange("users", 0, -1);
  res.send(users);
});

app.post("/user", async (req, res) => {
  await client.rpush("users", req.body.user);
  res.send("User added");
});

const PORT = 3000;

app.listen(PORT, () => {
  console.log(`Server listening on localhost:${PORT}`);
});
