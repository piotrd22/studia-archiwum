#lang racket

(define (root f a b tol) ; added tolerance
  (if (> (* (f a) (f b)) 0)
      #f ; no root in [a, b]
      (let loop ((a a) (b b))
        (let ((c (/ (+ a b) 2)))
          (cond ((= (f c) 0) c) ; found exact root
                ((< (* (f a) (f c)) 0) (loop a c))
                (else (loop c b))))))
  (let ((mid (/ (+ a b) 2)))
    (if (< (- b a) tol)
        mid ; approximate root
        (let ((left (root f a mid tol))
              (right (root f mid b tol)))
          (if left left right)))))

(define (f x) (- (+ (* x x x) (* -5 x)) 3))

(root f 1 2 0.0001)
