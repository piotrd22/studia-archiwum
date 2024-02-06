import sysv_ipc
import os
import random
import globalfile

pid = os.getpid()

serverq = sysv_ipc.MessageQueue(globalfile.SERVER_KEY)
clientq = sysv_ipc.MessageQueue(globalfile.CLIENT_KEY)

input("KLIKNIJ ENTER I START!")

for i in globalfile.vocab:
    serverq.send(i.encode(), True, pid)
    m, t = clientq.receive(True, pid)
    m = m.decode()
    print(f"{i}: {m}")