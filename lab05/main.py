func = input("Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): ")
length = int(input("Podaj rozmiar listy T: "))

T = [[] for i in range(length)]

def liczbaB(k, const):
    if len(k) == 1:
        return ord(k[0]) * const
    elif len(k)==2:
        return ord(k[0]) * const + ord(k[1])
    else:
        result=ord(k[0])
        for i in range(1,len(k)-1):
            result = result * const + ord(k[i])
        return result

def hwbudwoana(k):
    return hash(k) % length

def hdobra(k):
    return liczbaB(k,111) % length

def hslaba(k):
    return ord(k[0]) % length

f1 = open("3700.txt")
A=[]

for i in f1:
    A.append(i[:-1])
f1.close()

if func == "w":
    for i in range(2 * length):
        T[hwbudwoana(A[i])].append(A[i])
elif func == "d":
    for i in range(2 * length):
        T[hdobra(A[i])].append(A[i])
elif func == "s":
    for i in range(2 * length):
        T[hslaba(A[i])].append(A[i])

empty = 0
max = len(T[0])
sum = 0
hm = 0

for i in T:
    #print(str(i))
    if i == []:
        empty += 1
    if len(i)>max:
        max=len(i)
    if i!=[]:
        sum += len(i)
        hm += 1

avg = round(sum / hm, 2)

print("\nliczba pustych list w tablicy T: "+str(empty))
print("maksymalna długość listy w T: "+str(max))
print("średnia długość niepustych list w T: "+str(avg))


""" 
Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): w
Podaj rozmiar listy T: 17
###################################################################

liczba pustych list w tablicy T: 2
maksymalna długość listy w T: 5
średnia długość niepustych list w T: 2.27 

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): d
Podaj rozmiar listy T: 17

liczba pustych list w tablicy T: 1
maksymalna długość listy w T: 7
średnia długość niepustych list w T: 2.12

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): s
Podaj rozmiar listy T: 17

liczba pustych list w tablicy T: 2
maksymalna długość listy w T: 5
średnia długość niepustych list w T: 2.27

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): w
Podaj rozmiar listy T: 1031

liczba pustych list w tablicy T: 109
maksymalna długość listy w T: 7
średnia długość niepustych list w T: 2.24

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): d
Podaj rozmiar listy T: 1031

liczba pustych list w tablicy T: 145
maksymalna długość listy w T: 8
średnia długość niepustych list w T: 2.33

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): s
Podaj rozmiar listy T: 1031

liczba pustych list w tablicy T: 981
maksymalna długość listy w T: 183
średnia długość niepustych list w T: 41.24

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): w
Podaj rozmiar listy T: 1024

liczba pustych list w tablicy T: 145
maksymalna długość listy w T: 7
średnia długość niepustych list w T: 2.33

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): d
Podaj rozmiar listy T: 1024

liczba pustych list w tablicy T: 158
maksymalna długość listy w T: 8
średnia długość niepustych list w T: 2.36

###################################################################

Podaj wariant funkcji haszującej (w-wbudowana, d-dobra, s-słaba): s
Podaj rozmiar listy T: 1024

liczba pustych list w tablicy T: 974
maksymalna długość listy w T: 183
średnia długość niepustych list w T: 40.96

###################################################################

Lepsze wyniki dawał rozmiar 1031, bo jest liczbą pierwszą.

Najlepsze wyniki - w, lekko gorsze - d, a najslabsze - s. 

"""
