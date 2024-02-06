import time
import os

message = input("Wiadomość: ")

LOCKFILE = "lockfile"
BUFFER_FILE = "buffer.txt"

FILE_PATH = f"{os.getcwd()}/{time.time()}.txt"

open(FILE_PATH, "w").close()

while True:
    try:
        lock = os.open(LOCKFILE, os.O_CREAT | os.O_EXCL | os.O_RDWR)
        break
    except:
        print("Serwer zajęty, proszę czekać")
        time.sleep(1)

with open(BUFFER_FILE, "w") as file:
    file.writelines(
        [f"{FILE_PATH}\n",
         f"{message}"]
    )

print("\nCZEKAM\n")
time.sleep(3)

with open(FILE_PATH, "r") as file:
    print(file.read())

if os.path.exists(FILE_PATH):
    os.remove(FILE_PATH)

if os.path.exists(LOCKFILE):
    os.close(lock)
    os.unlink(LOCKFILE)
