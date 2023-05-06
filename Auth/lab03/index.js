const express = require("express");
const app = express();
const { NodeAdapter } = require("ef-keycloak-connect");

const config = {
  realm: "demo-realm",
  "auth-server-url": "http://localhost:8080/",
  "ssl-required": "external",
  resource: "express-app",
  "verify-token-audience": true,
  credentials: {
    secret: "WstMqL6W7lz3oOSNEzxME3OLjPWWF9C6",
  },
  "use-resource-role-mappings": true,
  "confidential-port": 0,
  "policy-enforcer": {},
};

const keycloak = new NodeAdapter(config);
app.use(keycloak.middleware());

app.get("/hello", keycloak.protect(), (_, res) => {
  // normalnie przenosi sie to do gatewaya (podobne jak proxy np. nginx ale mozna tez dodac jakies tam autoryzacje itp.), zeby nie pisac tego w kazdym endpoincie
  res.send("Hello, World!");
});

app.listen(8081, () => {
  console.log("App listening on port 8081!");
});
