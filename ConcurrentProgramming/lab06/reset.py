import sysv_ipc

KEY = 12

try:
    sem1 = sysv_ipc.Semaphore(KEY, sysv_ipc.IPC_CREX, 0o700, 0)
    sem2 = sysv_ipc.Semaphore(KEY + 1, sysv_ipc.IPC_CREX, 0o700, 1)
    mem = sysv_ipc.SharedMemory(KEY, sysv_ipc.IPC_CREX)

except sysv_ipc.ExistentialError:
    sem1 = sysv_ipc.Semaphore(KEY)
    sem2 = sysv_ipc.Semaphore(KEY + 1)
    mem = sysv_ipc.SharedMemory(KEY)

sysv_ipc.remove_shared_memory(mem.id)
sysv_ipc.remove_semaphore(sem1.id)
sysv_ipc.remove_semaphore(sem2.id)

# ipcs -s | awk '/[0-9]+/ {print $2}' | xargs -n 1 ipcrm -s
# ipcs -m | awk '/[0-9]+/ {print $2}' | xargs -n 1 ipcrm -m