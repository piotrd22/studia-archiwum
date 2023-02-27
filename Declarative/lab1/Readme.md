1. Which values have the following expressions?

> 10

> (+ 5 3 4)

> (- 9 1)

> (/ 6 2)

> (+ ( \* 2 4) (- 4 6))

> (let ((a 1) (b 2)) (+ a b (\* a b)))

> a

> (define a 3)

> a

> (define b (+ a 1))

> (+ a b (\* a b))

> (= a b)

> (if (and (> b a) (< b (\* a b)))

      b
      a)

> (cond ((= a 4) 6)

        ((= b 4) (+ 6 7 a))
        (else    25))

2. Please define functions (new.< x y), (new.> x y), (new.= x y), (new.<= x y), (new.>= x y) oraz (new.<> x y). No built-in functions are allowed, except for < and boolean functions.

3. Please define functions (nwd a b) and (nww a b) whose values are the greatest common divisor and the least common multiple of (natural numbers) a and b.

4. Please define functions (odd? n) and (even? n) checking whether a natural number n is odd or even, resp. No built-in functions are allowed, except for #t, #f, zero? and a function "-1" calculating n-1.

5. Please define a function (same-values? p1 p2 x y) whose value is #t, if p1(x,y) i p2(x,y) have the same value and #f otherwise.

Examples: > (same-values? = new.= 3 1)
#t > (same-values? < > 2 3)
#f > (same-values? + \* 2 2)
#t
