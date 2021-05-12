(ns homework3.core)
;Programmer: Damian Zylski
;System:     Windows 7 - Visual Studio Code
;Date        04/05/2020
;Project:    Homework 3
;Purpose:    to finish a Shut The Box game. Or try to.

;Keeping the things from lab 13 and 14 and altering any as needed

;; Opens up a window in the upper-left corner of the screen.
;; All the ask and answer pop-ups will appear on top of this window.
;; You can position your Light Table window so that you can see the pop-ups.
(def parent-frame
  (doto (new javax.swing.JFrame)
    (.setDefaultCloseOperation javax.swing.JFrame/DISPOSE_ON_CLOSE)
    (.pack)
    (.setLocation 0 0)
    (.setVisible true)))

;; Pops up a dialog box with a prompt message and an input box.
;; The return value is what the user enters into the input box.
(defn ask-user [prompt]
  (javax.swing.JOptionPane/showInputDialog parent-frame prompt ""))

;; Pops up a dialog box with a message.
;; The return value is always nil.
(defn answer-user [message]
  (javax.swing.JOptionPane/showMessageDialog parent-frame message))

;The box - changing it to a vector
(def box ["closed" "open" "open" "open" "open" "open" "open" "open" "open"])
box
;A test win box
(def win-box ["closed" "closed" "closed" "closed" "closed" "closed" "closed" "closed" "closed"])
;A die roll
(defn dieroll [] (+ (rand-int 6) 1))
(dieroll)
;a dice roll, in a map
(def diceroll {:die1 (dieroll), :die2 (dieroll)})
diceroll
;A sample state
(def sample-state [(assoc box 1 "closed" 3 "closed" 6 "closed")
                   (assoc diceroll :die1 5 :die2 3)])
sample-state

;4.
;Possible moves
(defn possible-moves [box roll] (let [result (+ (get roll :die1) (get roll :die2) -1)
                                      move-set #{}]
                                  ;(println box)
                                  ;(println result)
                                  ;I think I covered too much ground with this first condition
                                  (cond (> (- result 6) 0) ;Check if dice result - 6 is above 0
                                        ;then check if - 6 spot in box is open
                                        (if (= (get box (- result 6)) "open")
                                          ;if so, also check if result spot is open
                                          (if (= (get box result) "open")
                                            ;conj both sets if they are both open
                                            (conj move-set (assoc box result "closed")
                                                  (assoc box (- result 6) "closed"))
                                            ;Otherwise just conj the minus 6 set
                                            (conj move-set (assoc box (- result 6) "closed")))
                                          ;if - 6 set is closed, check the result if open
                                          (if (= (get box result) "open")
                                            ;if so, conj the result spot set
                                            (conj move-set (assoc box result "closed"))
                                            ;otherwise return an empty set
                                            move-set))

                                        ;since we extensively tested both cases in the last cond
                                        ;we only need to check if result spot is open in this one
                                        (= (get box result) "open")
                                        ;conj result set if it's open
                                        (conj move-set (assoc box result "closed"))

                                        ;Else, we return the empty set
                                        :else move-set)))

(defn possible-moves2 [box roll] (let [result (+ (get roll :die1) (get roll :die2) -1)
                                       move-set #{}]
                                  ;(println box)
                                  ;(println result)
                                   (cond (and (> (- result 6) 0) (= (get box (- result 6)) "open") (= (get box result) "open"))
                                         (conj move-set (assoc box result "closed") (assoc box (- result 6) "closed"))
                                         (and (> (- result 6) 0) (= (get box (- result 6)) "open"))
                                         (conj move-set (assoc box (- result 6) "closed"))
                                         (= (get box result) "open")
                                         (conj move-set (assoc box result "closed"))
                                         :else
                                         move-set)))

(possible-moves box diceroll) ; seems to work when tested



;1 Dice roll is defined above which stores a dice roll
diceroll
;I guess I should make a function that returns a random dice roll
(defn roll-dice [die1 die2] {:die1 (die1), :die2 (die2)})
(roll-dice dieroll dieroll)

;(possible-moves2 box roll-dice)

;test possible moves with roll-dice
(possible-moves box (roll-dice dieroll dieroll))
(possible-moves win-box (roll-dice dieroll dieroll))

;2 A defualt type box is already defined above
(def start-box box)
start-box
;3. Test the game out
(possible-moves start-box (roll-dice dieroll dieroll))
(possible-moves (first #{["closed" "open" "open" "open" "open" "closed" "open" "open" "open"]})
                (roll-dice dieroll dieroll))
;This seems to work but I'm guessing I should have converted the map to a set?
(get box 1)


;checks if empty first to prevent null pointer error
;Then checks if this is the original box vector and sends it to possible-moves
;then we get back a set and we check if its a loss or a win or send it back to possible moves
(defn play-game [box] (do (cond
                            ;check for empty set which is a loss
                            (empty? box) "Lost"
                            ;check for initial box if it's a vector
                            (vector? box) (recur (possible-moves box
                                                                 (roll-dice dieroll dieroll)))
                            ;Check if set has more than one possible move
                            (= (count box) 2)
                            (if (= (rand-int 2) 0)
                              (recur (possible-moves (first box) ;choose first set if 0
                                                     (roll-dice dieroll dieroll)))
                              (recur (possible-moves (last box) ;choose second set if 1
                                                     (roll-dice dieroll dieroll))))
                            ;check for a win if set doesn't contain open spaces
                            (not (.contains (first box) "open")) "Win"
                            ;if it does contain open spaces, recur
                            (.contains (first box) "open") (recur (possible-moves (first box)
                                                                                  (roll-dice dieroll dieroll))))))
;**Task 1 and 2**************************************************************
;moving win? up here
(defn win? [result] (= result "Win"))

;defining a probability function outside of playgame.
(defn probability [box-state] (let [games (map (fn [x] (play-game box-state)) (range 10000))
                         wins (int (count (filter win? games))) num-games (int (count games))]
                     ;(println wins)
                     ;(println num-games)
                     (double (/ wins num-games))))
(probability start-box)
(probability ["closed" "closed" "closed" "closed" "closed" "closed" "closed" "closed" "closed"])

;**Task 3**********************************************************************************************************************
;Using probability, we need to make a lookup table for probability values. We'll use the start box and go from there
(def test-map {["closed" "open" "open" "open" "open" "closed" "open" "open" "open"] 0.0024
               ["closed" "open" "open" "open" "open" "open" "open" "open" "open"] 0.0039})
;Maybe we can use a map to store the probability values
test-map 

(get test-map ["closed" "open" "open" "open" "open" "closed" "open" "open" "open"]) ;seems to work

;this idea failed
(defn calc-probs [i k j box box-map] 
  (let [p (probability box)
        limit (count box)]
    (println "i: " i)
    (println "j: " j)
    (println box-map)
    ;(answer-user (str "i: " i " k: " k  " j: " j))
          ;check if i goes over box length and return the box-map if so
    (cond (= i 10) box-map
          ;check if j goes over box length, if so inc i
          (and (= k 9) (= i 9)) (recur (inc i) 0 0 box box-map)
          (= k 9) (recur (inc i) 0 0 (assoc start-box (inc i) "closed") box-map)
          (= j 9) (recur i (inc k) (inc k) (assoc (assoc start-box (inc k) "closed") i "closed") box-map)
          ;check if current box already exists. if so don't add and inc j
          ;(contains? box-map box) (recur i (inc j) (assoc box (inc j) "closed") box-map)
          ;other wise we're going to add to the box map and inc j
          :else (recur i k (inc j) (assoc box (inc j) "closed") (conj box-map {box p})))
    ))

;I guess this adds a member
(conj ["closed" "open" "open" "open" "open" "open" "open" "open" "open"] "closed")
;I guess this combines stuff
(into ["quack"] ["duck"])

;the professor's example
 (defn conj-closed [box] (conj box "closed"))
(defn conj-open [box] (conj box "open"))
(defn boxes [n]
  (if (zero? n) [[]]
      ;the next part confuses me. I can't picture in my mind how this works. Could not have figured this out myself. 
      (let [prev-boxes (boxes (dec n))]
        (into (map conj-closed prev-boxes) (map conj-open prev-boxes)))))

(boxes 2) 
(boxes 3)
(count (boxes 9)) ;wow that's actually 512. The solution was so simple but I can't wrap my head around it.

;now the hard part. To make a function to calculate all the 512 state probability values
;looks like I can map the probability function
(def probability-table (map probability (boxes 9)))

probability-table

;looks like 512. But what's in here?
(count probability-table)

;I guess this is just the probabilities
(nth probability-table 500)

;I had to refer to the clojure cheat sheet but I think zipmap will work here!
(def probability-map (zipmap (boxes 9) probability-table))

probability-map

;oh wow it works. Now I need to add it into the game
(get probability-map ["closed" "open" "open" "open" "open" "closed" "open" "open" "open"])

;implementing gui stuff. Implementing task 3
(defn play-game-user [box] (let [dice (roll-dice dieroll dieroll)] ;the dice roll
                             (answer-user (str "Current Box: " box
                                               "\nCurrent Dice Roll: " dice))
                             (cond
                            ;check for empty set which is a loss
                               (empty? box) (do (answer-user "You Lose!") "Lost")
                            ;check for initial box if it's a vector
                               (vector? box) (recur (possible-moves box
                                                                    dice))
                            ;Check if set has more than one possible move
                               (= (count box) 2)
                               ;The user gets to choose in this case. Answer must be 1 or 2
                               (loop [choice (ask-user (str "Choose one of the two box sets:\n1. "
                                                            (first box) "\n2. " (last box)
                                                            "\nBox 1 win probability: "
                                                            (format "%.2f" (* (get probability-map (first box)) 100))
                                                            "%\nBox 2 win probability: "
                                                            (format "%.2f" (* (get probability-map (last box)) 100))
                                                            "%\nInput 1 for first, 2 for the second"))]
                                 (cond (or (= choice "1") (= choice "2")) 
                                       (if (= choice "1")
                                         ;I used to recur to possible moves but that just recurred to the loop
                                         ;so I had to run play-game with the right box instead
                                         (play-game-user (first box)) ;choose first set if 1
                                         (play-game-user (last box))) ;choose second set if 2)
                                       ;otherwise ask the user again if bad input
                                       :else (recur (ask-user (str "Choose one of the two box sets:\n1. "
                                                                   (first box) "\n2. " (last box)
                                                                   "\nBox 1 win probability: "
                                                                   (format "%.2f" (* (get probability-map (first box)) 100))
                                                                   "%\nBox 2 win probability: "
                                                                   (format "%.2f" (* (get probability-map (last box)) 100))
                                                                   "%\nInput 1 for first, 2 for the second")))
                                       )
                                 )
                            ;check for a win if set doesn't contain open spaces
                               (not (.contains (first box) "open")) (do (answer-user "You Win!") "Win")
                            ;if it does contain open spaces, recur
                               (.contains (first box) "open") (recur (possible-moves (first box)
                                                                                     dice)))))

;playing the game seems to work. just the box is sent in
(play-game-user start-box)

;need to make a win function like in lab 10
;(defn win? [result] (= result "Win"))
(win? (play-game start-box))

;now we need to play several games!
(def games
  (map (fn [box] (play-game start-box)) (range 10000)))
games

;then we count how many games were wins. Wow. The odds of winning this seems very low.
(count (filter win? games))
(count games)

;Calculate the probability of winning
(def probability-of-winning
  (let [wins (int (count (filter win? games))) games (int (count games))]
    (double
     (/ wins games))))
probability-of-winning ;I spent 20 minutes trying to figure out why this wasn't working
;then i realized it's because I had parenthesis around this. 

;now we need to make the simulate function for 1 million games
(defn simulate [n] (let [games (map (fn [x] (play-game start-box)) (range n))
                         wins (int (count (filter win? games))) num-games (int (count games))]
                     (println wins)
                     (println num-games)
                     (double (/ wins num-games))))
(simulate 100) ;After waiting like 5 minutes, it output 0.004144!

