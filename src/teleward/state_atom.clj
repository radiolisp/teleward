(ns teleward.state-atom
  "
  An atom-driven state that tracks a map like
  chat-id => user-id => {attrs}
  inside.
  "
  (:require
   [teleward.state :as state]))


(defn in? [val collection]
  (contains? (set collection) val))


(defrecord AtomState [-state]

  state/IState

  (set-attr [_ chat-id user-id attr val]
    (swap! -state assoc-in [chat-id user-id attr] val))

  (set-attrs [_ chat-id user-id mapping]
    (swap! -state update-in [chat-id user-id] merge mapping))

  (get-attr [_ chat-id user-id attr]
    (get-in @-state [chat-id user-id attr]))

  (get-attrs [_ chat-id user-id]
    (get-in @-state [chat-id user-id]))

  (del-attr [_ chat-id user-id attr]
    (swap! -state update-in [chat-id user-id] dissoc attr))

  (del-attrs [_ chat-id user-id]
    (swap! -state update chat-id dissoc user-id))

  (inc-attr [_ chat-id user-id attr]
    (swap! -state update-in [chat-id user-id attr] (fnil inc 0)))

  (filter-by-attr [_ attr op value]

    (let [fn-op
          (case op
            (> :>) >
            (< :<) <
            (= :=) =
            (<= :<=) <=
            (>= :>=) >=
            (in :in) in?
            ;; else
            (throw (new Exception (format "Wrong op: %s" op))))]

      (for [[chat-id user-id->attrs] @-state
            [user-id attrs] user-id->attrs

            :when
            (some-> attrs (get attr) (fn-op value))]

        [chat-id user-id attrs]))))


(defn make-state [& _]
  (map->AtomState {:-state (atom {})}))


#_
(

 (def -s (make-state))

 (state/set-attr -s 1 2 :foo 1)
 (state/set-attr -s 1 3 :foo 9)

 (state/get-attrs -s 1 2)
 (state/get-attr -s 1 2 :foo)

 (state/inc-attr -s 1 2 :foo)

 (state/filter-by-attr -s :foo '> 3)

 (state/filter-by-attr -s :foo :in #{9})


 )
