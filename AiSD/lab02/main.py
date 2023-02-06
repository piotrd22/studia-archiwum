#Funkcja kopcująca
kopiec = [2,6,3,3,4,5,7,8,6,7]

def heapify(heap, heapLen, index):
    r = 2*index + 2 #right son
    l = 2*index + 1 #left son
    if heapLen > l and heap[l] > heap[index] :
        largest = l
    else:
        largest = index
    if heapLen > r and heap[r] > heap[largest]:
        largest = r
    if largest != index:
        heap[index], heap[largest] = heap[largest], heap[index]
        heapify(heap, heapLen, largest)
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
    for i in range(len(heap)-1, 0, -1):
        heap[0], heap[heapLen-1] = heap[heapLen-1], heap[0]
        heapLen -= 1
        heapify(heap, heapLen, 0)
    return heap

# heapify v2
def heapify2(heap, heapLen, index):
    done = True
    while done:
        largest = index
        r = 2 * index + 2  # right son
        l = 2 * index + 1  # left son
        if heapLen > l and heap[l] > heap[index]:
            largest = l
        else:
            largest = index
        if heapLen > r and heap[r] > heap[largest]:
            largest = r
        if largest != index:
            heap[index], heap[largest] = heap[largest], heap[index]
            index = largest
        else:
            done = False
    return heap

def buildHeap2(heap):
    heapLen = len(heap)
    father = (heapLen-2) // 2

    for i in range(father, -1, -1):
        heapify2(heap, heapLen,i)
    return heap

# heapSort

def heapSort2(heap):
    heap = buildHeap2(heap)
    heapLen = len(heap)
    for i in range(len(heap), 0, -1):
        heap[0], heap[heapLen-1] = heap[heapLen-1], heap[0]
        heapLen -= 1
        heapify2(heap, heapLen,0)
    return heap



def listffile(file):
    filez = open(file, "r")
    matrix = []
    for line in filez:
        matrix.append(int(line))
    return matrix


def listtofile(file, new):
    lines = new
    for i in range(len(lines)):
        lines[i] = str(lines[i])+"\n"
    score_txt = open(file, "w")
    score_txt.writelines(lines)
    score_txt.close()

macierz = listffile('matrix.txt')
listtofile('odp.txt', heapSort(macierz))
