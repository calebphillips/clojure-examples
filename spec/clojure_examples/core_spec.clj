(ns clojure-examples.core-spec
    (:use [speclj.core]
          [clojure-examples.core]))

(defn first-10-fibo-shoulds [f]
      (do
        (should= 0 (f 0))
        (should= 1 (f 1))
        (should= 1 (f 2))
        (should= 2 (f 3))
        (should= 3 (f 4))
        (should= 5 (f 5))
        (should= 8 (f 6))
        (should= 13 (f 7))
        (should= 21 (f 8))
        (should= 34 (f 9))
        (should= 55 (f 10))))

(describe "Sequence Fibonacci"
          (it "returns the right value from the seq"
              (first-10-fibo-shoulds fibo))
          
          (it "doesn't blow the stack"
              (should (> (fibo 1000) 1E200))))

(describe "Recur Fibonacci"
          (it "returns the right value from the seq"
              (first-10-fibo-shoulds recur-fibo))
          
          (it "doesn't blow the stack"
              (should (> (recur-fibo 1000) 1E200))))

