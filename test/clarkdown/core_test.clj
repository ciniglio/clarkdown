(ns clarkdown.core-test
  (:require [clojure.test :refer :all]
            [clarkdown.core :refer :all]))


(deftest all-normal
  (let [next-char \s
        result ["thi"]
        remainder ""]
    (is (= ["this"]
           (in-normal-text next-char result remainder)))))

(deftest all-normal-1
  (let [next-char \s
        result ["first" "thi"]
        remainder ""]
    (is (= ["first" "this"]
           (in-normal-text next-char result remainder)))))

(deftest all-normal-2
  (let [next-char \t
        result [""]
        remainder "his is a test"]
    (is (= ["this is a test"]
           (in-normal-text next-char result remainder)))))

(deftest parse-normal
  (let [string "this test again"]
    (is (= [string] (parse string)))))

(deftest parse-bold-1
  (let [string "this *is* a test"]
    (is (= ["this " {:text ["is"] :type "bold"} " a test"] (parse string)))))

(deftest parse-bold-2
  (let [string "*is*"]
    (is (= ["" {:text ["is"] :type "bold"} ""] (parse string)))))

(deftest parse-bold-3
  (let [string "this *is* a *test*"]
    (is (= ["this " {:text ["is"] :type "bold"} " a " {:text ["test"] :type "bold"} ""] (parse string)))))


