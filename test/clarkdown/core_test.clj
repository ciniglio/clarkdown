(ns clarkdown.core-test
  (:require [clojure.test :refer :all]
            [clarkdown.core :refer :all]))

(deftest parse-normal
  (let [string "this test again"]
    (is (= [string] (parse string)))))

(deftest parse-em-1
  (let [string "this *is* a test"]
    (is (= ["this " {:text ["is"] :type "em"} " a test"] (parse string)))))

(deftest parse-em-2
  (let [string "*is*"]
    (is (= ["" {:text ["is"] :type "em"} ""] (parse string)))))

(deftest parse-em-3
  (let [string "this *is* a *test*"]
    (is (= ["this " {:text ["is"] :type "em"} " a " {:text ["test"] :type "em"} ""] (parse string)))))


