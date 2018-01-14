(ns wk5.calc,
  (:require [wk5.problems :as lib]))

; Simple meta calculator

(defn calc-apply [fn args]
  (cond (= fn '+) (lib/accumulate + 0 args)
        (= fn '-) (cond (empty? args) (throw (Exception. "Calc: no args to -"))
                        (= (count args) 1) (- (first args))
                        :else (- (first args) (lib/accumulate + 0 (rest args))))
        (= fn '*) (lib/accumulate * 1 args)
        (= fn '/) (cond (empty? args) (throw (Exception. "Calc: no args to /"))
                        (= (count args) 1) (/ (rest args))
                        :else (/ (first args) (lib/accumulate * 1 (rest args))))
        :else (throw (Exception. "Calc: bad operator:" fn))))

(defn calc-eval [exp]
  (cond (number? exp) exp
        (list? exp) (calc-apply (first exp) (map calc-eval (rest exp)))
        :else (throw (Exception. "Calc: bad expression:" exp))))

(print (calc-eval '(+ 2 1 10 (- 5 2))))
(newline)
(print (calc-eval '2))