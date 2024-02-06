import sysv_ipc
import signal
import sys
import time
import globalfile

dictionary = {
    "butter": "maslo",
    "apple": "jablko",
    "door": "drzwi"
}

print("START SERWERA")

serverq = sysv_ipc.MessageQueue(globalfile.SERVER_KEY, sysv_ipc.IPC_CREAT)
clientq = sysv_ipc.MessageQueue(globalfile.CLIENT_KEY, sysv_ipc.IPC_CREAT)

while True:
    m, pid = serverq.receive(True, 0)
    m = m.decode()
    response = dictionary.get(m, "Nie znam takiego słowa")
    clientq.send(response.encode(), True, pid)
    time.sleep(2)

