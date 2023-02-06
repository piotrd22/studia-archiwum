import matplotlib.pyplot as plt
import numpy as np
import math

print('Proszę podać liczbę: '
      'UWAGA! Część zespoloną podawaj jako j! Nie używaj spacji!')
c = complex(input())
data = c

a = data.imag
b = data.real

z = round((a**2 + b**2)**(1/2), 3)

x = [0,b]
y = [0,a]

degrees = round(np.degrees(np.angle(data)), 3)

if a<0:
    plt.xlim(a-1,-a+1)
else:
    plt.xlim(-a-1,a+1)
if b<0:
    plt.ylim(b-1,-b+1)
else:
    plt.ylim(-b-1,b+1)

plt.ylabel(f'Imaginary, |Z| = {z}')
plt.xlabel(f'Real, Degrees = {degrees}')

x1 = [0, 0]
y1 = [-b-1, b+1]

x2 = [-a-1, a+1]
y2 = [0, 0]

plt.plot(x2, y2)
plt.plot(x1, y1)
plt.plot(x, y)

plt.show()
