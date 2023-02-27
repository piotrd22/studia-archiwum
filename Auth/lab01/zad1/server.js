const express = require("express");
const helmet = require("helmet");
const cors = require("cors");
const morgan = require("morgan");
const bodyParser = require("body-parser");
const basicAuth = require('express-basic-auth')

const app = express();

const corsOptions = {
  origin: "*",
  credentials: true,
  optionSuccessStatus: 200,
};

app.use(basicAuth({
    users: { 'admin': 'supersecret' },
    challenge: true,
    unauthorizedResponse: getUnauthorizedResponse
}))

function getUnauthorizedResponse(_) {
    return 'Credentials rejected'
}

app.use(cors(corsOptions));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(morgan("common"));
app.use(helmet());

// Routes
app.get("/", (_, res) => {
    res.status(200).json("Hello world!")
})

app.listen(3000, () => {
  console.log("Backend is alive!");
});