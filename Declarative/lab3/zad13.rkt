#lang racket

(define (f g) (g 2))
;;; That is, f is equivalent to (lambda (x) (x 2)).

(f (lambda (x) (* x x)))
;;; a) (f square) is equivalent to (square 2), which evaluates to 4.



(f (lambda (z) (+ z (* 3 z))))
;;; b) (f (lambda (z) (+ z (* 3 z)))) is equivalent to ((lambda (z) (+ z (* 3 z))) 2), which evaluates to 8.

;;; (f f)
;;; c) Here i have error application: not a procedure expected a procedure that can be applied to arguments
;;; (f (lambda (x) (f x)))
