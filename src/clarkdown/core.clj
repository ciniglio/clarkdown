(ns clarkdown.core
  (:require [clarkdown.add-to-result :refer :all])) 

(declare em)
(declare in-normal-text)

(defn em
  [{:keys [remainder contents] :as last-result}]
  (let [next-char (first remainder)]
    (cond
     (= next-char \*) {:contents contents
                       :remainder (rest remainder)}
     :else (em {:contents (add-char-to-result contents next-char)
                :remainder (rest remainder)}))))

(defn next-result
  [{:keys [remainder contents] :as last-result}]
  (cond
   (= (first remainder) \*) (em {:remainder (rest remainder)
                                 :contents (conj contents {:text [""]
                                                           :type "em"})})
   (empty? remainder) last-result
   :else {:contents (add-char-to-result contents (first remainder))
          :remainder (rest remainder)}))

(defn in-normal-text
  [result]
  (loop [last-result result]
    (if (empty? (:remainder last-result)) 
      (:contents last-result)
      (recur (next-result last-result)))))

(defn parse
  [string]
  (in-normal-text {:contents [""]
                   :remainder string}))

