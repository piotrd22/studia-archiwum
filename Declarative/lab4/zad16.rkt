#lang racket

(define (iter f n)
  (define (iter-helper g i)
    (if (= i n)
        g
        (iter-helper (compose f g) (+ i 1))))
  (iter-helper f 1))

(define (fifth-power x)
  ((iter (lambda (y) (* y x)) 5) 1))

(fifth-power 2)
