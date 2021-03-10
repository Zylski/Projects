(ns peoplework.core)
;Programmer: Damian Zylski
;System:     Windows 7 - Visual Studio Code
;Date        08/24/2020
;Project:    PeopleWork
;
;Purpose: To read in student objects from a file and output them to the user

(import java.util.Scanner)
(import java.io.FileInputStream)
(import java.util.Comparator)

;define scanner for reading in students from file
(def fin 
  (new Scanner 
       (try (new FileInputStream "Student.dat") ;open student data file here
            (catch java.io.FileNotFoundException e (println e)) )
       )
  )

;read in students from file into a vector of maps
(def student-list  
    (loop [students []]
      (if (not (. fin hasNextInt)) ;loop until last line
        students ;return students after last line
        (recur (conj students ;create a map for each student with key fields and values
                     (zipmap [:Student_ID :Major :Level :Name ]
                             (first (conj [] (let [student [(. fin nextInt) (. fin next) (. fin next) (. fin nextLine)]]
                                               student)))))))
      ))

(first student-list)

;Sort the students by id number
(def sorted-students (sort-by :Student_ID student-list))

;print the students out to the user
(defn print-students [i n students] 
(if (>= i n) "" (do (println (nth students i) ) (print-students (inc i) n students))))

(print-students 0 (count sorted-students) sorted-students)

nil