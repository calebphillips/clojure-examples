(ns clojure-examples.core
    (:use [clojure.string :only [join]]))

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

; me 
; interesting: I wondered if I could do it off the top of my head with existing functions.
;               Seems rough but it works.  Partial for map functions that need more than 1 argument.
;               Maybe able to remove some intermediate stringiness
(defn commas [n] (apply str (reverse (join "," (map (partial apply str) (partition 3 3 '() (reverse (str n))))))))

; source: Joy of Clojure
; interesting: < with multiple args as a predicate (args "are in monotonically increasing order")
;             map with seq of seqs maps corresponding elements together
(defn neighbors 
      ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]] size yx)) 
      ([deltas size yx]
       (filter (fn [new-yx] 
                   (every? #(< -1 % size) new-yx))
               (map #(vec (map + yx %)) deltas))))

; source: 
; interesting: apply
(apply hash-map ["Java" "Gosling" "Ruby" "Matz" "Clojure" "Hickey"])

