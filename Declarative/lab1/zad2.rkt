#lang racket

(define (new.< x y)
  (< x y))

(define (new.> x y)
  (< y x))

(define (new.= x y)
  (and (not (new.< x y)) (not (new.< y x))))

(define (new.<= x y)
  (or (new.< x y) (new.= x y)))

(define (new.>= x y)
  (or (new.> x y) (new.= x y)))

(define (new.<> x y)
  (not (new.= x y)))

(new.> 5 2)