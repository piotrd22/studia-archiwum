import threading
import time
import random

def sum_sublist(lst, start, end, result, lock):
    partial_sum = sum(lst[start:end])
    with lock:
        result.append(partial_sum)

def parallel_sum(lst, num_threads):
    list_length = len(lst)
    thread_chunks = [(i * list_length) // num_threads for i in range(num_threads + 1)]
    threads = []
    result = []

    lock = threading.Lock()

    for i in range(num_threads):
        start = thread_chunks[i]
        end = thread_chunks[i + 1]
        thread = threading.Thread(target=sum_sublist, args=(lst, start, end, result, lock))
        threads.append(thread)
        thread.start()
    
    [thread.join() for thread in threads]

    total_sum = sum(result)
    return total_sum


M = 10000000  # rozmiar tablicy
numbers = [random.randint(0, 100) for i in range(M)]

print(f"Poprawna wartość: {sum(numbers)}")

# Regulacja ilości wątków
num_threads = 3

# Pomiar czasu
start_time = time.time()

# Wywołanie funkcji sumującej z określoną ilością wątków
result_sum = parallel_sum(numbers, num_threads)

end_time = time.time()
elapsed_time = end_time - start_time

print(f"Suma elementów listy: {result_sum}")
print(f"Czas wykonania: {elapsed_time} sekundy")
