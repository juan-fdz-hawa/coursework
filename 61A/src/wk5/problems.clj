(ns wk5.problems)

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
;corresponding product for the right side) and if each of the submobiles hanging
;off its branches is balanced.
(defn is-balanced? [mobile])