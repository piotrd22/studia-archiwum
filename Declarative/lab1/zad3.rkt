#lang racket

(define (nwd a b)
  (if (= b 0)
      a
      (nwd b (modulo a b))))

(define (nww a b)
  (/ (* a b) (nwd a b)))

(nww 2 6)