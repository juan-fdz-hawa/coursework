(ns wk4.problems)

(defn last-pair [seq]
  (cond (empty? seq) nil
        (empty? (rest seq)) (first seq)
        :else (last-pair (rest seq))))

(defn same-parity [first-el & rest]
  (let [parity (if (odd? first-el) odd? even?)]
    (cons first-el (filter parity rest))))

(defn for-each [seq action]
  (defn actuator [seq]
    (action (first seq))
    (rest seq))
  (if (empty? seq) nil (for-each (actuator seq) action)))

; Tree recursion baby
(defn substitute [sentence old-word new-word]
  (defn substitute-inner [s]
    (let [el (first s) the-rest (rest s)]
      (cond (empty? s) nil
            (list? el) (cons (substitute-inner el) (substitute-inner the-rest))
            (= el old-word) (cons new-word (substitute-inner the-rest))
            :else (cons el (substitute-inner the-rest)))))
  (substitute-inner sentence))
