(ns wk3.pj1)

; A game of blackjack.
; Cards are represented as strings (i.e. "10s", "js", "qh", etc)

(defn twenty-one [strategy]

  (defn rank [card] (first card))

  (defn rank-value [card]
    (cond (= (rank card) \j) 10)
    (cond (= (rank card) \q) 10)
    (cond (= (rank card) \k) 10)
    (cond (= (rank card) \a) '(1 11))
    :else (Character/digit (rank card) 10))

  ; Given a hand of cards computes the best possible total
  ; (An ace can be either 1 or 11 points).
  (defn best-total [hand]
    (defn is-ace? [card] (= (rank card) \a))

    (def total-without-aces
      (reduce + (map (fn [c] (if (is-ace? c) 0 (rank-value c))) hand)))

    (defn possible-ace-values [hand]
      (let [n-aces (reduce + (map (fn [c] (if (is-ace? c) 1 0)) hand))]
        (cond (= n-aces 1) '(11 1)
              (= n-aces 2) '(12 2)
              (= n-aces 3) '(13 3)
              (= n-aces 4) '(14 4))))

    (first (filter
             #(<= % 21)
             (map #(+ total-without-aces %) (possible-ace-values hand)))))

  ; The customer always plays before the dealer.
  ; Returns 1: Customer wins, 0: Tie, -1: Losses
  (defn play-dealer [customer-hand dealer-hand deck]
    (let [dealer-total (best-total dealer-hand)
          customer-total (best-total customer-hand)]
      (cond (> dealer-total 21) 1 ; Dealer busted, customer wins
            (< dealer-total 17)   ; Dealer always takes a hit if he has 16 or less
            (play-dealer customer-hand (cons dealer-hand (first deck)) (rest deck))
            (< customer-total dealer-total) -1    ; Customer losses
            (= dealer-total customer-total) 0     ; Tie
            (> customer-total dealer-total) 1)))  ; Customer wins

  (define (play-customer customer-hand-so-far dealer-up-card rest-of-deck)
          (cond ((> (best-total customer-hand-so-far) 21) -1)
                ((strategy customer-hand-so-far dealer-up-card)
                  (play-customer (se customer-hand-so-far (first rest-of-deck))
                                 dealer-up-card
                                 (bf rest-of-deck)))
                (else
                  (play-dealer customer-hand-so-far
                               (se dealer-up-card (first rest-of-deck))
                               (bf rest-of-deck)))))


  (defn play-customer [customer-hand dealer-up-card deck]
    (let [customer-total (best-total customer-hand)]
      (cond (> customer-total 21) -1
            ; If customer should take a hit
            ((strategy customer-hand dealer-up-card)
              (play-customer (cons customer-hand (first deck))
                             dealer-up-card
                             (rest deck)))
            :else (play-dealer customer-hand
                               (cons dealer-up-card (first deck))
                               (rest deck))))))


; Same strategy as dealer, ask for another card if
; the hand is 16 or less.
(defn stop-at-17 [customer-hand dealer-up-card]
  (< (best-total customer-hand) 17))

