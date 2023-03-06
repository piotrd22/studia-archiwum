#lang racket

(define (accumulate combiner null-value term a next b)
  (if (> a b)
      null-value
      (combiner (term a) (accumulate combiner null-value term (next a) next b))))

(accumulate + 0 (lambda (x) x) 1 (lambda (x) (+ x 1)) 5)

(define (silnia n)
  (accumulate *
              1
              (lambda (x) x)
              1
              (lambda (x) (+ x 1))
              n))

(silnia 4)