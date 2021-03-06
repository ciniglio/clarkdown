(ns clarkdown.add-to-result-test
  (:require [clojure.test :refer :all]
            [clarkdown.add-to-result :refer :all]))

(deftest append-char-string
  (let [text "abc"
        char "d"]
    (is (= "abcd"
           (append-char text char)))))

(deftest append-lit-char-string
  (let [text "abc"
        char \d]
    (is (= "abcd"
           (append-char text char)))))

(deftest add-char-to-result-strings
  (let [result ["this" "i"]
        char \s]
    (is (= ["this" "is"]
           (add-char-to-result result char)))))

(deftest add-char-to-result-simplemap
  (let [result [{:text ["thi"]}]
        char \s]
    (is (= [{:text ["this"]}]
           (add-char-to-result result char)))))

(deftest add-char-to-result-map-1
  (let [result ["this is" {:text ["a tes"]}]
        char \t]
    (is (= ["this is" {:text ["a test"]}]
           (add-char-to-result result char)))))

(deftest add-char-to-result-map-2
  (let [result ["this is" {:text ["a tes"] :property :arbitrary}]
        char \t]
    (is (= ["this is" {:text ["a test"] :property :arbitrary}]
           (add-char-to-result result char)))))

(deftest add-char-to-nested-result-map
  (let [result ["this is" {:text ["a test " {:text ["strin"] :property :mine}] :property :arbitrary}]
        char \g]
    (is (= ["this is" {:text ["a test " {:text ["string"] :property :mine}] :property :arbitrary}]
           (add-char-to-result result char)))))

(deftest add-char-to-nested-result-map-1
  (let [result ["this is" {:text ["a test " {:text ["string"] :property :mine} "to"] :property :arbitrary}]
        char \o]
    (is (= ["this is" {:text ["a test " {:text ["string"] :property :mine} "too"] :property :arbitrary}]
           (add-char-to-result result char)))))
