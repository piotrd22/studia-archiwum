import time

OUTPUT_FILE = "output.txt"
INPUT_FILE = "input.txt"

a = int(input("Number: "))

with open(INPUT_FILE, "w") as file:
    file.write(str(a))

# Mozna uzyc sleep lub petli, jednak w rozwiazaniu ze sleepem serwer moglby nie zdazyc
# time.sleep(1)

is_running = True
while is_running:
    with open(OUTPUT_FILE, "r") as file:
        result = file.readline()
        if (len(result) > 0):
            print(f"Result: {result}")
            open(OUTPUT_FILE, 'w').close()
            is_running = False
