# W + OL
import math

m = int(input("Podaj rozmiar m: "))
procent = int(input("Podaj procent wypełnienia tablicy (sama liczba): "))

file = open("nazwiskaASCII.txt")
tablica = []
for i in file:
    x = i[:-1].split(" ")
    tablica.append((int(x[0]), x[1]))
file.close()

def h1(k):
    return hash(k) % m

def h(k, i):
    return (h1(k[1]) + i) % m

T = [[] for i in range(m)]

def wstaw_element(x):
    for i in range(m * m):
        if T[h(x, i)] == [] or T[h(x, i)] == ['del']:
            T[h(x, i)].append(x)
            break

def wstaw(x):
    for i in range(x):
        wstaw_element(tablica[i])

def sym(x):
    j = 1
    for i in range(m * m):
        if T[h(x, i)] == [] or T[h(x, i)] == ['del']:
            return j
        j += 1

def symulacja(x):
    j = 0
    suma = 0
    for i in range(x, x + math.floor(5 * m / 100) + 1):
        suma += sym(tablica[i])
        j += 1
    return suma / j

i = math.floor(m / 100 * procent)
wstaw(i)
print("Średnia ilosc prób: " + str(round(symulacja(i), 2)))

"""
Podaj rozmiar m: 5711
Podaj procent wypełnienia tablicy (sama liczba): 50
Średnia ilosc prób: 2.42

Podaj rozmiar m: 5711
Podaj procent wypełnienia tablicy (sama liczba):70
Średnia ilosc prób:5.47

Podaj rozmiar m: 5711
Podaj procent wypełnienia tablicy (sama liczba):90
Średnia ilosc prób:35.03

Podaj rozmiar m: 14387
Podaj procent wypełnienia tablicy (sama liczba):50
Średnia ilosc prób:2.65

Podaj rozmiar m: 14387
Podaj procent wypełnienia tablicy (sama liczba):70
Średnia ilosc prób: 5.98

Podaj rozmiar m: 14387
Podaj procent wypełnienia tablicy (sama liczba):90
Średnia ilosc prób:46.78
"""
