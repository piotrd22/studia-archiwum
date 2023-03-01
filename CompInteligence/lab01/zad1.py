def prime(num):
    if num <= 1:
        return False
    for i in range(2, int(num / 2) + 1):
        if num % i == 0:
            return False
    return True


def select_primes(arr):
    return list(filter(prime, arr))


print(prime(2))
print(prime(3))
print(select_primes([3, 6, 11, 25, 19, 7]))
