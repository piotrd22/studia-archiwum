print("Server has been started!")

OUTPUT_FILE = "output.txt"
INPUT_FILE = "input.txt"

while True:
    with open(INPUT_FILE, "r") as file:
        k = file.readline().strip()

    if len(k) > 0 and k.isdigit():
        k = int(k)
        with open(OUTPUT_FILE, "w") as file:
            result = k + 1
            file.write(f"{result}")

        open(INPUT_FILE, 'w').close()
