(ns wk3.hw3, (:require [wk2.hw2 :as wk2]))

(defn square [x] (* x x))

; Space and time complexity: O(n)
(defn expo [b n]
  (if (= n 0) 1 (* b (expo b (- n 1)))))
(print (expo 2 5))
(newline)

; Recursive process for computing b ^ n in O(log(n)) time and space complexity
(defn fast-expo-1 [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expo-1 b (/ n 2)))
        (odd? n) (* b (fast-expo-1 b (- n 1)))))
(print (fast-expo-1 2 5))
(newline)

; Iterative process for computing b ^ n in O(log(n)) time, O(1) space.
(defn fast-expo-2 [b n]
  (defn fast-expo-iter [b n a]
    (cond (= n 0) 1
          (= n 1) (* a b)
          (even? n) (fast-expo-iter (square b) (/ n 2) a)
          (odd? n) (fast-expo-iter (square b) (/ (- n 1) 2) (* a b))))
  (fast-expo-iter b n 1))
(print (fast-expo-2 2 6))
(newline)

; Approximating to the golden ratio by using x |-> 1 + 1/x
(print (wk2/fixed-point (wk2/average-damp (fn [x] (+ 1 (/ 1 x)))) 1.0))
(newline)

; k-term continuous fractions: it can be shown that if n-i and d-i both equal
; to 1, the infinite continuous fraction will eventually converge to 1/golden ratio.
(defn cont-frac [n d k]
  (defn cont-frac-iter [i]
    (if (= i k)
      (/ (n i) (d i))
      (/ (n i) (+ (d i) (cont-frac-iter (+ i 1))))))
  (cont-frac-iter 1))
(print (cont-frac (fn [_] 1.0) (fn [_] 1.0) 20))
(newline)

; Approximation of e - 2 based on Euler's expansion.
(defn d-i [term]
  (defn d-i-iter [i j a]
    (cond (= term 1) 1
          (= term 2) 2
          (= i (+ term 2)) a
          (= (mod i 3) 0) (d-i-iter(+ i 1) (+ j 1) (* j 2))
          :else (d-i-iter (+ i 1) j 1)))
  (d-i-iter 1 1 2))
(print (+ (cont-frac (fn [_] 1.0) d-i 20) 2))
(newline)

; A perfect number is equal to the sum of its factors minus itself.
; This procedure computes the next perfect number starting from n
(defn is-factor? [a b] (= (rem a b) 0))

(defn sum-of-factors [n]
  (defn sum-of-factors-iter [i acc]
    (cond (> i n) acc
          (is-factor? n i) (sum-of-factors-iter (+ i 1) (+ acc i))
          :else (sum-of-factors-iter (+ i 1) acc)))
  (sum-of-factors-iter 1 0))

(defn next-perfect [n]
  (let [next-n (+ n 1)]
  (if (= (sum-of-factors next-n) (* 2 next-n))
    next-n
    (next-perfect next-n))))
(print (next-perfect 28))
(newline)

; Number of ways to break down an integer.
(defn i-range [n] (if (= n 0) nil (cons n (i-range (- n 1)))))

(defn number-of-partitions [n]
  (defn n-partitions [n parts]
    (cond (= n 0) 1
          (or (< n 0) (empty? parts)) 0
          :else (+ (n-partitions n (rest parts))
                   (n-partitions (- n (first parts)) parts))))
  (n-partitions n (i-range n)))
(print (number-of-partitions 5))
(newline)