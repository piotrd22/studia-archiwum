lines = open("input.txt").read().splitlines()
macierz = []
wyniki = []

for line in lines:
    temp = []
    for string in line.split(" "):
        temp.append(float(string))

    macierz += [temp[:-1]]
    wyniki += temp[-1:]

def obl(macierz, wyniki):
    a = macierz
    b = wyniki
    aleny = (len(a))
    alenx = (len(a[0]))

    # do postaci schodkowej
    for i in range(aleny - 1):
        def f(p):
            x = a[i][i]
            if x != 0:
                for s in range(alenx):
                    a[i][s] /= x
                b[i] /= x
            for j in range(i + 1, aleny):
                if a[i][i] != 0:
                    v = a[j][i] / a[i][i]
                    for k in range(i, alenx):
                        a[j][k] -= v * a[i][k]
                    b[j] -= v * b[i]
                else:
                    for l in range(i + 1, aleny):
                        if a[l][i] != 0:
                            a[i], a[l] = a[l], a[i]
                            b[i], b[l] = b[l], b[i]
                            break
                    p = p + 1
                    if p <= aleny:
                        f(p)

        f(0)

    for i in reversed(range(1, aleny)):
        n = a[i][i]
        if n != 0:
            for j in range(alenx):
                a[i][j] /= n
            b[i] /= n
            break

    # do postaci schodkowej normalnej
    for i in reversed(range(aleny)):
        for j in reversed(range(0, i)):
            if a[i][i] != 0:
                if a[i][i] != 0:
                    v = a[j][i] / a[i][i]
                    for k in range(i, alenx):
                        a[j][k] -= a[i][k] * v
                    b[j] -= b[i] * v

    return a, b


a2, b2 = obl(macierz, wyniki)

for i in range(len(a2)):
    print(str(a2[i]) + "|" + str(b2[i]))
print("\n")

answers = [0 for i in range(len(a2))]

if len(a2) < len(a2[0]):
    print("układ ma nieskończenie wiele rozwiązań.")
else:
    s = False
    for i in range(len(a2)):
        mzerowe = 0
        if all(v == 0 for v in a2[i]):
            if b2[i] != 0:
                print("układ jest sprzeczny.")
                sprzeczny = True
            mzerowe += 1
    if len(a2) - mzerowe < len(a2[0]) and s == False:
        print("układ ma nieskończenie wiele rozwiązań")
    elif (s == False):
        answers[len(a2) - 1] = b2[len(a2) - 1] / a2[len(a2) - 1][len(a2) - 1]
        for j in reversed(range(0, len(a2) - 1)):
            suma = b2[j]
            for k in range(j + 1, len(a2)):
                suma -= answers[k] * a2[j][k]
            answers[j] = suma / a2[j][j]
        print(answers)
