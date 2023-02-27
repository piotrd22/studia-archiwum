#lang racket

(define (same-values? p1 p2 x y)
  (equal? (p1 x y) (p2 x y)))

(same-values? < > 2 3)
(same-values? + * 2 2)