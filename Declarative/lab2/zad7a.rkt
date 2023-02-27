#lang racket

;;; Normal recursive version

(define (fibonacci n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        (else (+ (fibonacci (- n 1)) (fibonacci (- n 2))))))

;;; Acc version

(define (fibonacci2 n)
  (define (fibonacci_helper i prev curr)
    (if (> i n)
        prev
        (fibonacci_helper (+ i 1) curr (+ prev curr))))
  (fibonacci_helper 1 0 1))

(fibonacci 6)
(fibonacci2 6)