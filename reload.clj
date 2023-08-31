(use 'hawk.core)
(use 'clojure.string)
(use 'teleward.main)

(def srcwatch (hawk.core/watch!
                [{:paths ["."]
                  :filter #(clojure.string/ends-with? (.getName (:file %2)) ".clj")
                  :handler (fn [ctx e] (require 'teleward.main :reload-all) (println "Reloaded") ctx)}]))

(def r (future (teleward.main/-main)))

(comment
  (future-cancel r)
  (hawk.core/stop! srcwatch)
)
