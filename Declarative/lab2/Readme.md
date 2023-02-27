6. a) Please define a function (factorial n) whose value is n! using recursion.

b) The factorial function can also be defined the following way:

(define (factorial_help n i acc)
(if (< n i)
acc
(factorial_help n (+ i 1) (\* i acc))))

(define (factorial n)
(factorial_help n 1 1))

7. Please show how the expression (factorial 4) is evaluated, once for the recursive version a) and again for version b).
   Note: The argument acc is called accumulator and one says that this version defines the faculty function using accumulation.

a) Please define functions (fibonacci n) whose value is the n-th element of the Fibonacci sequence, one using recursion and again using accumulation.

b) For both versions please show how the expression (fibonacci 4) is evaluated.

8. a) The exponentiation b^e (for natural numbers b and e) can be quickly executed using b^e = (b^(e/2))^2.
   Using this property please define functions (exp b e) whose value is b^e, one recursive function and one using accumulation.

b) For both versions please show how the expression (exp 2 6) is evaluated.

9. Why the expression if cannot be defined the following way?

(define (new-if condition consequence alternative)
(cond (condition consequence)
(else alternative)))
