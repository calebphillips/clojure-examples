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

; source: http://blog.fogus.me/2011/03/09/recursion-is-a-low-level-operation/
; interesting: partition-by, juxt, mapcat higher-order functions with partial and comp
(def pack (partial partition-by identity))

(def rle (comp (partial map (juxt count first)) pack))

(def rld (partial mapcat (partial apply repeat)))

; source: clojure source.  I think I got curious while reading OnLisp
; interesting: using reduce with a list accumulator, conj to the front of a list (not vector), simple algorithm
(defn my-reverse [coll]
     (reduce conj '() coll))
