(ns wk4.problems-test,
  (:require [clojure.test :refer :all]
            [wk4.problems :as hw4]))

(deftest last-pair-tests
  (is (= 4 (hw4/last-pair (list 1 2 3 4)))))

(deftest same-parity-tests
  (is (= '(1 3 5 7) (hw4/same-parity 1 2 3 4 5 6 7)))
  (is (= '(2 4 6) (hw4/same-parity 2 3 4 5 6 7))))

(deftest substitute-tests
  (is (= '(("lead" "axe") ("bass" "axe") ("rhythm" "axe") "drums")
         (hw4/substitute '(("lead" "guitar")
                           ("bass" "guitar")
                           ("rhythm" "guitar")
                           "drums")
                     "guitar" "axe")))
  )

(run-tests)