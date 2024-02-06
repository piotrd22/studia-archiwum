import os
import errno
import time
import signal
import sys
import select

DB = {
    "1": "Słowacki",
    "2": "Mickiewicz",
    "3": "Norwid",
    "4": "Szymborska",
    "5": "Sienkiewicz"
}

FIFO_PATH = "server-fifo"

def sigusr1Handler(signum, frame):
    print("SERWER SIE ZAMYKA -- SIGUSR1")
    sys.exit(0)

def sighupHandler(signum, frame):
    print("SERWER DALEJ DZIAŁA -- SIGHUP")

def sigtermHandler(signum, frame):
    print("SERWER DALEJ DZIAŁA -- SIGTERM")

signal.signal(signal.SIGUSR1, sigusr1Handler)
signal.signal(signal.SIGHUP, sighupHandler)
signal.signal(signal.SIGTERM, sigtermHandler)

def main():
    try:
        os.mkfifo(FIFO_PATH)
    except OSError as oe:
        if oe.errno != errno.EEXIST:
            raise

    print("SERWER DZIAŁA")
    server_input_fifo = open(FIFO_PATH, "r")

    while True:
        rlist, _, _ = select.select([server_input_fifo], [], [])
        raw_request = server_input_fifo.readline()

        if not raw_request:
            continue

        raw_request_split = raw_request.strip().split(" ")
        if len(raw_request_split) != 2:
            print("400 ZŁY REQUEST")
            continue

        [person_id, client_input_fifo_path] = raw_request_split

        print(f"Otrzymano request od klienta: {client_input_fifo_path} W poszukiwaniu osoby o id: {person_id}")

        try:
            with open(client_input_fifo_path, "w") as client_input_fifo:
                person = DB.get(person_id, "404 - NIE ZNALEZIONO")
                client_input_fifo.write(f"{person}\n")
        except FileNotFoundError:
            print("PLIK FIFO NIE ZNALEZIONY")


if __name__ == "__main__":
    main()

#ps all | grep 'server.py' | awk '{print $3}' | xargs kill -s SIGUSR1