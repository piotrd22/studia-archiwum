from timeit import default_timer as timer

def partition(A,p,r):
    x=A[r]
    i=p-1
    for j in range (p, r+1):
        if A[j]<=x :
            i=i+1
            A[i], A[j] = A[j], A[i]
    if i<r :
        return i
    else:
        return i-1


def quickSort(A):
    def betterquicksort(A,p,r):
        if p<r :
            q = partition(A,p,r)
            betterquicksort(A,p,q)
            betterquicksort(A,q+1,r)
        return A
    return betterquicksort(A, 0, len(A)-1)

print(quickSort([1,2,3,4,5,6,7,8,9,10,1]))


def bubblesort(A, l, h):
    for i in range(l, h):
        for j in range(l, h):
            if A[j] > A[j+1]:
                A[j], A[j+1] = A[j+1], A[j]
    return A
# print(bubblesort([6,5,2,9,10,20,18,0]))

def modifiedquicksort(A):
    c = 12
    def bqs(A,p,r):
        if r - p + 1 < c:
            bubblesort(A, p, r)
        elif p < r:
            q = partition(A, p, r)
            bqs(A, p, q)
            bqs(A, q + 1, r)
        return A

    return bqs(A, 0, len(A) - 1)
print(modifiedquicksort([1,2,3,4,5,6,7,8,9,10,1]))

arr = [6,3,8,10,14,12,9]
arr2 = [1,2,3,4,5,6,7,8,9]

start = timer()
modifiedquicksort(arr2)
stop = timer()
Tn = stop-start
print(Tn)

#DANE LOSOWE
#mod == 1.4100000000002999e-05
#nie mod == 1.3700000000005375e-05

#DANE NIEKORZYSTNE
#mod == 5.58000000000225e-05
#nie mod == 0.0003576999999999886

# ALGORYTM PODSTAWOWY W OBU PRZYPADKACH JEST SZYBSZY
