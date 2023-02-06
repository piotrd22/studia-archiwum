while True:
    print('Podaj czy chcesz dodawac (d), mnozyc (m),dzielic (dz), czy wyjsc (q)')
    print('Liczby zespolone podawaj jako j')
    x = input()
    if x == 'q':
        break
    y = 0
    if x != 'dz':
        print('Podaj liczbę liczb')
        n = input()
        for i in range(1, int(n) + 1):
            print('Podaj liczbę')
            a = complex(input())
            if x == 'd':
                y += a
            if x == 'm':
                y *= a
        print('Wynik:')
        print(y)
    else:
        print('Podaj liczby do dzielenia')
        c = complex(input())
        d = complex(input())
        if d == 0:
            print('Prosze nie dzielic przez 0!!!')
        else:
            print('Wynik:')
            print(c/d)
