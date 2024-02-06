import time
import random

print("SERWER DZIAŁA")

BUFFER_FILE = "buffer.txt"

responses = [
    "odpowiedź1",
    "odpowiedź2",
    "odpowiedź3",
    "odpowiedź4",
    "odpowiedź5",
    "odpowiedź6",
    "odpowiedź7",
]

while True:
    filePath = ""
    messageFromClient = ""
    with open(BUFFER_FILE, "r") as file:
        filePath = file.readline()[:-1]
        messageFromClient = file.readline()

    if len(filePath) > 0:
        with open(filePath, "w") as file:
            file.writelines(
                [f"Odpowiedź serwera: {random.choice(responses)}\n",
                 f"Wiadomość klienta: {messageFromClient}"]
            )
            print(f"{time.time()} {messageFromClient}")

        open(BUFFER_FILE, "w").close()

    time.sleep(0.2)
