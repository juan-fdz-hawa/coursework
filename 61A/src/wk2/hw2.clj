(ns wk2.hw2)

(defn square [x] (* x x))
(defn cube [x] (* x x x))
(defn avg [a b] (/ (+ a b) 2))
(defn abs [a] (if (< a 0) (* -1 a) a))
(defn close-enough? [a b] (< (abs (- a b)) 0.0001))
(defn sin [x] (Math/sin x))
(defn cos [x] (Math/cos x))

; Computes the sum of f(x) from "a" to "b".
(defn sum [a b f next] (if (> a b) 0 (+ (f a) (sum (next a) b f next))))
(print (sum 1 3 identity inc))
(newline)

; Computes the product of f(x) from "a" to "b"
(defn prod [a b f next] (if (> a b) 1 (* (f a) (prod (next a) b f next))))

; Factorial of n using the product operation
(defn factorial [n] (prod 1 n identity inc))
(print (factorial 9))
(newline)

; phi/2 approximation using Wallis' product - gotta watch out for
; those pesky types coercions
(defn wallis-product [n]
  (let [g-x (fn [x] (* (/ (* 2 x)
                          (- (* 2.0 x) 1))
                       (/ (* 2 x)
                          (+ (* 2.0 x) 1))))]
    (prod 1 n g-x inc)))
(print (wallis-product 1000))
(newline)

; SUM and PRODUCT are both special cases of a more
; generic "accumulator" procedure
(defn accumulator [combiner identity-el a b f next]
  (defn c-accumulator [a b]
    (if (> a b) identity-el (combiner (f a) (c-accumulator (next a) b))))
  (c-accumulator a b))

(defn sum-2 [a b f next] (accumulator + 0 a b f next))
(print (sum-2 1 4 identity inc))
(newline)

(defn product-2 [a b f next] (accumulator * 1 a b f next))
(print (product-2 1 4 identity inc))
(newline)

; An "improved" accumulator is one that knows how to filter stuff
(defn filtered-accumulator [combiner identity-el a b f next filter]
  (defn c-accumulator [a b]
    (cond (> a b) identity-el
          (filter a) (combiner (f a) (c-accumulator (next a) b))
          :else (combiner identity-el (c-accumulator (next a) b))))
  (c-accumulator a b))

(defn prime? [n]
  (defn prime-iter [n factor]
    (cond (or (< n 2) (= n factor)) true
          (= (rem n factor) 0) false
          :else (prime-iter n (+ factor 1))))
  (prime-iter n 2))

(print (filtered-accumulator + 0 1 10 square inc prime?))
(newline)


; Binary search strategy for findings roots using the half-interval method.
; Assumptions: f(x) is continuous and f(a) < 0 < f(b)
(defn half-interval-root [f a-point b-point]
  (let [mid-point (avg a-point b-point)
        a-value (f a-point)
        b-value (f b-point)
        mid-value (f mid-point)]
    (if (or (pos? a-value) (neg? b-value))
      (throw (Exception. "Invalid interval"))
      (cond (close-enough? mid-value 0) mid-point
            (close-enough? a-point b-point) mid-point
            (pos? mid-value) (half-interval-root f a-point mid-point)
            (neg? mid-value) (half-interval-root f mid-point b-point)))))

; Finding value of Phi by finding the roots of sin(x) = 0
(print (half-interval-root sin 4.0 2.0))
(newline)

; The fixed point of a function is defined as the value such that
; f(x) = x, the fixed point can be found by continuously applying
; x = f(f(f...(x))...) until convergence.
(defn fixed-point [f guess]
  (if (close-enough? (f guess) guess) guess (fixed-point f (f guess))))

(print (fixed-point cos 1.0))
(newline)

; Avoids oscillations between successive approximations when finding
; midpoints by limiting the "width" of the guess on each iteration
(defn average-damp [f] (fn [x] (avg (f x) x)))

; From the fundamental theorem of calculus, the derivative of a function
; f at some point x is given by:
(defn deriv [f x]
  (let [dx 0.00001]
  (/ (- (f (+ x dx))
        (f x))
     dx)))

; Newton's method: If x |-> g(x) is differentiable, then a solution
; to g(x) = 0 is given by finding a fixed point for f(x) = x - g(x) / g'(x)
(defn newtons-transformation [g] (fn [x] (- x (/ (g x) (deriv g x)))))
(defn newtons-method [g guess] (fixed-point (newtons-transformation g) guess))

; Example of using Newton's method for finding the root of
; a cubic expression.
(defn make-cubic [a b c] (fn [x] (+ (cube x) (* a (square x)) (* b x) c)))
(print (newtons-method (make-cubic 1 2 3) 1.0))
(newline)

(defn double-it [f] (fn [x] (f (f x))))
(print ((double-it inc) 5))
(newline)

; (repeat-it f 3) -> f(f(f(x)))
(defn repeat-it [f n]
  (if (= n 1)
    (fn [x] (f x))
    (fn [x] (f ((repeat-it f (- n 1)) x)))))
(print ((repeat-it inc 4) 5))
(newline)

; Applies f to every element in seq
(defn every [seq f]
  (let [el (first seq) the-rest (rest seq)]
  (if (empty? seq) nil (cons (f el) (every the-rest f)))))
(print (every '(1 2 3) square))
(newline)

; In principle, all you need is lambda to define any computation,
; but how the heck can you do recursion with just lambda?
(print ((fn [f-x x] (f-x f-x x))
         (fn [factorial n]
           (if (zero? n) 1 (* n (factorial factorial (- n 1)))))
         5))
