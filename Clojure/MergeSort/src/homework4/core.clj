(ns homework4.core)
;Programmer: Damian Zylski
;System:     Windows 7 - Visual Studio Code
;Date        04/20/2020
;Project:    Homework 4
;Purpose:    to code merge sort. Or try to
;
;*TASK 1***********************************************************************************************

;;Helper function: inserts the given item into the right place
;; in the given sorted collection.
(defn insert [scoll item]
  (if (empty? scoll) (list item)
      (if (< (first scoll) item)
        (conj (insert (rest scoll) item) (first scoll))
        (conj (seq scoll) item))))

;;Insertion sort
(defn insertion-sort [coll]
  (if (or (empty? coll) (empty? (rest coll))) coll
      (insert (insertion-sort (rest coll)) (first coll))))

(insertion-sort [9 1 5 3 777 0 39])

(list)

;*TASK 2***********************************************************************************************

;Split
(defn split [coll] (let [half (quot (count coll) 2)]
                     (if (<= (count coll) 1) coll
                          [ (vec (subvec coll 0 half )) (vec (subvec coll half ))])
                     )
  )

(split [3 4 7 2])

(split [3 4 7 2 1])

;Merge
(defn merge [coll1 coll2] (cond (and (empty? coll1) (empty? coll2)) [] ;if both colls are empty, end recursion
                                (empty? coll1) (reduce conj [] coll2 ) ;if first coll is empty
                                (empty? coll2) (reduce conj [] coll1) ;if second coll is empty
                                (>= (last coll1) (last coll2))  ;if first of first coll greater than first of second coll
                                (conj (merge (subvec coll1 0 (- (count coll1) 1) ) coll2) (last coll1) )
                                (>= (last coll2) (last coll1))  ;if first of second coll greater than first of first coll
                                (conj (merge coll1 (subvec coll2 0 (- (count coll2) 1))) (last coll2) )
                                )
  )
 
(merge [4 7 18 40 99] [3 6 9 12 24 36 50])

(merge [2] [1])

(merge [2] [])

;Merge sort
(defn merge-sort [coll] (let [len (count coll) new-coll []]
                          (cond (<= len 1) coll
                                ;below are some things I tried before I figured it out. The first one returned a single num
                                ;:else (merge-sort (last (split coll))) )
                                ;:else (merge-sort (merge (first (split coll)) (last (split coll)))) 
                                
                                ;looking at your insertion sort example is what helped me solve this
                                :else (merge (merge-sort (first (split coll))) (merge-sort (last (split coll)))) )
                          
                          )
  )

(merge-sort [9 3 14 7 5 11 2])

(merge-sort [777 333 0 111 444 4444 222 777 222])

