(ns wk1.hw1)

(defn square [n] (* n n))

;Takes a sequence of numbers and returns a new sequence
;with each number squared
(defn squares [numbers]
  (let [first-n (first numbers) rest-n (rest numbers)]
  (if (empty? numbers) nil (cons (square first-n) (squares rest-n)))))
(print (squares '(1 2 3)))
(newline)

;Replaces every instance of "I" or "me" with "you"
;and every instance of "you" with me, except for if it is
;the first word of a sentence
(defn switch [sentence]
  (let [word (first sentence) the-rest (rest sentence)]
  (cond (empty? sentence) nil
        (or (= word "I") (= word "me")) (cons "you" (switch the-rest))
        (= word "you") (cons "me" (switch the-rest))
        :else (cons word (switch the-rest)))))
(print (switch '("you" "told" "me" "that" "I" "should" "wake" "you" "up")))
(newline)

;Returns true if the sequence is ordered
(defn ordered? [numbers]
  (let [current-n (first numbers) the-rest (rest numbers)]
  (cond (or (empty? numbers) (empty? the-rest)) true
        :else (and (<= current-n (first the-rest)) (ordered? the-rest)))))
(print (ordered? '(2 2 3)))
(newline)

;Returns only sentences that end with e
(defn ends-e [sentence]
  (let [word (first sentence) ends-with (last word) the-rest (rest sentence)]
  (cond (empty? sentence) '()
        (= ends-with \e) (cons word (ends-e the-rest))
        :else (ends-e the-rest))))
(print (ends-e '("bye" "hello" "word" "bye" "rede")))