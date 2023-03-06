10. Please define a function (product term next a b) analogous to function sum.
    Please show, how the function product can be used to define both function silnia and for the approximation of p using the formula p/4 = 2 · 4 · 4 · 6 · 6 · 8 ··· / 3 · 3 · 5 · 5 · 7 · 7 ··· .

11. Please define a function (accumulate combiner null-value term a next b)generalizing functions sum i product: The arguments term, next, a, and b are the same as in the definitions of sum i prod.
    combiner is a two-argument function describing, how (term a) is tied together with the recursive result. null-value is the initial value used in the basic step of recursion.
    Please also show, how sum i product can be defined using accumulate.

12. Even function accumulate can be generalized: Please define a function filter-accumulate with an additional argument pred.
    pred is a one-argument predicate and filter-accumulate ties (term a) to the result only if a fulfills pred.
    Using filter-accumulate please compute the sum of squares of the prime numbers in the interval [a,b] and the product of the natural numbers i smaller than n for which nwd(i,n) = 1.

13. Suppose the following definition of function f.

(define f g) (g 2))

Please show how the following expressions are evaluated.

a) (f square)

b) (f (lambda (z) (+ z (\* 3 z)))

c) (f f)
