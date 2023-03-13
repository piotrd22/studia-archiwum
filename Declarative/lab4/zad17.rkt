#lang racket

;;; ; Creating a list
;;; (define my-list '(1 2 3 4 5))

;;; ; Getting the first element of the list
;;; (car my-list) ; Output: 1

;;; ; Getting the rest of the list
;;; (cdr my-list) ; Output: (2 3 4 5)

;;; ; Adding an element to the front of the list
;;; (cons 0 my-list) ; Output: (0 1 2 3 4 5)

;;; ; Checking if a list is empty
;;; (null? my-list) ; Output: #f
;;; (null? '()) ; Output: #t

;;; ; Checking the length of a list
;;; (length my-list) ; Output: 5

;;; ; Accessing an element of the list by index
;;; (list-ref my-list 2) ; Output: 3

;;; ; Removing the last element of the list
;;; (reverse (cdr (reverse my-list))) ; Output: (1 2 3 4)

;;; ; Appending two lists together
;;; (append '(1 2 3) '(4 5 6)) ; Output: (1 2 3 4 5 6)


(define test_list '(1 8 3 10 -34))
(define test_list2 '(12 14 16 ))

;;; a)
(define (append2 l m)
  (if (null? l) m
      (cons (car l) (append (cdr l) m))))

(append2 test_list test_list2)

;;; b)
(define (last l)
  (if (null? (cdr l))
      (car l) (last (cdr l))))

(last test_list)

;;; c)
(define (reverse l)
  (if (null? l) '()
      (append (reverse (cdr l)) (list (car l)))))

(reverse test_list)

;;; d)
(define (delete x l)
  (cond ((null? l) '())
        ((equal? (car l) x) (delete x (cdr l)))
        (else (cons (car l) (delete x (cdr l))))))

(delete 10 test_list)

;;; e)
(define (pairing l1 l2)
  (if (or (null? l1) (null? l2)) '()
      (cons (cons (car l1) (car l2)) (pairing (cdr l1) (cdr l2)))))

(pairing test_list test_list2)

;;; f)
(define (split x l)
  (cond ((null? l) (list '() '()))
        ((< (car l) x)
         (let ((rest (split x (cdr l))))
           (list (cons (car l) (car rest)) (cadr rest)))) ; In Scheme, cadr is a built-in function that returns the second element of a list.
        ((> (car l) x)
         (let ((rest (split x (cdr l))))
           (list (car rest) (cons (car l) (cadr rest)))))
        (else (split x (cdr l)))))

(split 9 test_list)

(cadr test_list)
