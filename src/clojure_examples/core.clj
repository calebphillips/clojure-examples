(ns clojure-examples.core)

; source: Programming Clojure
; interesting: lazy sequences instead of recursion, destructuring (iterate)
(defn fibo [n] 
      (nth (map first (iterate (fn [[a b]] [b (+ b a)]) [0 1])) n))

; source: Programming Clojure
; interesting: tail recursion with recur, letfn for local function binding
(defn recur-fibo [n] 
      (letfn [(fib
                [current next n] 
                (if (zero? n)
                  current
                  (recur next (+ current next) (dec n))))] 
             (fib 0 1 n)))


