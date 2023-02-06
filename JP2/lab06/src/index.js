const status = "completed";

const runFunc = () => 5;
const runFunc2 = () => 3;

switch (status) {
  case "Completed":
    runFunc();
    break;

  case "Running":
    runFunc2();
    break;
}
