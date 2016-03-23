; Aldo Anaya

;(println "Lexeme    Token")
(def is-num (fn [s]
              (if (> s 47)
                (if (< s 58)
                  (do                                       ;(println (char s) "        INT")
                    (def lexemes (conj lexemes (char s)))
                    (def tokens (conj tokens "INT ")))
                  )
                )
              )
  )
(def is-char (fn [s]
               (if (> s 64)
                 (if (< s 91)
                   (do                                      ;(println (char s) "        ID")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "ID ")))
                   )
                 )
               (if (> s 96)
                 (if (< s 123)
                   (do                                      ;(println (char s) "        ID")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "ID ")))
                   )
                 )
               )
  )

(def is-symbol (fn [s]
                 (if (== s 40)
                   (do                                      ;(println (char s) "        LPAREN")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "LPAREN "))))
                 (if (== s 41)
                   (do                                      ;(println (char s) "        RPAREN")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "RPAREN "))))
                 (if (== s 42)
                   (do                                      ;(println (char s) "        MULT-OP")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "MULT-OP "))))
                 (if (== s 43)
                   (do                                      ;(println (char s) "        ADD-OP")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "ADD-OP "))))
                 (if (== s 45)
                   (do                                      ;(println (char s) "        MINUS-OP")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "MINUS-OP "))))
                 (if (== s 47)
                   (do                                      ;(println (char s) "        DIV-OP")
                     (def lexemes (conj lexemes (char s)))
                     (def tokens (conj tokens "DIV-OP "))))
                 )
  )

(def run-all (fn [s]
               (is-char s)
               (is-num s)
               (is-symbol s)))

(def check-first-last (fn [s]
                        (def length (dec (count s)) )

                        (if (not= (first s) "FACTOR")                  ; make sure first token is accepted
                          (if (not= (first s) "LPAREN")
                              (def result "REJECT")
                              )
                          )

                        (if (not= (nth s length) "FACTOR")             ; make sure last token is accepted
                          (if (not= (nth s length) "RPAREN")
                            (do
                              (def result "REJECT")
                              )
                            )
                            )
                        (if (= (nth s length) "RPAREN")
                          (def paren-counter (dec paren-counter)))
                   ))


(def check-Paren (fn [s]
                   (dotimes [token (dec (count s))]                                         ; now check rest of string
                     ;(def paren-counter 0)
                     (if (= (nth s token) "LPAREN")
                       (do
                         (def paren-counter (inc paren-counter))
                         (if (= (nth s (inc token)) "ADD-OP")
                           (def result "REJECT")
                           )
                         (if (= (nth s (inc token)) "DIV-OP")
                           (def result "REJECT")
                           )
                         (if (= (nth s (inc token)) "MULT-OP")
                           (def result "REJECT")
                           )
                         (if (= (nth s (inc token)) "MINUS-OP")
                           (def result "REJECT")
                           )))
                       )
                     )

                   )
(def check-string (fn [s]
                    (dotimes [token (dec (count s))]

                 (if (= (nth s token) "FACTOR")
                   (do
                     (if (= (nth s (inc token)) "FACTOR")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "LPAREN")
                       (def result "REJECT "))
                     ))

                 (if (= (nth s token) "ADD-OP")
                   (do
                     (if (= (nth s (inc token)) "ADD-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MINUS-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MULT-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "DIV-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "RPAREN")
                       (def result "REJECT "))
                     )
                   )
                 (if (= (nth s token) "MINUS-OP")
                   (do
                     (if (= (nth s (inc token)) "ADD-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MINUS-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MULT-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "DIV-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "RPAREN")
                       (def result "REJECT ")                    )
                   ))
                 (if (= (nth s token) "DIV-OP")
                   (do
                     (if (= (nth s (inc token)) "ADD-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MINUS-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MULT-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "DIV-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "RPAREN")
                       (def result "REJECT "))
                     )
                   )
                 (if (= (nth s token) "MULT-OP")
                   (do
                     (if (= (nth s (inc token)) "ADD-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MINUS-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "MULT-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "DIV-OP")
                       (def result "REJECT "))
                     (if (= (nth s (inc token)) "RPAREN")
                       (def result "REJECT "))
                     )
                   )
                 (if (= (nth s token) "RPAREN")
                    (do
                      (if (= (nth s (inc token)) "FACTOR")
                        (def result "REJECT "))
                      (def  paren-counter (dec paren-counter))
                      ))



             )
                    (if (not= paren-counter 0 )
                      (def result "REJECT: Missed matched parenthesis"))
                    ))


(def parseing (fn [list-tokens]

                (def parsed-list (apply str list-tokens))
                (def parsed-list (clojure.string/replace parsed-list #"ID " "FACTOR "))
                (def parsed-list (clojure.string/replace parsed-list #"INT " "FACTOR "))
                (def parsed-list (clojure.string/split parsed-list #" "))
                ;(println parsed-list)
                )
  )


(use 'clojure.java.io)
(def one-line (list))
(with-open [rdr (reader "input.txt")]
  (doseq [line (line-seq rdr)]
    (def one-line (conj one-line line)))
  )
(def num-of-lines (count one-line))
;(println num-of-lines)
(def j 0)
(dotimes [j (count one-line)]
  (def lexemes (list))
  (def tokens (list))
  (def parsed-list str)
  (def result "ACCEPT ")
  (def token 0)
  (def expression (nth (reverse one-line) j))
  (def holder 0)
  (dotimes [holder (count expression)]
    (run-all (int (nth expression holder)))
    )
  (parseing (reverse tokens))
  (def paren-counter 0)
  (check-first-last parsed-list)

  (if (= result "ACCEPT ")
    (check-Paren parsed-list))

  (if (= result "ACCEPT ")
      (check-string parsed-list)
         )

  (println "All Tokens")
  (println result (reverse tokens))

  )



