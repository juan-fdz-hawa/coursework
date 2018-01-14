(ns wk5.problems)

; ----------------

;A binary mobile consists of two branches, a left branch and a right branch.
;Each branch is a rod of a certain length, from which hangs either a weight or
;another binary mobile.
(defn make-mobile [left-branch right-branch]
  (list left-branch right-branch))

;A branch is constructed from a length (which must be a number)
;together with a structure, which may be either a number
;(representing a simple weight) or another mobile
(defn make-branch [length structure]
  (list length structure))

(defn is-mobile? [structure]
  (list? structure))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (second mobile))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (second branch))

(defn total-weight [mobile]
  (let [left-branch-struct  (branch-structure (left-branch mobile))
        right-branch-struct (branch-structure (right-branch mobile))]

    (cond (and (is-mobile? left-branch-struct)
               (is-mobile? right-branch-struct))
          (+ (total-weight left-branch-struct) (total-weight right-branch-struct))

          (is-mobile? left-branch-struct)
          (+ (total-weight left-branch-struct) right-branch-struct)

          (is-mobile? right-branch-struct)
          (+ left-branch-struct (total-weight right-branch-struct))

          :else (+ left-branch-struct right-branch-struct))))

;A mobile is said to be balanced if the torque applied by its top-left branch
;is equal to that applied by its top-right branch (that is, if the length of the
;left rod multiplied by the weight hanging from that rod is equal to the
;corresponding product for the right side).
(defn is-balanced? [mobile]

  (defn torque [branch]
    (* (branch-length branch)
       (if (is-mobile? (branch-structure branch))
         (total-weight branch)
         (branch-structure branch))))

  (defn structure-is-balanced? [mobile]
      (defn checker [structure]
        (if (is-mobile? structure) structure-is-balanced? (fn [_] true)))

      (and ((checker (branch-structure (left-branch mobile)))
             (branch-structure (left-branch mobile)))
           ((checker (branch-structure (right-branch mobile)))
             (branch-structure (right-branch mobile)))))

  (and (structure-is-balanced? (left-branch mobile))
       (structure-is-balanced? (right-branch mobile))
       (= (torque (left-branch mobile)) (torque (right-branch mobile)))))

; -----------

(defn make-tree [datum children]
  (list datum children))

(defn datum [node]
  (first node))

(defn children [node]
  (second node))

(defn tree-map [fn tree]
  (make-tree (fn (datum tree)) (map tree-map (children tree))))

; -----------


(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

; Given s = ((1 2 3) (4 5 6) (7 8 9) (10 11 12))
; (accumulate-n + 0 s) -> (22 26 30)
(defn accumulate-n [op init nested-seq]
  (if (empty? (first nested-seq))
    nil
    (cons (accumulate op init (map first nested-seq))
          (accumulate-n op init (map rest nested-seq)))))

; ---------

(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map (fn [m-i] (dot-product m-i v)) m))

(defn transpose [m]
  (accumulate-n cons '() m))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map (fn [row-i] (matrix-*-vector cols row-i)) m)))