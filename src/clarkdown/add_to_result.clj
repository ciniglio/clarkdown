(ns clarkdown.add-to-result)

(declare add-char-to-result)
(defmulti append-char (fn [a b] (class a)))
(defmethod append-char String
  [string char]
  (str string char))
(defmethod append-char (class {})
  [mymap char]
  (update-in mymap [:text] #(add-char-to-result % char)))

(defn add-char-to-result
  [result char]
  (conj (pop result) (append-char (last result) char)))
