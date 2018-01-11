(ns wk1.lab1)

(defn square [n] (* n n))

;Assume that, at this point in time, you don't know nothing about sets.
(defn member? [el seq]
  (if (empty? seq)
    false
    (or (= (first seq) el) (member? el (rest seq)))))
(print (member? "z" '("a" "e" "i")))
(newline)

;Given three numbers, returns the sum of the square of the two largest
(defn square-of-largest [x y z]
  (cond (or (and (>= x y) (>= y z)) (and (>= y x) (>= x z))) (+ (square x) (square y))
        (or (and (>= x z) (>= z y)) (and (>= z x) (>= x y))) (+ (square x) (square z))
        :else (+ (square y) (square z))))
(print (square-of-largest 10 1 2))
(newline)

;Removes duplicates from a sentence
(defn duplicates-removed [s]
  (defn duplicates-removed-iter [sentence seen]
    (let [word (first sentence) the-rest (rest sentence)]
      (cond (empty? sentence) '()
            (member? word seen) (duplicates-removed-iter the-rest seen)
            :else (cons word (duplicates-removed-iter the-rest (cons word seen))))))
  (duplicates-removed-iter s '()))
(print (duplicates-removed '("hello" "hello" "pussy" "cat")))
(newline)