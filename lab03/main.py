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
