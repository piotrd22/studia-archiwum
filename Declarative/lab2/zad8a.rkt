#lang racket

(define (exp-norm b e)
  (if  (= e 0)
       1
       (* b (exp-norm b (- e 1)))))

(define (exp b e)
  (cond ((= e 0) 1)
        ((= e 1) b)
        (else (let ((half (exp b (quotient e 2))))
                (if (= (remainder e 2) 0)
                    (* half half)
                    (* half half b))))))

(define (exp2 b e)
  (define (exp_helper b e acc)
    (cond ((= e 0) acc)
          ((= e 1) (* acc b))
          (else (if (= (remainder e 2) 0)
                    (exp_helper (* b b) (quotient e 2) acc)
                    (exp_helper (* b b) (quotient e 2) (* acc b))))))
  (exp_helper b e 1))

(exp-norm 2 6)
(exp 2 6)
(exp2 2 6)

;;; quotient is a built-in procedure in Scheme that performs integer division and returns the quotient of two integers.

;;; For example, (quotient 10 3) would evaluate to 3, since 10 divided by 3 is 3 with a remainder of 1.
;;; (quotient 11 3) would also evaluate to 3, since the quotient of 11 divided by 3 is also 3, with a remainder of 2.

