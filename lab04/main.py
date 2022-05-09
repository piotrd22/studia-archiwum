class Node:
    def __init__(self,k):
        self.key = k
        self.next = None
        self.prev = None 

class LinkedList:
    def __init__(self):
        self.head = Node(None)
        self.head.next = self.head
        self.head.prev = self.head

    def listInsert(self, k): 
    # lista z wartownikiem
        lista=Node(k)
        if self.head.next == self.head:
            self.head.prev = lista
        lista.next = self.head.next
        self.head.next = lista
        lista.prev = self.head
        lista.next.prev = lista

    def listSearch(self,k):
    # szuka wezla zawierajacego klucz k
    # lista z wartownikiem
        lista = self.head.next
        while lista.key != None:
            if lista.key==k:
                return lista
            lista = lista.next
        return None

    def listDelete(self,wezel):
    # lista z wartownikiem
        wezel = self.listSearch(wezel)
        wezel.prev.next = wezel.next
        wezel.next.prev = wezel.prev

    def listPrint(self):
        list = [self.head.key]
        x = self.head
        while x.next.key!=None:
            x = x.next
            list.append(x.key)
        print(list)

    def noRedundant(self):
        list = LinkedList()
        x = self.head
        while x.next.key!=None:
            #if (x.next.key not in used):
            if list.listSearch(x.next.key)==None:
                #used.append(x.next.key)
                list.listInsert(x.next.key)
            x=x.next
        return list
        
def merge(l1, l2):
    merged=LinkedList()
    x=l1.head
    y=l2.head
    z=merged.head

    z.prev=y.prev
    y.prev.next=z

    z.next=x.next
    x.next.prev=z
    x.next=x #x.next=None
    z=x.prev
    x.prev=x #x.prev=None

    z.next=y.next
    y.next.prev=z
    y.next=y #y.next=None
    y.prev=y #y.prev=None

    return merged

print("nowa, pusta lista")
new_list = LinkedList()
new_list.listPrint()

new_list.listInsert(7)
new_list.listInsert(6)
new_list.listInsert(5)
new_list.listInsert(6)
new_list.listInsert(6)

print("\nwypełniona lista")
new_list.listPrint()

print("\nwyszukaj element")
print(new_list.listSearch(5))

print("\nwyszukaj element którego nie ma")
print(new_list.listSearch(12))

print("\nlista bez powtórzeń")
print(new_list.noRedundant())
new_list.noRedundant().listPrint()

print("\nlista po usunięciu elementu")
new_list.listDelete(5)
new_list.listPrint()

print("\nlista 1 do scalenia")
l1 = LinkedList()
l1.listInsert(2)
l1.listInsert(1)
l1.listPrint()

print("\nlista 2 do scalenia")
l2 = LinkedList()
l2.listInsert(4)
l2.listInsert(3)
l2.listPrint()

print("\nscalona lista")
l3=merge(l1,l2)
l3.listPrint()

l1.listPrint()
l2.listPrint()
