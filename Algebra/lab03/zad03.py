import matplotlib.pyplot as plt
import numpy as np
import math

print('Proszę podać liczbę pierwiastka')
c = input()
n = int(c)

x = []
y = []

for i in range(0,n):
    w=(i*2*math.pi)/n
    x.append(math.cos(w))
    y.append(math.sin(w))

plt.fill(x,y, facecolor='red', edgecolor='blue')

plt.show()
