#lang racket

(define (filter-accumulate combiner null-value term a next b pred)
  (if (> a b)
      null-value
      (if (pred a)
          (combiner (term a) (filter-accumulate combiner null-value term (next a) next b pred))
          (filter-accumulate combiner null-value term (next a) next b pred))))

(define (prime? n)
  (cond ((<= n 1) #f)
        ((= n 2) #t)
        ((even? n) #f)
        (else (let loop ((i 3))
                (cond ((> (* i i) n) #t)
                      ((zero? (modulo n i)) #f)
                      (else (loop (+ i 2))))))))

(filter-accumulate + 0 (lambda (x) x) 1 (lambda (x) (+ x 1)) 5 (lambda (x) (prime? x)))
