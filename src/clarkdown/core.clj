(ns clarkdown.core
  (:require [clarkdown.add-to-result :refer :all])) 

(declare bold)
(declare in-normal-text)

(defn one-star
  [next-char
   result
   remainder]
  (bold next-char (conj result {:type "bold" :text [""]}) remainder))

(defn bold
  [next-char
   result
   remainder]
  (cond
   (= next-char \*) (in-normal-text (first remainder) (conj result "") (rest remainder))
   :else (bold (first remainder) (add-char-to-result result next-char) (rest remainder))))

(defn in-normal-text
  [next-char
   result
   remainder]
  (cond
   (= next-char \*) (one-star (first remainder) result (rest remainder))
   (= '() next-char) (result)
   :else (in-normal-text 
          (first remainder) 
          (add-char-to-result result next-char) 
          (rest remainder))))


