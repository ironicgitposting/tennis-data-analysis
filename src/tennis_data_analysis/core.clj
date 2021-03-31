(ns tennis-data-analysis.core
  (:gen-class))

(require '[clojure.java.io :as io])
(require '[clojure.data.csv :as csv])


(def data-source
  "src/data.csv")

(def first-row
  (with-open [r (io/reader data-source)]
    (first (csv/read-csv r))))

(defn count-nb-of-rows
  []
  (with-open [r (io/reader data-source)]
    (count (csv/read-csv r))))

;; extract the name of the winner of each of the first five matches
;; remember lazy sequences are not evaluated until they are consumed
(defn extract-first-n-winners
  ;; the winner_name field is number 7 (starting 0)
  [n]
  (with-open [r (io/reader data-source)]
    (->> (csv/read-csv r)
         (map #(nth % 7))                                   ;; extract 8th field
         (take n)                                           ;; take n element
         (doall))))                                         ;; force side effects (whole seq stored in memory....)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
 (count-nb-of-rows))


