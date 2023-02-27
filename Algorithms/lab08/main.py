def lcsLength(x, y):
    m = len(x)
    n = len(y)
    c = [[0 for i in range(n + 1)]  # utworzenie tablic wypełnienie zerami
         for j in range(m + 1)]
    b = [[0 for i in range(n + 1)]
         for j in range(m + 1)]
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if x[i - 1] == y[j - 1]:
                c[i][j] = c[i - 1][j - 1] + 1
                b[i][j] = "\\"
            else:
                if c[i - 1][j] >= c[i][j - 1]:
                    c[i][j] = c[i - 1][j]
                    b[i][j] = "|"
                else:
                    c[i][j] = c[i][j - 1]
                    b[i][j] = "-"
    return c, b


def PrintLCS(x, b, i, j, wynik):
    if i == 0 or j == 0:
        return "".join(wynik[::-1])
    if b[i][j] == "\\":
        wynik.append(x[i - 1])
        PrintLCS(x, b, i - 1, j - 1, wynik)

    elif b[i][j] == "|":
        PrintLCS(x, b, i - 1, j, wynik)
    else:
        PrintLCS(x, b, i, j - 1, wynik)
    return "".join(wynik[::-1])


x = "01010"   # sprawdzamy czy dobrze spr poszedl
y = "01101"

print(x, y)
print("")

w1, w2 = lcsLength(x, y)
for i in w1:
    print(i)
print("")
for i in w2:
    print(i)
print("")

max = w1[len(x)][len(y)]
podciagi = []
for i in range(0, len(x) + 1):
    for j in range(0, len(y) + 1):
        if w1[i][j] == max:
            if not PrintLCS(x, w2, i, j, []) in podciagi:
                podciagi.append(PrintLCS(x, w2, i, j, []))
print(podciagi)
