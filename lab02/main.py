#Funkcja kopcująca
kopiec = [2,6,3,3,4,5,7,8,6,9]
#
def heapify(heap, heapLen, index):
    r = 2*index + 2 #right son
    l = 2*index + 1 #left son
    if heapLen > l and heap[l] > heap[index] :
        if heapLen > r and heap[l] < heap[r]:
            largest = r
        else:
            largest = l
        heap[index], heap[largest] = heap[largest], heap[index]
        heapify(heap, heapLen, largest)
    else:
        largest = index
    return heap


#budowanie kopca

def buildHeap(heap):
    heapLen = len(heap)
    father = (heapLen-2) // 2

    for i in range(father, -1, -1):
        heapify(heap, heapLen, i)
    return heap

# heapSort

def heapSort(heap):
    heap = buildHeap(heap)
    heapLen = len(heap)
    for i in range(len(heap), 0, -1):
        heap[0], heap[heapLen-1] = heap[heapLen-1], heap[0]
        heapLen -= 1
        heapify(heap, heapLen, 0)
    return heap

# heapify v2
def heapify2(heap, heapLen, index):
    largest = 'c'
    while largest != index:
        r = 2 * index + 2  # right son
        l = 2 * index + 1  # left son
        if heapLen > l and heap[l] > heap[index]:
            if heapLen > r and heap[l] < heap[r]:
                largest = r
            else:
                largest = l
            heap[index], heap[largest] = heap[largest], heap[index]
        else:
            largest = index
    r = 2*index + 2 #right son
    l = 2*index + 1 #left son
    if heapLen > l and heap[l] > heap[index] :
        if heapLen > r and heap[l] < heap[r]:
            largest = r
        else:
            largest = l
        heap[index], heap[largest] = heap[largest], heap[index]
    else:
        largest = index
    return heap

def buildHeap2(heap):
    heapLen = len(heap)
    father = (heapLen-2) // 2

    for i in range(father, -1, -1):
        heapify2(heap, heapLen, i)
    return heap

# heapSort

def heapSort2(heap):
    heap = buildHeap2(heap)
    heapLen = len(heap)
    for i in range(len(heap), 0, -1):
        heap[0], heap[heapLen-1] = heap[heapLen-1], heap[0]
        heapLen -= 1
        heapify2(heap, heapLen, 0)
    return heap

print(heapify2(kopiec, 8, 0))
print(heapify(kopiec, 8, 0))
print(heapSort(kopiec))
print(heapSort2(kopiec))

