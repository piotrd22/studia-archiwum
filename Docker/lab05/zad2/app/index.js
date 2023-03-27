const express = require('express');

const app = express();

const PORT = process.env.PORT || 5000

app.get('/', (_, res) => {
    res.json('Hello World!');
});

app.listen(PORT, () => {
    console.log(`App listening on ${PORT}!`);
});