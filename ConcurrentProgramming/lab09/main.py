import math
import threading
from threading import Barrier

l = 2
r = 20
num_threads = 5 
pierwsze = []

def pierwsza(k):
    # sprawdzenie, czy k jest pierwsza
    s = math.ceil(math.sqrt(k))
    for i in range(2, s + 1):
        if k % i == 0:
            return False
    return True

def znajdz_pierwsze_w_podprzedziale(start, end, barrier):
    local_pierwsze = []
    for i in range(start, end + 1):
        if pierwsza(i):
            local_pierwsze.append(i)

    pierwsze.extend(local_pierwsze)
    barrier.wait()

bariera = Barrier(num_threads + 1)

podprzedzial = (r - l + 1) // num_threads

threads = []

# Rozpoczęcie pracy wątków
for i in range(num_threads):
    start = l + i * podprzedzial
    end = start + podprzedzial - 1 if i < num_threads - 1 else r
    thread = threading.Thread(target=znajdz_pierwsze_w_podprzedziale, args=(start, end, bariera))
    thread.start()
    threads.append(thread)

# Poczekaj na zakończenie wszystkich wątków
bariera.wait()

print(sorted(pierwsze))
