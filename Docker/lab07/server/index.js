const express = require('express');
const mongoose = require('mongoose');

const app = express();

mongoose.connect('mongodb://db:27017/test', { useNewUrlParser: true, useUnifiedTopology: true });

const userSchema = new mongoose.Schema({
  name: String,
  last_name: String
});

const User = mongoose.model('User', userSchema);

app.get('/users', async (_, res) => {
  const users = await User.find({});
  res.json(users).status(200);
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});