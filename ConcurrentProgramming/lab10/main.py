import math
import time
from multiprocessing import Pool

l=1000000
r=2000000

def pierwsza(k):
    for i in range (2,k-1):
        if i*i>k:
            return True
        if k%i == 0:
            return False
        return True

def stworz_mlp(r):
    mlp = []
    s = math.ceil(math.sqrt(r))
    for i in range (2,s+1):
        if pierwsza(i):
            mlp.append(i)
    return mlp

 
def pierwsza1(k,mlp):
    for p in mlp:
        if k%p == 0:
            return False
        if p*p>k:
            return True
        return True


def licz(l,r, mlp):
    pierwsze = []
    for i in range (l,r+1):
        if pierwsza1(i,mlp):
            pierwsze.append(i)
    return set(pierwsze)

if __name__ == '__main__':
    print(l,r)
    mlp = stworz_mlp(r)
    procesy = 4

    print("Start programu sekwencyjnego")
    start1 = time.time()
    wynik1 = licz(l, r, mlp)
    koniec1 = time.time() - start1
    print("Czas programu sekwencyjnego: ", koniec1)

    print("Start programu wieloprocesowego")
    with Pool(procesy) as pool:
        argumenty = []
        czesc = (r - l) // procesy
        for i in range(procesy):
            argumenty.append([l + i * czesc, l + (i + 1) * czesc, mlp])
        start2 = time.time()
        wynik2 = pool.starmap(licz, argumenty)
        koniec2 = time.time() - start2

    print("Czas programu wieloprocesowego", koniec2)
    print("Przyspieszenie programu korzystajac z kilku procesow", koniec1 / koniec2)
    print("Czy wyniki sa zgodne?", wynik1 == set.union(*wynik2))
