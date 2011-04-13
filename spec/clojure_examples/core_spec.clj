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

(describe "Run length encoding"
          (it "encodes stuff"
              (should= '([1 :a]) (rle [:a]))
              (should= '([2 :a]) (rle [:a :a]))
              (should= '([2 :a] [4 6]) (rle [:a :a 6 6 6 6]))
              (should= '([2 :a] [2 6] [1 :a] [3 "x"]) (rle [:a :a 6 6 :a "x" "x" "x"]))
              (should= '([4 a] [1 b] [2 c] [2 a] [1 d] [4 e]) (rle '[a a a a b c c a a d e e e e]))))

(describe "Run length decoding"
          (it "decodes stuff"
              (should= [:a] (rld '([1 :a])))
              (should= [:a :a] (rld '([2 :a])))
              (should= [:a :a 6 6 6 6] (rld '([2 :a] [4 6])))
              (should= [:a :a 6 6 :a "x" "x" "x"] (rld '([2 :a] [2 6] [1 :a] [3 "x"])))
              (should= '[a a a a b c c a a d e e e e] (rld '([4 a] [1 b] [2 c] [2 a] [1 d] [4 e])))))

(describe "My Reverse"
          (it "reverses a list"
              (should= [9 8 7 6 5 4 3 2 1] (my-reverse '(1 2 3 4 5 6 7 8 9))))

          (it "reverses a vector"
              (should= [9 8 7 6 5 4 3 2 1] (my-reverse [1 2 3 4 5 6 7 8 9]))))

(describe "Formatting integers with commas"
          (it "works for numbers less than 1000"
              (should= "900" (commas 900)))
          
          (it "works for 1000"
              (should= "1,000" (commas 1000)))
          
          (it "works in the millions"
              (should= "1,234,567" (commas 1234567)))
          
          (it "works for big ones"
              (should= "1,231,231,231,231,231,231,231,231,239" (commas 1231231231231231231231231239))))

(describe "Neighbors"
          (it "find the neighbors for a coordinate pair in a 3x3 matrix"
              (should= [[1 0] [0 1]] (neighbors 3 [0 0]))
              (should= [[1 1] [0 0] [0 2]] (neighbors 3 [0 1]))
              (should= [[0 1] [2 1] [1 0] [1 2]] (neighbors 3 [1 1])))
          )

(describe "locating the positional index"
          (it "works for vectors"
              (should= [5] (pos (partial = 3) [:a 1 :b 2 :c 3 :d 4]))
              (should= [] (pos (partial = :foo) [:a 1 :b 2 :c 3 :d 4]))
              (should= [1 3 5] (pos (partial = 3) [:a 3 :b 3 :c 3 :d 4])))

          (it "works for maps"
              (should= [:c] (pos (partial = 3) {:a 1 :b 2 :c 3 :d 4}))
              (should= [:a :b :c] (pos (partial = 3) {:a 3 :b 3 :c 3 :d 4})))

          (it "works for lists"
              (should= [5] (pos (partial = 3) '(:a 1 :b 2 :c 3 :d 4))))

          (it "works for strings"
              (should= [13] (pos (partial = \3) ":a 1 :b 2 :c 3 :d 4")))

          (it "works with functions other than equality"
              (should= [:c :d] (pos #{3 4} {:a 1 :b 2 :c 3 :d 4}))
              (should= [0 2] (pos even? [2 3 6 7])))

          )
