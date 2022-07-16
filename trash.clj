(ns trash)


(require '[clj-http.client :as client])
(require '[cheshire.core :as cheshire])


(def token "....")


{:ok true,
 :result [{:message {:chat {:all_members_are_administrators true,
                            :id -721166690,
                            :title "sss",
                            :type "group"},
                     :date 1657608797,
                     :from {:first_name "Ivan",
                            :id 223429441,
                            :is_bot false,
                            :last_name "Grishaev",
                            :username "igrishaev"},
                     :group_chat_created true,
                     :message_id 15},
           :update_id 75640054}]}


{:ok true,
 :result [{:message {:chat {:all_members_are_administrators true,
                            :id -721166690,
                            :title "sss",
                            :type "group"},
                     :date 1657608797,
                     :from {:first_name "Ivan",
                            :id 223429441,
                            :is_bot false,
                            :last_name "Grishaev",
                            :username "igrishaev"},
                     :group_chat_created true,
                     :message_id 15},
           :update_id 75640054}
          {:message {:chat {:all_members_are_administrators true,
                            :id -721166690,
                            :title "sss",
                            :type "group"},
                     :date 1657608895,
                     :from {:first_name "Ivan",
                            :id 223429441,
                            :is_bot false,
                            :last_name "Grishaev",
                            :username "igrishaev"},
                     :message_id 16,
                     :new_chat_member {:first_name "TEST",
                                       :id 873472876,
                                       :is_bot true,
                                       :username "fsdfsfdsdfgdfhsdfgsdfg_bot"},
                     :new_chat_members [{:first_name "TEST",
                                         :id 873472876,
                                         :is_bot true,
                                         :username "fsdfsfdsdfgdfhsdfgsdfg_bot"}],
                     :new_chat_participant {:first_name "TEST",
                                            :id 873472876,
                                            :is_bot true,
                                            :username "fsdfsfdsdfgdfhsdfgsdfg_bot"}},
           :update_id 75640055}]}



(defn send-message []
  (let [request
        {:method :post
         :url "https://api.telegram.org/bot.../sendMessage"
         :form-params
         {:chat_id -721166690
          :text "hello @fsdfsfdsdfgdfhsdfgsdfg_bot !!! hey"
          ;; :reply_to_message_id 16
          :allow_sending_without_reply true
          :protect_content true}
         :content-type :json
         :as :json}

        response
        (client/request request)

        {:keys [status body]}
        response]

    body))


(defn ban-user [user-id]
  (let [request
        {:method :post
         :url "https://api.telegram.org/bot.../banChatMember"
         :form-params
         {:chat_id -721166690
          :user_id user-id
          :until_date (quot (System/currentTimeMillis) 1000)
          :revoke_messages true          }

         :throw-exceptions? false
         :coerce :always
         :content-type :json
         :as :json}

        response
        (client/request request)

        {:keys [status body]}
        response]

    body))


{:description "Bad Request: USER_ID_INVALID", :error_code 400, :ok false}

;; deleteMessage
;; chat_id
;; message_id


;; offset
;; limit
;; timeout
;; allowed_updates

{:message_id 22,
 :from
 {:id 882763008,
  :is_bot true,
  :first_name "Ivan Grishaev's Bot",
  :username "igrishaev_bot"},
 :chat
 {:id -721166690,
  :title "sss",
  :type "group",
  :all_members_are_administrators true},
 :date 1657695757,
 :text "hello!"}


[{:update_id 75640711,
  :callback_query
  {:id "959622145778160373",
   :from
   {:id 223429441,
    :is_bot false,
    :first_name "Ivan",
    :last_name "Grishaev",
    :username "igrishaev",
    :language_code "en"},
   :message
   {:message_id 742,
    :from
    {:id 882763008,
     :is_bot true,
     :first_name "Ivan Grishaev's Bot",
     :username "igrishaev_bot"},
    :chat
    {:id -721166690,
     :title "sss",
     :type "group",
     :all_members_are_administrators true},
    :date 1657729194,
    :text "hello!",
    :reply_markup
    {:inline_keyboard
     [[{:text "a", :callback_data "aaa"}
       {:text "b", :callback_data "bbb"}
       {:text "c", :callback_data "ccc"}]]}},
   :chat_instance "-2767002656552552926",
   :data "ccc"}}]
