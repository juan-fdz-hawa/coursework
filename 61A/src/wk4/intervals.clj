(ns wk4.intervals)

(defn make-interval [lower upper] (list lower upper))

(defn make-center-percent [center percent]
  (let [delta (+ 1 (/ percent 100))]
    (make-interval (- center delta) (+ center delta))))

(defn upper-bound [interval] (last interval))

(defn lower-bound [interval] (first interval))

(defn spans-zero? [interval] (or (<= (lower-bound interval) 0)
                                 (<= (upper-bound interval) 0)))

(defn center [interval] (/ (+ (upper-bound interval) (lower-bound interval)) 2))

(defn percent [interval] (/ (- (upper-bound interval) (lower-bound interval)) 2))

(defn reciprocal [interval] (make-interval (/ 1.0 (upper-bound interval))
                                           (/ 1.0 (lower-bound interval))))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mult-scalar [scalar interval]
  (make-interval (* scalar (lower-bound interval))
                 (* scalar (upper-bound interval))))

(defn mult-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4) (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (if (spans-zero? y)
    (throw (Exception. "Dividend can't span zero"))
    (mult-interval x (reciprocal y))))

(defn sub-interval [x y]
  (add-interval x (mult-scalar -1 y)))