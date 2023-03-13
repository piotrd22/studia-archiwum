14. Please define a function (root f a b) approximating a root of f between a and b using the half-interval-method.

15. Suppose the following function definitions are given.

(define (comb f g)
(lambda (x) (f (g x))))

(define (square n) (\* n n))

(define (double n) (+ n n))

Please show how the following expression ((comb square double) 5) is evaluated.

16. Please define a function (iter f n), whose value is the function fn.

17. Please define the following functions for lists.

a) (append l m), whose value is the concatenation of the lists l i m.

b) (last l), whose value is the last element of the list l.

c) (reverse l), whose value is the list l in reversed order.

d) (delete x l), whose value is the list l without the element x.

e) (pairing l1 l2), that builds a list of pairs out of the lists l1 and l2. Example:

> (pairing '(1 2 3) '(a b c))
> '((1.a) (2.b) (3.c))

f) (split x l), that splits l into two lists l1 and l2. l1 contains the element of l smaller than x and l2 the elements greater than x.
