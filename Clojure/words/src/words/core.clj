(ns words.core)
;Programmer: Damian Zylski
;System:     Windows 7 - Visual Studio Code
;Date        08/05/2020
;Project:    Words
;
;Purpose: To read in a text file full of words and output it to the user. Then save the words
;to a different file

;read the words from file
(def word-file (slurp "words_no_duplicates.txt"))

word-file

;save those words to a vector
(def word-list (vec (.split word-file "\r\n")))

word-list

;we can access the words like this. This is by far the easiest out of any languages to do this.
(get word-list 4580)

(defn format-words [w] 
  (format "%s\n" w))

;write the words to a file. This just writes a big string
;UPDATE: using format-words above makes this work like the with-open example
(spit "DATA.DAT" (reduce str (map format-words word-list) ))

;This writes with new lines for each word
(with-open [wrtr (clojure.java.io/writer "BACON.DAT")]
  (doseq [i word-list]
    (.write wrtr (str i "\n"))))

nil